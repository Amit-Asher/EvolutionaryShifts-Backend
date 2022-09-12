package com.evo.springboot.app.Converters;

import Arrangement.ArrangementProperties;
import com.evo.springboot.bl.BusinessLogic;
import Model.Employee.Employee;
import Model.Range;
import Model.Role;
import Model.Slot.ReqSlot;
import Model.Slot.Slot;
import Rule.IRule;
import Rule.RuleFactory;
import com.evo.springboot.app.DTO.Incoming.PropertiesDTO;
import com.evo.springboot.app.DTO.Incoming.RangeDTO;
import com.evo.springboot.app.DTO.Incoming.ReqSlotDTO;
import com.evo.springboot.app.DTO.Incoming.RuleWeightDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class PropertiesConverter {
    @Autowired
    BusinessLogic businessLogic;
    
    public List<ReqSlot> buildReqSlots(PropertiesDTO propertiesDTO) {
        List<ReqSlotDTO> reqSlotsDTO = propertiesDTO.getReqSlots();
        List<ReqSlot> reqSlots = new ArrayList<>();
        reqSlotsDTO.forEach(reqSlotDTO -> {
            Slot slot = new Slot(
                    TimeConverter.convert(reqSlotDTO.getStartTime()),
                    TimeConverter.convert(reqSlotDTO.getEndTime())
            );

            // todo: make it stateless! yaks
            Set<Role> allRoles = businessLogic.getRoles(BusinessLogic.staticCompName);
            Role role = allRoles.stream()
                    .filter(existingRole -> existingRole.name.equals(reqSlotDTO.getRole()))
                    .findFirst()
                    .orElse(null);

            Range personnelSize = new Range(
                    reqSlotDTO.getPersonnelSize().getMin(),
                    reqSlotDTO.getPersonnelSize().getMax()
            );
            reqSlots.add(new ReqSlot(slot, role, personnelSize));
        });

        return reqSlots;
    }

    public List<Employee> buildActiveEmployees(PropertiesDTO propertiesDTO) {
        List<String> activeEmployeesIds = propertiesDTO.getActiveEmployeesIds();

        // todo: make it stateless! yaks
        List<Employee> allEmployees = businessLogic.getAllEmployees(BusinessLogic.staticCompName);
        List<Employee> activeEmployees = allEmployees.stream().filter(employee ->
                        activeEmployeesIds.contains(employee.getID()))
                .collect(Collectors.toList());

        return activeEmployees;
    }

    public static Map<IRule, Double> buildRuleWeights(PropertiesDTO propertiesDTO) {
        Map<IRule, Double> ruleWeights = new HashMap<IRule, Double>();
        propertiesDTO.getRulesWeights().forEach(ruleWeightDTO -> {
            ruleWeights.put(
                    RuleFactory.createRule(ruleWeightDTO.getRuleName()),
                    ruleWeightDTO.getWeight()
            );
        });
        return ruleWeights;
    }

    public ArrangementProperties convert(PropertiesDTO propertiesDTO) {
        List<ReqSlot> reqSlots = this.buildReqSlots(propertiesDTO);
        List<Employee> activeEmployees = this.buildActiveEmployees(propertiesDTO);
        // todo: pass 'params' json to factory in order to build the rule with generic schema for parameters
        Map<IRule, Double> ruleWeights = PropertiesConverter.buildRuleWeights(propertiesDTO);

        return new ArrangementProperties(
                reqSlots,
                activeEmployees,
                ruleWeights
        );
    }
    public PropertiesDTO convert(ArrangementProperties arrangementProperties) {
        PropertiesDTO propertiesDTO = new PropertiesDTO();
        if (arrangementProperties == null) {
            return propertiesDTO;
        }

        // build activeEmployeesIds
        List<String> activeEmployeesIds = arrangementProperties
                .getActiveEmployees()
                .stream().map(Employee::getID)
                .collect(Collectors.toList());
        propertiesDTO.setActiveEmployeesIds(activeEmployeesIds);

        // build rulesWeights
        List<RuleWeightDTO> rulesWeights = arrangementProperties
                .getRuleName2weight()
                .entrySet().stream().map(rule2weight -> {
                    RuleWeightDTO ruleWeightDTO = new RuleWeightDTO();
                    ruleWeightDTO.setRuleName(rule2weight.getKey().getName());
                    ruleWeightDTO.setWeight(rule2weight.getValue());
                    return ruleWeightDTO;
                }).collect(Collectors.toList());
        propertiesDTO.setRulesWeights(rulesWeights);

        // build reqSlots
        List<ReqSlotDTO> reqSlots = arrangementProperties
                .getReqSlots()
                .stream().map(reqSlot -> {
                    ReqSlotDTO reqSlotDTO = new ReqSlotDTO();
                    // set slot
                    reqSlotDTO.setStartTime(TimeConverter.convert(reqSlot.getSlot().getStartTime()));
                    reqSlotDTO.setEndTime(TimeConverter.convert(reqSlot.getSlot().getEndTime()));
                    // set personnel size
                    RangeDTO rangeDTO = new RangeDTO();
                    rangeDTO.setMax(reqSlot.getPersonnelSize().getMax());
                    rangeDTO.setMin(reqSlot.getPersonnelSize().getMin());
                    reqSlotDTO.setPersonnelSize(rangeDTO);
                    // set role
                    reqSlotDTO.setRole(reqSlot.getRole().getName());
                    return reqSlotDTO;
                }).collect(Collectors.toList());
        propertiesDTO.setReqSlots(reqSlots);

        return propertiesDTO;
    }

}
