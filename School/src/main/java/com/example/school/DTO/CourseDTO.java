package com.example.school.DTO;

import com.example.school.Model.Teacher;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CourseDTO {
    @NotEmpty(message = "empty name")
    private String name;
    private Teacher teacher;

}
