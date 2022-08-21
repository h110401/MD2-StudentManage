package view;

import controller.ClassroomController;
import controller.StudentController;
import model.Classroom;
import model.Student;

import java.util.List;
import java.util.Scanner;

public class StudentView {

    Scanner sc = new Scanner(System.in);
    StudentController studentController = new StudentController();
    ClassroomController classroomController = new ClassroomController();
    List<Student> studentList = studentController.getStudentList();

    public void menu() {
        System.out.println("Menu");
        System.out.println("1. Show Student List");
        System.out.println("2. Create student");

        int choice = -1;

        try {
            choice = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid choice");
            menu();
        }

        switch (choice) {
            case 1:
                this.formShowStudentList();
                break;
            case 2:
                this.formCreateStudent();
                break;
            default:
                System.out.println("Invalid choice");
        }
        menu();

    }

    private void formCreateStudent() {
        System.out.println("Enter student name");
        String name = sc.nextLine();
        System.out.println("Enter student age");
        int age = Integer.parseInt(sc.nextLine());
        System.out.println("Choose classroom");

        List<Classroom> classroomList = classroomController.getClassroomList();
        for (Classroom classroom : classroomList) {
            System.out.println(classroom);
        }

        int idClassroom = Integer.parseInt(sc.nextLine());

        int lastId = studentList.get(studentList.size() - 1).getId();

        Classroom classroomCreate = classroomController.getClassroom(idClassroom);

        Student studentCreate = new Student(lastId + 1, name, age, classroomCreate);

        studentController.createStudent(studentCreate);
    }

    private void formShowStudentList() {
        for (Student student : studentList) {
            System.out.println(student);
        }
    }

}
