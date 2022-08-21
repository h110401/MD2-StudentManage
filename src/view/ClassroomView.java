package view;


import controller.ClassroomController;
import model.Classroom;

import java.util.List;
import java.util.Scanner;

public class ClassroomView {

    ClassroomController classroomController = new ClassroomController();
    Scanner sc = new Scanner(System.in);
    List<Classroom> classroomList = classroomController.getClassroomList();

    public void menu() {
        System.out.println("Menu");
        System.out.println("1. Show list classroom");
        System.out.println("2. Create new classroom");
        System.out.println("3. Edit classroom");
        System.out.println("4. Delete");
        System.out.println("5. Back");

        int choice = 0;

        try {
            choice = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid choice");
            menu();
        }

        switch (choice) {
            case 1:
                this.showListClassroom();
                break;
            case 2:
                this.formCreateClassroom();
                break;
            case 3:
                this.formEditClassroom();
                break;
            case 4:
                this.formDeleteClassroom();
                break;
            case 5:
                return;
            default:
                System.out.println("Invalid choice");
        }
        menu();
    }


    private void formDeleteClassroom() {
        System.out.println("Enter Id classroom to delete");
        int idDelete = -1;
        try {
            idDelete = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Id not valid");
            formDeleteClassroom();
        }
        Classroom classroomDeleted = classroomController.getClassroom(idDelete);
        if (classroomDeleted == null) {
            System.out.println("Classroom not found");
        } else {
            System.out.println("Choose YES deleted or NO not deleted");

            while (true) {
                String inputDelete = sc.nextLine();
                if (inputDelete.equalsIgnoreCase("YES")) {
                    classroomController.deleteClassroom(idDelete);
                    System.out.println("deleted successfully");
                    showListClassroom();
                    break;
                } else if (inputDelete.equalsIgnoreCase("NO")) {
                    System.out.println("not deleted");
                    break;
                } else {
                    System.out.println("Stupid .Do you know YES/NO ?");
                }
            }
        }
    }

    private void formEditClassroom() {
        showListClassroom();
        System.out.println("Enter id classroom to edit");
        int idEdit = -1;

        try {
            idEdit = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Id not valid");
            formEditClassroom();
        }


        Classroom classroomEdit = classroomController.getClassroom(idEdit);
        if (classroomEdit == null) {
            System.out.println("Classroom not found");
        } else {
            System.out.println("Enter new name");
            String newName = "";
            boolean check = true;
            while (check) {
                check = false;
                newName = checkName();
                for (Classroom c : classroomList) {
                    if (newName.equalsIgnoreCase(c.getName())) {
                        System.out.println("Classroom existed");
                        System.out.println("bạn nghĩ ra tên cho lớp chưa nhỉ");
                        check = true;
                    }
                }
            }

            Classroom newClassroom = new Classroom(idEdit, newName);
            classroomController.editClassroom(newClassroom);
            showListClassroom();
        }

    }

    private void formCreateClassroom() {
        System.out.println("Enter classroom name");
        String name = "";
        boolean check = true;
        while (check) {
            check = false;
            name = checkName();
            for (Classroom c : classroomList) {
                if (name.equalsIgnoreCase(c.getName())) {
                    System.out.println("Classroom existed");
                    System.out.println("bạn nghĩ ra tên cho lớp chưa nhỉ");
                    check = true;
                }
            }
        }


        int lastId = classroomList.get(classroomList.size() - 1).getId();
        Classroom classroom = new Classroom(lastId + 1, name);
        classroomController.saveClassroom(classroom);
        showListClassroom();

    }

    private String checkName() {
        String name = "";

        while (!name.matches("[a-zA-Z0-9]+")) {

            name = sc.nextLine();

            if (!name.matches("[a-zA-Z0-9]+")) {
                System.out.println("Invalid name, enter again");
            }

        }
        return name;
    }

    private void showListClassroom() {
        for (Classroom classroom : classroomList) {
            System.out.println(classroom);
        }
    }

}
