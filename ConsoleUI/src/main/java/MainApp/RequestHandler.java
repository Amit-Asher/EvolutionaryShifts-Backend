package MainApp;

import Algorithm.AlgorithmConfig;
import Algorithm.EvolutionStatus;
import Arrangement.Arrangement;
import Arrangement.ArrangementProperties;
import BusinessLogic.BusinessLogic;
import Crossovers.BasicCrossover;
import Model.Employee.Employee;
import Model.Role;
import Model.Shift;
import Model.Slot.PrfSlot;
import Mutations.MutationByEmployee;
import Mutations.MutationBySlot;
import Rule.RuleSlots.RuleSlotsPreference;
import org.uncommons.maths.random.Probability;
import org.uncommons.watchmaker.framework.EvolutionaryOperator;
import org.uncommons.watchmaker.framework.SelectionStrategy;
import org.uncommons.watchmaker.framework.TerminationCondition;
import org.uncommons.watchmaker.framework.operators.AbstractCrossover;
import org.uncommons.watchmaker.framework.selection.RankSelection;
import org.uncommons.watchmaker.framework.selection.RouletteWheelSelection;
import org.uncommons.watchmaker.framework.selection.TournamentSelection;
import org.uncommons.watchmaker.framework.termination.GenerationCount;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

public class RequestHandler {

    public static void handleSelection(MenuItem selection) {
        switch(selection) {
            case ShowAllRoles:
                handleShowAllRoles();
                break;
            case ShowAllEmployees:
                handleShowAllEmployees();
                break;
            case ShowArrangementProperties:
                handleShowArrangementProperties();
                break;
            case ShowEmployeesPreferences:
                handleShowEmployeesPreferences();
                break;
            case RunEvolutionAlgorithm:
                handleRunEvolutionAlgorithm();
                break;
            case ShowBestSolution:
                handleShowBestSolution();
                break;
            case Exit:
                System.exit(1);
                break;
            default:
                break;
        }
    }

    public static void handleShowAllRoles() {
        List<Role> allRoles = BusinessLogic.getInstance().getAllRoles(Constants.COMPANY_NAME);

        System.out.println();
        System.out.println("******* ALL ROLES ********");
        allRoles.forEach((role) -> {
            System.out.println(role.m_Name);
        });
        System.out.println("**************************");
        System.out.println();
    }

    public static void handleShowAllEmployees() {
        List<Employee> allEmployees = BusinessLogic.getInstance().getAllEmployees(Constants.COMPANY_NAME);
        allEmployees = allEmployees.stream().sorted(Comparator.comparing(Employee::getFullName)).collect(Collectors.toList());

        System.out.println();
        System.out.println("***************** ALL EMPLOYEES ********************");
        String leftAlignFormat = "| %-20s | %-20s |%n";
        System.out.format("+----------------------+----------------------+%n");
        System.out.format("| Employee             | Roles                |%n");
        System.out.format("+----------------------+----------------------+%n");
        allEmployees.forEach((employee) -> {
            AtomicBoolean firstFlag = new AtomicBoolean(true);
            employee.getFitRoles().forEach(role -> {
                System.out.format(String.format(leftAlignFormat,
                        firstFlag.get() ? employee.getFullName() : "",
                        role.m_Name
                        ));
                firstFlag.set(false);
            });

        });
        System.out.println();
    }

    public static void handleShowArrangementProperties() {
        ArrangementProperties arrangementProperties = BusinessLogic.getInstance().getArrangementProperties(Constants.COMPANY_NAME);

        System.out.println("************** ARRANGEMENT PROPERTIES ******************");
        System.out.println("Manager required the following slots:");
        String leftAlignFormat = "| %-25s | %-15s | %-15s | %-15s | %-15s |%n";
        System.out.format("+---------------------------+-----------------+-----------------+-----------------+-----------------+%n");
        System.out.format("| Date                      | Start Time      | End Time        | Role            | Personnel Size  |%n");
        System.out.format("+---------------------------+-----------------+-----------------+-----------------+-----------------+%n");
        arrangementProperties.getSlots().forEach(reqSlot -> {
            DateTimeFormatter hmFormatter = DateTimeFormatter.ofPattern("HH:mm");
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("d.M.u");
            System.out.format(String.format(leftAlignFormat,
                    reqSlot.getSlot().getStartTime().format(dateFormatter) +
                            " (" + reqSlot.getSlot().getStartTime().getDayOfWeek() + ")",
                    reqSlot.getSlot().getStartTime().format(hmFormatter),
                    reqSlot.getSlot().getEndTime().format(hmFormatter),
                    reqSlot.getRole().m_Name,
                    reqSlot.getPersonnelSize().toString()));
        });

        System.out.format("%nManager opened the following rules and weights (preferences types):%n");
        arrangementProperties.getRule2weight().forEach((key, value) -> {
            System.out.format("%s: %s%n",
                    key.getName(),
                    value);
        });


        System.out.format("%nManager defined the active employees as follows (participants):%n");
        arrangementProperties.getActiveEmployees().forEach(employee -> {
            System.out.format("%s%n", employee.getFullName());
        });

        System.out.println();
    }

    public static void handleShowEmployeesPreferences() {
        List<RuleSlotsPreference> ruleSlotsPreferences = BusinessLogic.getInstance().getEmployeeSlotsPreference(Constants.COMPANY_NAME);

        System.out.println("************** EMPLOYEES PREFERENCES ******************");
        String leftAlignFormat = "| %-20s | %-15s | %-18s | %-18s |%n";
        System.out.format("+----------------------+-----------------+--------------------+--------------------+%n");
        System.out.format("| Employee             | Role            | Start Time         | End Time           |%n");
        System.out.format("+----------------------+-----------------+--------------------+--------------------+%n");

        ruleSlotsPreferences.forEach(preference -> {
            List<PrfSlot> slots = preference.getSlots();
            slots.forEach(slot -> {
                System.out.format(String.format(leftAlignFormat,
                        preference.getEmployee().getFullName(),
                        slot.role,
                        slot.startTime,
                        slot.endTime));
            });
        });
        System.out.println();
    }

    public static void handleRunEvolutionAlgorithm() {

        System.out.println("hold on!...");
        List<Employee> activeEmployees = BusinessLogic.getInstance().getActiveEmployees(Constants.COMPANY_NAME);
        List<EvolutionaryOperator<Arrangement>> mutations = new ArrayList<>(2);
        MutationByEmployee mutationByEmployee = new MutationByEmployee(0.1, 3, activeEmployees);
        MutationBySlot mutationBySlot = new MutationBySlot(
                0.8,
                8,
                BusinessLogic.getInstance().getEmployeeSlotsPreference(Constants.COMPANY_NAME),
                BusinessLogic.getInstance().getReqSlots(Constants.COMPANY_NAME)

        );

        mutations.add(mutationByEmployee);
        mutations.add(mutationBySlot);

        AbstractCrossover<Arrangement> crossover = new BasicCrossover(7);

        // selection strategy
        SelectionStrategy<? super Arrangement> selectionStrategy = new RouletteWheelSelection();

        List<TerminationCondition> terminationConditions = new ArrayList<>();
        terminationConditions.add(new GenerationCount(300));

        // population size
        int populationSize = 100;

        // elitism
        int elitism = 10;

        AlgorithmConfig algorithmConfig = new AlgorithmConfig(
                mutations,
                crossover,
                selectionStrategy,
                terminationConditions,
                populationSize,
                elitism
        );

        BusinessLogic.getInstance().startAlgorithm(Constants.COMPANY_NAME, algorithmConfig);
        System.out.println("Evolution Algorithm Started Successfully!");
        System.out.println("(Choose 6 in order to watch status/solution)");
        System.out.println();
    }

    public static void handleShowBestSolution() {

        EvolutionStatus evolutionStatus = BusinessLogic.getInstance().getSolution(Constants.COMPANY_NAME);
        if (evolutionStatus.arrangementSolution == null) {
            System.out.println("No solution found");
            System.out.println("(Choose 5 in order to run evolution algorithm)");
            System.out.println();
            return;
        }

        if (!evolutionStatus.isFinished) {
            System.out.format("Evolution algorithm still running (generation: %s/%s, fitness: %s)%n%n",
                    evolutionStatus.arrangementSolution.generationNumber,
                    100,
                    evolutionStatus.arrangementSolution.fitness);
            return;
        }

        System.out.println("************** BEST ARRANGEMENT ******************");
        System.out.println("Fitness: " + evolutionStatus.arrangementSolution.fitness);
        String leftAlignFormat = "| %-25s | %-15s | %-15s | %-20s | %-15s |%n";
        System.out.format("+---------------------------+-----------------+-----------------+----------------------+-----------------+%n");
        System.out.format("| Date                      | Start Time      | End Time        | Employee             | Role            |%n");
        System.out.format("+---------------------------+-----------------+-----------------+----------------------+-----------------+%n");
        DateTimeFormatter hmFormatter = DateTimeFormatter.ofPattern("HH:mm");
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("d.M.u");

        evolutionStatus.arrangementSolution.arrangement.getShifts()
                .stream().sorted(Comparator.comparing(slot -> slot.getSlot().getStartTime()))
                .forEach(shift -> {
            System.out.format(String.format(leftAlignFormat,
                    shift.getSlot().getStartTime().format(dateFormatter) +
                            " (" + shift.getSlot().getStartTime().getDayOfWeek() + ")",
                    shift.getSlot().getStartTime().format(hmFormatter),
                    shift.getSlot().getEndTime().format(hmFormatter),
                    shift.getEmployee().getFullName(),
                    shift.getRole().m_Name));
        });

        System.out.println();
    }
}
