
package com.example.employee;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        EmployeeDAO dao = new EmployeeDAO();

        int choice = 0;

        while (choice != 5) {

            System.out.println("\n===== Employee Management System =====");
            System.out.println("1. Add Employee");
            System.out.println("2. Search by Department");
            System.out.println("3. Update Salary");
            System.out.println("4. Delete Employee");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            try {
                choice = Integer.parseInt(sc.nextLine().trim());

                switch (choice) {

                    case 1:
                        System.out.print("Enter ID: ");
                        int id = Integer.parseInt(sc.nextLine().trim());

                        System.out.print("Enter Name: ");
                        String name = sc.nextLine().trim();

                        System.out.print("Enter Department: ");
                        String dept = sc.nextLine().trim();

                        System.out.print("Enter Salary: ");
                        double salary =
                                Double.parseDouble(sc.nextLine().trim());

                        if (name.isEmpty() || dept.isEmpty() || salary < 0) {
                            System.out.println(
                                    "Name and department are required; salary cannot be negative.");
                            break;
                        }

                        Employee emp = new Employee(id, name, dept, salary);
                        dao.addEmployee(emp);
                        break;

                    case 2:
                        System.out.print("Enter Department: ");
                        String searchDept = sc.nextLine().trim();
                        dao.getByDept(searchDept);
                        break;

                    case 3:
                        System.out.print("Enter Employee ID: ");
                        int updateId =
                                Integer.parseInt(sc.nextLine().trim());

                        System.out.print("Enter New Salary: ");
                        double newSalary =
                                Double.parseDouble(sc.nextLine().trim());

                        if (newSalary < 0) {
                            System.out.println(
                                    "Salary cannot be negative.");
                            break;
                        }

                        dao.updateSalary(updateId, newSalary);
                        break;

                    case 4:
                        System.out.print("Enter Employee ID to delete: ");
                        int deleteId =
                                Integer.parseInt(sc.nextLine().trim());

                        dao.deleteEmployee(deleteId);
                        break;

                    case 5:
                        System.out.println("Exiting Employee Management System.");
                        break;

                    default:
                        System.out.println("Invalid choice. Enter 1 to 5.");
                }

            } catch (NumberFormatException e) {
                System.out.println("Please enter valid numeric values.");

            } catch (SQLException e) {
                System.out.println("Database error: " + e.getMessage());
            }
        }

        sc.close();
    }
}
