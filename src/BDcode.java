import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class BDcode {
    private static boolean isNum(String str)
    {
        for (char c : str.toCharArray())
        {
            if (!Character.isDigit(c)) return false;
        }
        return true;
    }
    public static void mySql()
    {
        Scanner in = new Scanner(System.in);
        try{
            String url = "jdbc:mysql://localhost/store?serverTimezone=Europe/Moscow&useSSL=false";
            String username = "root";
            String password = "dionis0799";
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, username, password)){
                Statement statement = conn.createStatement();
                while(true) {
                    System.out.println("Menu.\n1. New employee.\n2. Delete employee.\n3. Change data of employee.\n4. List of employee.\n5. Exit.");
                    String str = in.nextLine();
                    switch (str) {
                        case "1":
                            System.out.print("Enter nickname of employee:");
                            String a = in.nextLine();
                            System.out.print("Enter full name of employee:");
                            String b = in.nextLine();
                            System.out.print("Enter age of employee:");
                            String c = in.nextLine();
                            if (!isNum(c)) {
                                do {
                                    System.out.println("Incorrectly!");
                                    c = in.nextLine();
                                } while (!isNum(c));
                            }
                            statement.executeUpdate("INSERT Customers(Nickname,FullName,Age) VALUES" + "(" + "'" + a + "'" + "," + "'" + b + "'" + "," + c + ")");
                            System.out.println("Employee added in base!");
                            break;
                        case "2":
                            System.out.print("Enter nickname of employee:");
                            String d = in.nextLine();
                            statement.executeUpdate("DELETE FROM Customers WHERE Nickname = " + "'" + d + "'");
                            System.out.println("Employee deleted from base!");
                            break;
                        case "3":
                            System.out.print("Enter nickname of employee:");
                            String o=in.nextLine();
                            System.out.println("1. Changed full name.\n2. Changed age");
                            String i=in.nextLine();
                            switch(i) {
                                case "1":
                                    System.out.print("Enter full name of employee:");
                                    String u=in.nextLine();
                                    statement.executeUpdate("UPDATE `store`.`Customers` SET `FullName` = '" + u + "' WHERE (`Nickname` = '" + o + "');");
                                    System.out.println("Changed!");
                                    break;
                                case "2":
                                    System.out.print("Enter age of employee:");
                                    String y=in.nextLine();
                                    if (!isNum(y)) {
                                        do {
                                            System.out.println("Incorrectly!");
                                            y = in.nextLine();
                                        } while (!isNum(y));
                                    }
                                    statement.executeUpdate("UPDATE `store`.`Customers` SET `Age` = '" + y + "' WHERE (`Nickname` = '" + o + "');");
                                    System.out.println("Changed!");
                                    break;
                            }
                        case "4":
                            System.out.println("List of employee");
                            ResultSet resultSet = statement.executeQuery("SELECT * FROM Customers");
                            while(resultSet.next()) {
                                String id = resultSet.getString("Nickname");
                                String name = resultSet.getString("FullName");
                                int age = resultSet.getInt("Age");
                                System.out.printf("Nickname: %s; Fullname: %s; возраст: %d \n", id, name, age);
                            }
                            break;
                        case "5": System.exit(0);
                        default:
                            System.out.println("Incorrectly!");
                    }
                }
            }
        }
        catch(Exception ex){
            System.out.println("Connection failed...");
            System.out.println(ex);
        }
    }
}
