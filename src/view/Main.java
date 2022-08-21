package view;

import java.util.Random;
import java.util.Scanner;

public class Main {

    Scanner sc = new Scanner(System.in);

    public void menu() {
        System.out.println("Menu");
        System.out.println("1. Classroom Manage");
        System.out.println("2. Student Manage");
        System.out.println("3. Exit");
        int choice = -1;

        try {
            choice = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid choice");
            menu();
        }

        switch (choice) {
            case 1:
                new ClassroomView().menu();
                break;
            case 2:
                new StudentView().menu();
                break;
            case 3:
                System.exit(0);
            default:
                System.out.println("Invalid choice");
        }
        menu();
    }

    public static void main(String[] args) {
        new Main().menu();
    }

}
