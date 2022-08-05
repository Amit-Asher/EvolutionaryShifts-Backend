package com.evo.springboot.app.Converters;

import Model.Slot.ReqSlot;
import Schemas.SchemaFamily;
import com.evo.springboot.app.DTO.Incoming.PropertiesDTO;
import com.evo.springboot.app.DTO.Outgoing.ParamOfSchemaDTO;
import com.evo.springboot.app.DTO.Outgoing.SchemaDTO;
import com.evo.springboot.app.DTO.Outgoing.SchemaFamilyDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SchemaConverter {

    public static List<SchemaFamilyDTO> covert(List<SchemaFamily> schemaFamilies) {
        List<SchemaFamilyDTO> schemaFamiliesDto = new ArrayList<>();
        schemaFamilies.stream().forEach(schemaFamily -> {
            schemaFamiliesDto.add(new SchemaFamilyDTO(
                    schemaFamily.getFamily(),
                    schemaFamily.getSchemas().stream().map(schema -> new SchemaDTO(
                                    schema.getName(),
                                    schema.getParams().stream().map(paramOfSchema -> new ParamOfSchemaDTO(
                                            paramOfSchema.getName(),
                                            paramOfSchema.getType()
                                    )).collect(Collectors.toList())
                    )).collect(Collectors.toList())
            ));
        });
        return schemaFamiliesDto;
    }
}
