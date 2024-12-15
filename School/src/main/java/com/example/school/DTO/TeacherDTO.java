package com.example.school.DTO;

import com.example.school.Model.Address;
import com.example.school.Model.Course;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherDTO {

    public String name;
    public String email;
    private Integer age;
    private Double salary;
    private Address address;

}
