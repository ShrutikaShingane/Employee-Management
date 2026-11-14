
package com.example.employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeDAO {

    // Add employee
    public void addEmployee(Employee emp) throws SQLException {

        String sql = "INSERT INTO Employee (id, name, dept, salary) VALUES (?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, emp.getId());
            ps.setString(2, emp.getName());
            ps.setString(3, emp.getDept());
            ps.setDouble(4, emp.getSalary());

            ps.executeUpdate();
            System.out.println("Employee added successfully!");
        }
    }

    // Search employees by department
    public void getByDept(String dept) throws SQLException {

        String sql = "SELECT * FROM Employee WHERE dept = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, dept);

            try (ResultSet rs = ps.executeQuery()) {

                boolean found = false;

                while (rs.next()) {
                    found = true;

                    System.out.println(
                        "ID: " + rs.getInt("id")
                        + ", Name: " + rs.getString("name")
                        + ", Department: " + rs.getString("dept")
                        + ", Salary: " + rs.getDouble("salary")
                    );
                }

                if (!found) {
                    System.out.println("No employees found.");
                }
            }
        }
    }

    // Update employee salary
    public void updateSalary(int id, double salary)
            throws SQLException {

        String sql = "UPDATE Employee SET salary = ? WHERE id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setDouble(1, salary);
            ps.setInt(2, id);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Salary updated successfully!");
            } else {
                System.out.println("Employee ID not found.");
            }
        }
    }

    // Delete employee
    public void deleteEmployee(int id) throws SQLException {

        String sql = "DELETE FROM Employee WHERE id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Employee deleted successfully!");
            } else {
                System.out.println("Employee ID not found.");
            }
        }
    }
}
