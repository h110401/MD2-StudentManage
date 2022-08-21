package service.student;

import model.Classroom;
import model.Student;
import service.classroom.ClassroomServiceIMPL;
import service.classroom.IClassroomService;

import java.util.ArrayList;
import java.util.List;

public class StudentServiceIMPL implements IStudentService {

    static List<Student> studentList = new ArrayList<>();

    static {
        List<Classroom> classroomList = new ClassroomServiceIMPL().findAll();
        studentList.add(new Student(1, "Hung", 19, classroomList.get(0)));
        studentList.add(new Student(2, "Khanh", 18, classroomList.get(1)));
    }

    @Override
    public List<Student> findAll() {
        return studentList;
    }

    @Override
    public void save(Student student) {
        studentList.add(student);
    }

    @Override
    public Student findById(int id) {
        for (Student student : studentList) {
            if (student.getId() == id) return student;
        }
        return null;
    }

    @Override
    public void remove(int id) {
        studentList.remove(findById(id));
    }
}
