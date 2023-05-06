import java.sql.DriverManager;
import java.sql.Connection;



public class DBConnection
{
    static Connection con;
    
    public static Connection createDBConnetion() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            final String url = "jdbc:mysql://localhost:3306/socialmediaappdb?useSSL=false";
            final String username = "root";
            final String password = "Tiga158158";
            DBConnection.con = DriverManager.getConnection(url, username, password);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return DBConnection.con;
    }
}
