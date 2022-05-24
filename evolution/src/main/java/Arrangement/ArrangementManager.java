package Arrangement;

import Algorithm.AlgorithmConfig;
import Algorithm.ArrangementEvoSolution;
import Algorithm.ArrangementSolution;
import Crossovers.BasicRandomCrossover;
import Model.Employee.Employee;
import Model.Employee.EmployeePreferences;
import Model.Slot.ReqSlot;
import Rule.IRule;
import Rule.RuleSlots.RuleSlots;
import Rule.RuleSlots.RuleSlotsPreference;
import org.json.JSONException;
import org.json.JSONObject;
import org.uncommons.maths.random.MersenneTwisterRNG;
import org.uncommons.watchmaker.framework.EvolutionEngine;
import org.uncommons.watchmaker.framework.EvolutionaryOperator;
import org.uncommons.watchmaker.framework.GenerationalEvolutionEngine;
import org.uncommons.watchmaker.framework.TerminationCondition;
import org.uncommons.watchmaker.framework.operators.EvolutionPipeline;

import java.util.*;

public class ArrangementManager
{
    private ArrangementProperties m_CurrArrangementProp = null;
    private ArrangementStatus m_CurrArrangementStatus = ArrangementStatus.SET_PROPS;
    private EvolutionEngine<Arrangement> engine = null;

    private ArrangementSolution curArrangementSolution = null;
    private Map<String, Ticket> tickets = new HashMap<String, Ticket>();
    private ArrangementEvaluator arrangementEvaluator = null;
    private Arrangement solution;
    private Thread evolutionWorker;

    public boolean isEvolutionWorkerAlive() {
        try {
            return evolutionWorker.isAlive();
        } catch(Exception e) {
            return false;
        }
    }

    public ArrangementStatus getCurrArrangementStatus() {
        return m_CurrArrangementStatus;
    }

    public void setEmployeePreference(EmployeePreferences employeePreference) throws JSONException {
        if (m_CurrArrangementStatus != ArrangementStatus.WAIT_EMP_REQ) {
            throw new RuntimeException("Failed to setEmployeePreference\n Current status: " +
                    m_CurrArrangementStatus + " expected: WAIT_EMP_REQ");
        }

        // todo: we should think of a better design than save the preferences
        //  inside the rules which is inside the arrangement props...
        //  maybe save the preferences in seperated field or even a whole independent preferences service!

        Set<IRule> rules = this.m_CurrArrangementProp.getRules();
        JSONObject preferencesJson = employeePreference.getPreferences();
        Iterator<String> ruleTypes = preferencesJson.keys();
        while(ruleTypes.hasNext()) {
            String ruleType = ruleTypes.next();
            IRule rule = rules.stream()
                    .filter(x -> x.getName().equals(ruleType))
                    .findFirst()
                    .orElse(null);

            if (rule == null) {
                throw new RuntimeException("rule type is not supported: " + ruleType);
            }

            JSONObject preferenceJson = (JSONObject) preferencesJson.get(ruleType);
            rule.addPreference(employeePreference.getEmployee(), preferenceJson); // baaa sa..
        }
    }

    public List<Employee> getActiveEmployees() {
        return this.m_CurrArrangementProp.getActiveEmployees();
    }

    /**
     * ONLY FOR TESTING
     * todo: we should think of a better design to return employee preference nicer
     */
    public List<RuleSlotsPreference> getEmployeesSlotsPreferences() {
        // let me introduce you the most terrible OOP method ever written.
        // enjoy catching the anti-patterns :)
        Set<IRule> rules = this.m_CurrArrangementProp.getRules();

        // I know, I know, pulling the instance out of dictionary keys make you sick
        RuleSlots rule = (RuleSlots) rules.stream() // cast to death
                .filter(x -> x.getName().equals("RuleSlots")) // hard code to end of times!
                .findFirst()
                .orElse(null);

        // just for testing relax!...
        return rule.getPreferences();
    }

    public List<ReqSlot> getReqSlots() {
        return m_CurrArrangementProp.getSlots();
    }

 // comment
    public void setCurrArrangementProp(ArrangementProperties m_CurrArrangementProp) {
        this.m_CurrArrangementProp = m_CurrArrangementProp;
        this.m_CurrArrangementStatus = ArrangementStatus.WAIT_EMP_REQ;
    }

    public ArrangementProperties getCurrArrangementProp() {
        return this.m_CurrArrangementProp;
    }

    public void BlockEmployeesToSetPref()
    {
        if (this.m_CurrArrangementStatus != ArrangementStatus.WAIT_EMP_REQ)
            return;

        this.m_CurrArrangementStatus = ArrangementStatus.SOLVING;
    }

    public void blockEmployeesToOpenTickets()
    {
        if (this.m_CurrArrangementStatus != ArrangementStatus.WAIT_EMP_APPROVAL)
            return;

        this.m_CurrArrangementStatus = ArrangementStatus.SOLVING;
    }

    public void setCurrArrangementStatus(ArrangementStatus m_CurrArrangementStatus) {
        this.m_CurrArrangementStatus = m_CurrArrangementStatus;
    }
 // main comment
    // todo: wrap with thread
    public void startAlgorithm(AlgorithmConfig algorithmConfig)
    {
        if (this.m_CurrArrangementStatus == ArrangementStatus.WAIT_EMP_REQ ||
                this.m_CurrArrangementStatus == ArrangementStatus.WAIT_EMP_APPROVAL) {
            this.m_CurrArrangementStatus = ArrangementStatus.SOLVING;
            this.tickets.clear();
        }
        if (this.m_CurrArrangementStatus != ArrangementStatus.SOLVING) {
            throw new RuntimeException("Failed to start algorithm \n Current status: " +
                    this.m_CurrArrangementStatus + " expected: SOLVING");
        }
            /*
         * todo:
         *
         *
         *
         * create EvolutionThread extends Thread that
         * implements all the logic off creating new engine
         * and run evolve based on algorithmConfig.
         *
         *
         *  */
        List<EvolutionaryOperator<Arrangement>> operators = new ArrayList<>();
        operators.add(algorithmConfig.getCrossover());
        operators.addAll(algorithmConfig.getMutations());
        EvolutionaryOperator<Arrangement> pipeline = new EvolutionPipeline<Arrangement>(operators);
        try{
            ArrangementFactory arrangementFactory = new ArrangementFactory(
                    m_CurrArrangementProp.getSlots(),
                    m_CurrArrangementProp.getActiveEmployees()
            );
            this.arrangementEvaluator = new ArrangementEvaluator(m_CurrArrangementProp.getRule2weight());
            this.engine = new GenerationalEvolutionEngine<Arrangement>(
                    arrangementFactory,
                    pipeline,
                    this.arrangementEvaluator,
                    algorithmConfig.getSelectionStrategy(),
                    new MersenneTwisterRNG()
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
        EvolutionEngine<Arrangement> finalEngine = engine;

        this.engine.addEvolutionObserver(populationData -> {
            curArrangementSolution = new ArrangementEvoSolution(
                    populationData.getBestCandidate(),
                    populationData.getGenerationNumber(),
                    populationData.getBestCandidateFitness());
        });

        this.evolutionWorker = new Thread(() ->{
            //the last parmeter passing is some kind of hack to be able to pass arrylist to vararg cause teminateCondition is ... type parameter
            //link:https://thispointer.com/how-to-pass-an-arraylist-to-varargs-method/
            solution = finalEngine.evolve(algorithmConfig.getPopulationSize(),
                    algorithmConfig.getElitism(),
                    algorithmConfig.getTerminationConditions().toArray(new TerminationCondition[0]));
        });
        this.evolutionWorker.setName("evolutionThread");
        this.evolutionWorker.start();
    }

    public ArrangementSolution getCurArrangementSolution() {
        return curArrangementSolution;
    }

    public void publishArrangement() {
        if (!m_CurrArrangementStatus.equals(ArrangementStatus.SOLVING)) {
            return;
        }

        // todo: if algorithm is running then block the operation and wait for terminate/end
        // todo: persist arrangement

        m_CurrArrangementStatus = ArrangementStatus.WAIT_EMP_APPROVAL;
    }

    public void createTicket(Employee employee,
                                   String employeeMessage) {
        Ticket ticket = new Ticket(employee, employeeMessage);
        this.tickets.put(ticket.getId(), ticket);
    }

    public void closeTicket(String ticketId) {
        this.tickets.get(ticketId).setStatus(TicketStatus.CLOSED);
    }

    public void finishArrangement() {
        if (!m_CurrArrangementStatus.equals(ArrangementStatus.WAIT_EMP_APPROVAL)) {
            return;
        }

        // todo: persist the solution into the db

        m_CurrArrangementStatus = ArrangementStatus.FINISH;
    }

    public void setArrangement(Arrangement arrangement) {
        if (!this.m_CurrArrangementStatus.equals(ArrangementStatus.WAIT_EMP_APPROVAL)) {
            throw new RuntimeException("Failed to set new arrangement \n Current status: " +
                    this.m_CurrArrangementStatus + " expected: WAIT_EMP_APPROVAL");
        }

        this.curArrangementSolution = new ArrangementSolution(
                arrangement,
                this.arrangementEvaluator.getFitness(arrangement, null)
        );
    }
}
