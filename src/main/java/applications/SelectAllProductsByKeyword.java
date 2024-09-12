package applications;

import model.Product;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class SelectAllProductsByKeyword {

    public static void main(String[] args) {
        ArrayList<Product> result = new ArrayList<Product>();
        String inputKeyword = "";
        Scanner sc = new Scanner(System.in);

        try {
            System.out.println("Enter product keyword: ");
            inputKeyword = sc.nextLine();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String dbURL = "jdbc:mysql://localhost:3306/classicmodels";
            String username = "root";
            String password = "mysql";

            try {
                Connection conn = DriverManager.getConnection(dbURL, username, password);

                try (PreparedStatement ps = conn.prepareStatement("select * from products where productName like ? ")) {

                    ps.setString(1, "%" + inputKeyword+ "%");

                    try (ResultSet rs = ps.executeQuery()){
                        while (rs.next()) {
                            Product pr = new Product(
                                    rs.getString("productCode"),
                                    rs.getString("productName"),
                                    rs.getString("productLine"),
                                    rs.getString("productScale"),
                                    rs.getString("productVendor"),
                                    rs.getString("productDescription"),
                                    rs.getInt("quantityInStock"),
                                    rs.getDouble("buyPrice"),
                                    rs.getDouble("MSRP")
                            );

                            result.add(pr);

                        }
                    }
                }

            } catch (SQLException e){
                System.out.println(LocalDateTime.now() + "An SQLException occurred while trying to connect to the " + dbURL);
                System.out.println("Error " + e.getMessage());
                e.printStackTrace();
            }


        }catch (ClassNotFoundException e){
            System.out.println("ClassNotFoundException occurred while trying to load the MySQL driver");
            System.out.println("Error " + e.getMessage());
            e.printStackTrace();
        }

        for (Product product : result) {
            System.out.println(product);
        }

    }

}
