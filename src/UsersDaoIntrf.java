
public interface UsersDaoIntrf
{
    void createUser(final Users p0);
    
    Users loginUser(final int p0, final String p1);
    
    boolean checkIdUnique(final int p0);
    
    Users Logout();
}
