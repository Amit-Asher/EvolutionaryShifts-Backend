package Arrangement;

import Algorithm.AlgorithmConfig;
import Algorithm.ArrangementSolution;
import Algorithm.ArrangementEvoSolution;
import Crossovers.BasicCrossover;
import Model.Employee.Employee;
import Model.Employee.EmployeePreferences;
import Mutations.MutationByDay;
import Rule.IRule;
import org.uncommons.maths.random.MersenneTwisterRNG;
import org.uncommons.watchmaker.framework.*;
import org.uncommons.watchmaker.framework.operators.EvolutionPipeline;
import org.uncommons.watchmaker.framework.selection.RouletteWheelSelection;
import org.uncommons.watchmaker.framework.termination.TargetFitness;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArrangementManager
{
    private ArrangementProperties m_CurrArrangementProp;
    private ArrangementStatus m_CurrArrangementStatus = ArrangementStatus.SET_PROPS;
    private EvolutionEngine<Arrangement> engine;

    private ArrangementSolution curArrangementSolution;
    private Map<String, Ticket> tickets = new HashMap<String, Ticket>();
    private ArrangementEvaluator arrangementEvaluator;

    public ArrangementStatus getCurrArrangementStatus() {
        return m_CurrArrangementStatus;
    }

    public void updateEmployeePreference(EmployeePreferences employeePreference)
    {
        if (m_CurrArrangementStatus != ArrangementStatus.WAIT_EMP_REQ) {
            throw new RuntimeException("Failed to setEmployeePreference\n Current status: " +
                    m_CurrArrangementStatus + " expected: WAIT_EMP_REQ");
        }

        // add employees preferences to each rule configuration
        employeePreference.getPreferences().forEach((ruleName, preferenceInput) -> {
            m_CurrArrangementProp.m_rule2weight.keySet().stream().filter(rule -> {
                return rule.getName().equals(ruleName);
            }).forEach(rule -> rule.addPreference(preferenceInput));
        });
    }

    public void setCurrArrangementProp(ArrangementProperties m_CurrArrangementProp) {
        this.m_CurrArrangementProp = m_CurrArrangementProp;
        this.m_CurrArrangementStatus = ArrangementStatus.WAIT_EMP_REQ;
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

    // todo: wrap with thread
    public Arrangement startAlgorithm(AlgorithmConfig algorithmConfig)
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

        Map<IRule, Double> rule2Weight = m_CurrArrangementProp.getM_rule2weight();
        ArrangementFactory factory = new ArrangementFactory();
        List<EvolutionaryOperator<Arrangement>> operators = new ArrayList<>(2);
        operators.add(new MutationByDay(0.3));
        operators.add(new BasicCrossover(2));
        EvolutionaryOperator<Arrangement> pipeline = new EvolutionPipeline<>(operators);
        EvolutionEngine<Arrangement> engine = null;
        try {
            this.arrangementEvaluator = new ArrangementEvaluator(rule2Weight);
            engine = new GenerationalEvolutionEngine<>(
                    factory,
                    pipeline,
                    this.arrangementEvaluator,
                    new RouletteWheelSelection(),
                    new MersenneTwisterRNG()
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
        engine.addEvolutionObserver(populationData -> {
            curArrangementSolution = new ArrangementEvoSolution(
                    populationData.getBestCandidate(),
                    populationData.getGenerationNumber(),
                    populationData.getBestCandidateFitness());
        });
        return engine.evolve(100, // 100 individuals in the population.
                5, // 5% elitism.
                new TargetFitness(0, true));
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
