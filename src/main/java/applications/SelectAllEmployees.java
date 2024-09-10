package applications;

import model.Employee;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class SelectAllEmployees {

    public static void main(String[] args) {


        ArrayList<Employee> employees = new ArrayList<Employee>();


        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String dbURL = "jdbc:mysql://localhost:3306/classicmodels";
            String username = "root";
            String password = "mysql";

            try {
                Connection conn = DriverManager.getConnection(dbURL, username, password);

                try (PreparedStatement ps = conn.prepareStatement("select * from employees")) {
                    try (ResultSet rs = ps.executeQuery()){
                        while (rs.next()) {
                            Employee emp = new Employee(
                            rs.getInt("employeeNumber"),
                            rs.getString("lastName"),
                            rs.getString("firstName"),
                            rs.getString("extension"),
                            rs.getString("email"),
                            rs.getString("officeCode"),
                            rs.getInt("reportsTo"),
                            rs.getString("jobTitle")
                            );

                            employees.add(emp);

                        }
                    }
                }

            } catch (SQLException e){
                System.out.println(LocalDateTime.now() + "An SQLException occurred while trying to connect to the " + dbURL);
                System.out.println("Error " + e.getMessage());
                e.printStackTrace();
            }


        }catch (ClassNotFoundException e){
            System.out.println("ClassNotFoundException occured while trying to load the MySQL driver");
            System.out.println("Error " + e.getMessage());
            e.printStackTrace();
        }

        for (Employee employee : employees) {
            System.out.println(employee);
        }
    }

}
