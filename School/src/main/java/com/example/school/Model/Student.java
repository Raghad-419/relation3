package com.example.school.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(columnDefinition = "varchar(20) not null")
    @NotEmpty(message = "empty name")
    private String name;
    @Column(columnDefinition = "int not null")
    @NotNull(message = "empty age")
    private Integer age ;
    @Column(columnDefinition = "varchar(30) not null")
    @NotEmpty(message = "empty major")
    private String major;

    @ManyToMany(mappedBy = "students")
    private Set<Course> courses;

}
