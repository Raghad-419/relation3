package com.example.school.DTO;

import com.example.school.Model.Course;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
public class StudentDTO {

    @NotEmpty(message = "empty name")
    private String name;
    @NotNull(message = "empty age")
    private Integer age ;
    @NotEmpty(message = "empty major")
    private String major;
}
