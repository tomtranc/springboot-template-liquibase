package com.ttran.example.model.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
public class Customer {
    private String id;
    @NotEmpty
    @Pattern(regexp = "^\\w+$")
    private String name;
    private String description;
}
