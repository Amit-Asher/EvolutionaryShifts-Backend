package com.evo.springboot.app.Converters;

import com.evo.springboot.bl.BusinessLogic;
import Model.Employee.Employee;
import Model.Employee.EmployeePreferences;
import Rule.RuleSlots.RuleSlotsPreference;
import com.evo.springboot.app.DTO.Incoming.EmployeePreferencesDTO;
import com.evo.springboot.app.DTO.Outgoing.EmpSlotsPreferenceDTO;
import com.evo.springboot.app.DTO.Outgoing.PrfSlotDTO;
import com.evo.springboot.app.DTO.Outgoing.SlotsPreferencesDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PreferencesConverter {

    @Autowired
    BusinessLogic businessLogic;

    public EmployeePreferences convert(EmployeePreferencesDTO preferencesDTO) {
        // todo: make it stateless! yaks

        List<Employee> allEmployees = businessLogic.getAllEmployees(BusinessLogic.staticCompName);
        Employee employee = allEmployees.stream()
                // todo: we should filter by id, not name
                .filter(employee1 ->
                        employee1.getFullName().equals(preferencesDTO.getEmployeeName()))
                .findFirst()
                .orElse(null);

        return new EmployeePreferences(
                employee,
                preferencesDTO.getPreferences()
        );
    }

    public SlotsPreferencesDTO convert(List<RuleSlotsPreference> preferences) {
        List<EmpSlotsPreferenceDTO> empSlotsPreferences = new ArrayList<>();
        preferences.forEach(preference -> {
            List<PrfSlotDTO> prfSlotsDTO = new ArrayList<>();
            preference.getSlots().forEach(prfSlot -> {
                prfSlotsDTO.add(new PrfSlotDTO(
                        prfSlot.role,
                        prfSlot.startTime,
                        prfSlot.endTime
                ));
            });

            empSlotsPreferences.add(new EmpSlotsPreferenceDTO(
                    prfSlotsDTO,
                    preference.getEmployee().getID(),
                    preference.getEmployee().getFullName()
                    ));
        });

        return new SlotsPreferencesDTO(empSlotsPreferences);
    }
}