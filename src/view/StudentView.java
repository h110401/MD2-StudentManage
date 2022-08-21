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
        System.out.println("3. Edit student");
        System.out.println("4. Delete student");
        System.out.println("5. Search student");
        System.out.println("6. Back");

        int choice = 0;

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
            case 3:
                this.formEditStudent();
            case 4:
                this.formDeleteStudent();
                break;
            case 5:
                this.formSearchStudent();
                break;
            case 6:
                return;
            default:
                System.out.println("Invalid choice");
        }
        menu();

    }

    private void formSearchStudent() {
        System.out.println("Enter student name to search");
        String nameSearch = sc.nextLine();

        for (Student student : studentList) {
            if (student.getName().toLowerCase().contains(nameSearch.toLowerCase())) {
                System.out.println(student);
            }
        }

    }

    private void formDeleteStudent() {
        formShowStudentList();
        System.out.println("Enter student id to delete");

        int idDelete = -1;

        try {
            idDelete = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Id not valid");
            formDeleteStudent();
        }

        Student studentDelete = studentController.getStudent(idDelete);

        if (studentDelete == null) {
            System.out.println("Student not found");
            return;
        }

        studentController.deleteStudent(idDelete);
        formShowStudentList();

    }

    private void formEditStudent() {
        formShowStudentList();
        System.out.println("Enter student id to edit");

        int idEdit = -1;

        try {
            idEdit = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Id not valid");
            formEditStudent();
        }

        Student studentEdit = studentController.getStudent(idEdit);

        if (studentEdit == null) {
            System.out.println("Student not found");
            return;
        }

        System.out.println("Enter new student name");
        String newName = sc.nextLine();
        if (newName.trim().equals("")) {
            newName = studentEdit.getName();
        }
        System.out.println("Enter new student age");
        int newAge;
        String ageString = sc.nextLine();

        if (ageString.trim().equals("")) {
            newAge = studentEdit.getAge();
        } else {
            newAge = Integer.parseInt(ageString);
        }

        List<Classroom> classroomList = classroomController.getClassroomList();
        for (Classroom classroom : classroomList) {
            System.out.println(classroom);
        }

        System.out.println("Enter new student classroom id");

        int newIdClassroom;
        String idString = sc.nextLine();
        if (idString.trim().equals("")) {
            newIdClassroom = studentEdit.getClassroom().getId();
        } else {
            newIdClassroom = Integer.parseInt(idString);
        }
        Student newStudent = new Student(idEdit, newName, newAge, classroomController.getClassroom(newIdClassroom));
        studentController.editStudent(newStudent);
        formShowStudentList();
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
