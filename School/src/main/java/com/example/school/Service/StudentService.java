package com.example.school.Service;

import com.example.school.ApiResponse.ApiException;
import com.example.school.DTO.CourseDTO;
import com.example.school.DTO.StudentDTO;
import com.example.school.Model.Course;
import com.example.school.Model.Student;
import com.example.school.Repository.CourseRepository;
import com.example.school.Repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;


    public List<StudentDTO> getStudents(){
        List<Student> students =studentRepository.findAll();
        List<StudentDTO> studentDTOS=new ArrayList<>();

        for(Student student:students){
            StudentDTO studentDTO = new StudentDTO(student.getName(),student.getAge(),student.getMajor());
            studentDTOS.add(studentDTO);
        }
        return studentDTOS;
    }

    public void addStudent(StudentDTO studentDTO){
        Student student = new Student(null,studentDTO.getName(),studentDTO.getAge(),studentDTO.getMajor(),null);
        studentRepository.save(student);
    }

    public void updateStudent(Integer id,StudentDTO studentDTO){
        Student student = studentRepository.findStudentById(id);
        if(student==null){
            throw new ApiException("Student not found");
        }
        student.setName(studentDTO.getName());
        student.setAge(studentDTO.getAge());
        student.setMajor(studentDTO.getMajor());
        studentRepository.save(student);
    }


    public void deleteStudent(Integer id){
        Student student = studentRepository.findStudentById(id);
        if(student==null){
            throw new ApiException("Student not found");
        }
        studentRepository.delete(student);
    }


    public void changeStudentMajor(Integer id, String major){
        Student student = studentRepository.findStudentById(id);
        if(student ==null){
            throw new ApiException("Student not found");
        }
        if(student.getCourses()!=null) {
            for (Course course : student.getCourses()) {
                course.getStudents().remove(student);
            }
            student.getCourses().clear();
        }
        student.setMajor(major);
        studentRepository.save(student);
    }


    public void assignStudentToCourse(Integer studentId,Integer courseId){
        Student student = studentRepository.findStudentById(studentId);
        Course course = courseRepository.findCourseById(courseId);
        if(student==null||course==null){
            throw new ApiException("Can't assign");
        }
        student.getCourses().add(course);
        course.getStudents().add(student);

        studentRepository.save(student);
        courseRepository.save(course);
    }


}

