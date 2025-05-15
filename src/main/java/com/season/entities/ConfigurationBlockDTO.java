package com.season.entities;

import lombok.Data;

import java.util.List;

@Data
public class ConfigurationBlockDTO {
    private String blockName;
    private List<FieldDTO> fields;



    public String getBlockName() {
        return blockName;
    }

    public void setBlockName(String blockName) {
        this.blockName = blockName;
    }

    public List<FieldDTO> getFields() {
        return fields;
    }

    public void setFields(List<FieldDTO> fields) {
        this.fields = fields;
    }
}