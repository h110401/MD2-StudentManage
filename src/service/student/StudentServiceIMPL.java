package service.student;

import config.Config;
import model.Classroom;
import model.Student;
import service.classroom.ClassroomServiceIMPL;

import java.util.ArrayList;
import java.util.List;

public class StudentServiceIMPL implements IStudentService {

    static String PATH = "src/data/studentList.txt";
    static Config<List<Student>> config = new Config<>();
    static List<Student> studentList = config.read(PATH);

//    static {
//        List<Classroom> classroomList = new ClassroomServiceIMPL().findAll();
//        studentList.add(new Student(1, "Hung", 19, classroomList.get(0)));
//        studentList.add(new Student(2, "Khanh", 18, classroomList.get(1)));
//
//        config.write(studentList, PATH);
//
//    }

    @Override
    public List<Student> findAll() {
        return studentList;
    }

    @Override
    public void save(Student student) {
        studentList.add(student);
        config.write(studentList, PATH);
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
        Student removeStudent = findById(id);
        studentList.remove(removeStudent);
        config.write(studentList, PATH);
    }

    public void edit(Student student) {
        Student studentEdit = findById(student.getId());
        studentEdit.setName(student.getName());
        studentEdit.setAge(student.getAge());
        studentEdit.setClassroom(student.getClassroom());
        config.write(studentList, PATH);
    }
}
