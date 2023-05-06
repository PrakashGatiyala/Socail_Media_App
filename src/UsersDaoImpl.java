import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Connection;



public class UsersDaoImpl implements UsersDaoIntrf
{
    private Connection con;
    private Users currentUser;
    
    public UsersDaoImpl() {
        this.con = DBConnection.createDBConnetion();
    }
    
    @Override
    public void createUser(final Users users) {
        final String query = "INSERT INTO users (id,username, password, email) VALUES (?,?, ?, ?)";
        try {
            final PreparedStatement pstm = this.con.prepareStatement(query);
            try {
                pstm.setInt(1, users.getId());
                pstm.setString(2, users.getUsername());
                pstm.setString(3, users.getPassword());
                pstm.setString(4, users.getEmail());
                final int cnt = pstm.executeUpdate();
                if (cnt != 0) {
                    System.out.println("Account Created Successfully !!!");
                }
                if (pstm != null) {
                    pstm.close();
                }
            }
            catch (Throwable t) {
                if (pstm != null) {
                    try {
                        pstm.close();
                    }
                    catch (Throwable exception) {
                        t.addSuppressed(exception);
                    }
                }
                throw t;
            }
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    @Override
    public Users loginUser(final int loginID, final String loginPassword) {
        final String query = "SELECT * FROM users WHERE id = ? AND password = ?";
        try {
            final PreparedStatement pstm = this.con.prepareStatement(query);
            try {
                pstm.setInt(1, loginID);
                pstm.setString(2, loginPassword);
                final ResultSet result = pstm.executeQuery();
                if (result.next()) {
                    this.currentUser = new Users(result.getInt("id"), result.getString("username"), result.getString("password"), result.getString("email"));
                    System.out.println("Login Success");
                    final Users currentUser = this.currentUser;
                    if (pstm != null) {
                        pstm.close();
                    }
                    return currentUser;
                }
                final Users users = null;
                if (pstm != null) {
                    pstm.close();
                }
                return users;
            }
            catch (Throwable t) {
                if (pstm != null) {
                    try {
                        pstm.close();
                    }
                    catch (Throwable exception) {
                        t.addSuppressed(exception);
                    }
                }
                throw t;
            }
        }
        catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    @Override
    public boolean checkIdUnique(final int id) {
        final String query = "SELECT * FROM users WHERE id = ?";
        try {
            final PreparedStatement pstm = this.con.prepareStatement(query);
            try {
                pstm.setInt(1, id);
                final ResultSet result = pstm.executeQuery();
                if (result.next()) {
                    final boolean b = true;
                    if (pstm != null) {
                        pstm.close();
                    }
                    return b;
                }
                final boolean b2 = false;
                if (pstm != null) {
                    pstm.close();
                }
                return b2;
            }
            catch (Throwable t) {
                if (pstm != null) {
                    try {
                        pstm.close();
                    }
                    catch (Throwable exception) {
                        t.addSuppressed(exception);
                    }
                }
                throw t;
            }
        }
        catch (SQLException ex) {
            ex.printStackTrace();
            return true;
        }
    }
    
    public boolean isLoggedIn() {
        return this.currentUser != null;
    }
    
    public Users getCurrentUser() {
        return this.currentUser;
    }
    
    @Override
    public Users Logout() {
        System.out.println("You have been logged out");
        return null;
    }
}
