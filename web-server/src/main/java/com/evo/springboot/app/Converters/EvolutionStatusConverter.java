package com.evo.springboot.app.Converters;

import Algorithm.EvolutionStatus;
import com.evo.springboot.app.DTO.Outgoing.EvolutionStatusDTO;
import com.evo.springboot.app.DTO.Outgoing.ShiftDTO;

import java.util.ArrayList;
import java.util.List;

public class EvolutionStatusConverter {
    public static EvolutionStatusDTO convert(EvolutionStatus evolutionStatus) {

        // convert shifts
        List<ShiftDTO> arrangementDTO = new ArrayList<>();
        evolutionStatus.arrangementSolution.arrangement.getShifts().forEach(shift -> {
            arrangementDTO.add(new ShiftDTO(
                    shift.getEmployee().getID(),
                    shift.getEmployee().getFullName(),
                    shift.getRole().name,
                    TimeConverter.convert(shift.getSlot().getStartTime()),
                    TimeConverter.convert(shift.getSlot().getEndTime())
            ));
        });

        return new EvolutionStatusDTO(
                arrangementDTO,
                evolutionStatus.arrangementSolution.generationNumber,
                evolutionStatus.arrangementSolution.fitness,
                evolutionStatus.isFinished
        );
    }
}
