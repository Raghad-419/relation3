package com.example.school.Controller;

import com.example.school.ApiResponse.ApiResponse;
import com.example.school.DTO.StudentDTO;
import com.example.school.Model.Student;
import com.example.school.Service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/student")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;

    @GetMapping("/get")
    public ResponseEntity getStudent(){
        return ResponseEntity.status(200).body(studentService.getStudents());
    }

    @PostMapping("/add")
    public ResponseEntity addStudent(@RequestBody @Valid StudentDTO studentDTO){
        studentService.addStudent(studentDTO);
        return ResponseEntity.status(200).body(new ApiResponse("Student added"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updatedStudent(@PathVariable Integer id,@RequestBody @Valid StudentDTO studentDTO){
        studentService.updateStudent(id,studentDTO);
        return ResponseEntity.status(200).body(new ApiResponse("Student updated"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteStudent(@PathVariable Integer id){
        studentService.deleteStudent(id);
        return ResponseEntity.status(200).body(new ApiResponse("Student deleted"));
    }

@PutMapping("/changeMajor/{id}/{major}")
    public ResponseEntity changeStudentMajor(@PathVariable Integer id,@PathVariable String major){
        studentService.changeStudentMajor(id,major);
        return ResponseEntity.status(200).body(new ApiResponse("Student major changed"));
    }

    @PutMapping("/assignStudentToCourse/{studentId}/{courseId}")
public ResponseEntity assignStudentToCourse(@PathVariable Integer studentId,@PathVariable Integer courseId){
        studentService.assignStudentToCourse(studentId,courseId);
        return ResponseEntity.status(200).body(new ApiResponse("Student assign to course"));
}

}
