package com.example.school.DTO.InDTO;

import com.example.school.DTO.AddressDTO;
import com.example.school.Model.Address;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TeacherInDTO {
    @NotEmpty(message = "Empty name")
    private String name;
    @NotNull(message = "empty age")
    @Min(value = 24,message = "Age should be more than 24")
    private Integer age;
    @NotEmpty(message = "Empty email")
    private String email;
    @NotNull(message = "empty salary")
    private Double salary;


}
