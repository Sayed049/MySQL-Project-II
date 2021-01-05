package mysqlproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class main {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Scanner sc = new Scanner(System.in);
        int choice = 0;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            try ( Connection con = DriverManager.getConnection("jdbc:mysql://localhost/employee?", "root", "")) {
                System.out.println("XAMPP MySQL Server is Connected...");
                System.out.println("");
                Statement st = con.createStatement();
                do {
                    System.out.println("Employee Management Server Menu");
                    System.out.println("_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _");
                    System.out.println("Press '1' For Creating The Values in The Database");
                    System.out.println("");
                    System.out.println("Press '2' For Reading The Values in The Database");
                    System.out.println("");
                    System.out.println("Press '3' For Updating The Values in The Database");
                    System.out.println("");
                    System.out.println("Press '4' For Deleting The Values in The Database");
                    System.out.println("");
                    System.out.println("Press '5' For Exit The Database");
                    System.out.println("");
                    System.out.print("Enter Your Choice : ");
                    choice = sc.nextInt();
                    switch (choice) {
                        case 1:
                            try {
                            System.out.println("Enter Employee ID : ");
                            int id = sc.nextInt();
                            System.out.println("Enter Employee Name : ");
                            String name = sc.next();
                            System.out.println("Enter Employee Age : ");
                            int age = sc.nextInt();
                            System.out.println("Enter Employee Salary : ");
                            int salary = sc.nextInt();
                            String sql = "insert into empinfo values (?,?,?,?);";
                            PreparedStatement stmt = con.prepareStatement(sql);
                            stmt.setInt(1, id);
                            stmt.setString(2, name);
                            stmt.setInt(3, age);
                            stmt.setInt(4, salary);
                            stmt.execute();
                            System.out.println("Employee Details Inserted Successfully...");
                            System.out.println("");
                        } catch (SQLException e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                        case 2:
                            try {
                            System.out.println("Enter Employee ID For Read The Record : ");
                            Scanner input = new Scanner(System.in);
                            int id = input.nextInt();
                            String sql = "select * from empinfo where id=?;";
                            PreparedStatement stmt = con.prepareStatement(sql);
                            stmt.setInt(1, id);
                            ResultSet rs = stmt.executeQuery();
                            while (rs.next()) {

                                System.out.println("Employee ID = " + rs.getInt(1));
                                System.out.println("Employee Name = " + rs.getString(2));
                                System.out.println("Employee Age = " + rs.getInt(3));
                                System.out.println("Employee Salary = " + rs.getInt(4));
                            }
                        } catch (SQLException e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                        case 3:
                            try {
                            System.out.println("Enter Employee Name : ");
                            String name = sc.next();
                            System.out.println("Enter Employee New Salary : ");
                            int salary = sc.nextInt();
                            String sql = "update empinfo set salary = ? where name = ?";
                            PreparedStatement statement = con.prepareStatement(sql);
                            statement.setInt(1, salary);
                            statement.setString(2, name);
                            int rowsUpdated = statement.executeUpdate();
                            if (rowsUpdated > 0) {
                                System.out.println("Record Updated Successfully...");
                            } else {
                                System.out.println("There is No Such Record Found in The Database");
                            }
                        } catch (SQLException e) {
                            System.out.println(e.getMessage());
                        }
                        break;

                        case 4:
                            try {
                            System.out.println("Enter Employee Name for Deleting the Record : ");
                            String name = sc.next();
                            String sql = "delete from empinfo where name = ?;";
                            PreparedStatement statement = con.prepareStatement(sql);
                            statement.setString(1, name);
                            int rowsUpdated = statement.executeUpdate();
                            if (rowsUpdated > 0) {
                                System.out.println("Record Deleted Successfully...");
                            } else {
                                System.out.println("There is No Such Record Found in The Database");
                            }
                        } catch (SQLException e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                        case 5:
                            System.out.println("Exiting The Database..");
                            break;
                        default:
                            System.out.println("Please Select The Correct Option");
                            break;
                    }

                } while (choice != 5);
                System.out.println("Thanks For Using The Database..");
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(jdbc.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}