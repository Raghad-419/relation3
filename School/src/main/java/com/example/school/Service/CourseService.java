package com.example.school.Service;

import com.example.school.ApiResponse.ApiException;
import com.example.school.DTO.CourseDTO;
import com.example.school.DTO.StudentDTO;
import com.example.school.DTO.TeacherDTO;
import com.example.school.Model.Course;
import com.example.school.Model.Student;
import com.example.school.Model.Teacher;
import com.example.school.Repository.CourseRepository;
import com.example.school.Repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {
    private final CourseRepository courseRepository;
    private final TeacherRepository teacherRepository;

    public List<CourseDTO> getCourses(){
    List<Course> courses = courseRepository.findAll();
    List<CourseDTO> courseDTOS = new ArrayList<>();
    for(Course course:courses){
        CourseDTO courseDTO = new CourseDTO(course.getName(),course.getTeacher());
        courseDTOS.add(courseDTO);
    }
    return courseDTOS;
    }

    public void addCourse(CourseDTO courseDTO){
        Course course = new Course(null,courseDTO.getName(),courseDTO.getTeacher(),null);
        courseRepository.save(course);

    }


    public void assignTeacherToCourse(Integer teacherId,Integer courseId){
        Teacher teacher = teacherRepository.findTeacherById(teacherId);
        Course course =courseRepository.findCourseById(courseId);
        if(teacher==null||course==null){
            throw new ApiException("Teacher or course not found");
        }
        course.setTeacher(teacher);
        courseRepository.save(course);
    }


    public void updateCourse(Integer courseId,CourseDTO courseDTO){
        Course course = courseRepository.findCourseById(courseId);
        if(course==null){
            throw new ApiException("Course not found");
        }
        course.setName(courseDTO.getName());

        courseRepository.save(course);

    }


    public void deleteCourse(Integer id){
        Course course =courseRepository.findCourseById(id);
        if(course==null){
            throw new ApiException("Course not found");
        }
        courseRepository.delete(course);
    }


    public String getTeacherNameByCourseId(Integer courseId){
        Course course =courseRepository.findCourseById(courseId);
        if(course==null){
            throw new ApiException("Course not found");
        }
        TeacherDTO teacherDTO = new TeacherDTO(course.getTeacher().getName(),course.getTeacher().getEmail(),course.getTeacher().getAge(),course.getTeacher().getSalary(),course.getTeacher().getAddress());
       return teacherDTO.getName();
    }

    public List<StudentDTO> getStudentOfCourse(Integer courseId){
        Course course = courseRepository.findCourseById(courseId);
        List<StudentDTO> studentDTOS = new ArrayList<>();

        for (Student student:course.getStudents()){
            StudentDTO studentDTO = new StudentDTO(student.getName(),student.getAge(),student.getMajor());
            studentDTOS.add(studentDTO);
        }
        return studentDTOS;
    }

}
