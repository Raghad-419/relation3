package com.example.school.Service;

import com.example.school.ApiResponse.ApiException;
import com.example.school.DTO.AddressDTO;
import com.example.school.DTO.CourseDTO;
import com.example.school.DTO.InDTO.TeacherInDTO;
import com.example.school.DTO.TeacherDTO;
import com.example.school.Model.Address;
import com.example.school.Model.Course;
import com.example.school.Model.Teacher;
import com.example.school.Repository.AddressRepository;
import com.example.school.Repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TeacherService {
    private final TeacherRepository teacherRepository;
    private final AddressRepository addressRepository;
//
//    public List<Teacher> getTeachers(){
//        return teacherRepository.findAll();
//    }

    public List<TeacherDTO> getTeachers(){
        List<Teacher> teachers = teacherRepository.findAll();
        List<TeacherDTO> teacherDTOS = new ArrayList<>();

        for(Teacher teacher: teachers){
            TeacherDTO teacherDTO = new TeacherDTO(teacher.getName(),teacher.getEmail(),teacher.getAge(),teacher.getSalary(),teacher.getAddress());
            teacherDTOS.add(teacherDTO);
        }
        return teacherDTOS;
    }

    public void addTeacher(TeacherInDTO teacherInDTO){
       Teacher teacher = new Teacher(null,teacherInDTO.getName(),teacherInDTO.getAge(),teacherInDTO.getEmail(),teacherInDTO.getSalary(),null ,null);
       teacherRepository.save(teacher);
    }

    public void updateTeacher(Integer id,TeacherInDTO teacherInDTO){
        Teacher teacher =teacherRepository.findTeacherById(id);
        if(teacher==null){
            throw new ApiException("Teacher not found");
        }
        teacher.setAge(teacherInDTO.getAge());
        teacher.setName(teacherInDTO.getName());
        teacher.setEmail(teacherInDTO.getEmail());
        teacher.setSalary(teacherInDTO.getSalary());

        teacherRepository.save(teacher);
    }


    public void deleteTeacher(Integer id){
        Teacher teacher = teacherRepository.getTeacherById(id);
        if(teacher==null) {
            throw new ApiException("Teacher not found");
        }
        Address address = addressRepository.findAddressById(id);

        teacher.setAddress(null);
        if(address != null){
        addressRepository.delete(teacher.getAddress());}
        teacherRepository.delete(teacher);
    }


    public TeacherDTO getTeacherById(Integer id){
        Teacher teacher = teacherRepository.findTeacherById(id);
        if(teacher ==null){
            throw new ApiException("Teacher not found");
        }
        return new TeacherDTO(teacher.getName(),teacher.getEmail(),teacher.getAge(),teacher.getSalary(),teacher.getAddress());
    }

}
