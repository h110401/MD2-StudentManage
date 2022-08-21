package controller;

import model.Student;
import service.student.IStudentService;
import service.student.StudentServiceIMPL;

import java.util.List;

public class StudentController {

    IStudentService studentService = new StudentServiceIMPL();

    public List<Student> getStudentList() {
        return studentService.findAll();
    }

    public void createStudent(Student student) {
        studentService.save(student);
    }

}
