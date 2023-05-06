import java.util.Scanner;



public class Main
{
    public static void main(String[] args) {
        System.out.println("Welcome to Social Media Application");
        final Scanner sc = new Scanner(System.in);
        final UsersDaoIntrf dao = (UsersDaoIntrf)new UsersDaoImpl();
        Users currentUser = null;
        while (true) {
            System.out.println("1. Signup\n2. Login\n3. Logout\n4. Exit\n");
            System.out.print("Enter Choice: ");
            final int ch = sc.nextInt();
            switch (ch) {
                case 1: {
                    final Users user = new Users();
                    System.out.print("Enter ID: ");
                    int id;
                    for (id = sc.nextInt(); dao.checkIdUnique(id); id = sc.nextInt()) {
                        System.out.print("The ID you have selected is already in use. Please choose a different ID.: ");
                    }
                    System.out.print("Enter username: ");
                    final String username = sc.next();
                    System.out.print("Enter Password: ");
                    final String password = sc.next();
                    System.out.print("Enter Email: ");
                    final String email = sc.next();
                    user.setId(Integer.valueOf(id));
                    user.setUsername(username);
                    user.setPassword(password);
                    user.setEmail(email);
                    System.out.println("\n---------------------------------------");
                    dao.createUser(user);
                    System.out.println("---------------------------------------\n");
                    continue;
                }
                case 2: {
                    if (currentUser != null) {
                        System.out.println("You are already logined as "+ currentUser.getUsername());
                        continue;
                    }
                    System.out.print("Enter id: ");
                    final int loginId = sc.nextInt();
                    System.out.print("Enter Password: ");
                    final String loginPassword = sc.next();
                    System.out.println("\n---------------------------------------");
                    final Users userLoggedIn = dao.loginUser(loginId, loginPassword);
                    if (userLoggedIn == null) {
                        System.out.println("Invalid username or password. Please try again.");
                    }
                    else {
                        currentUser = userLoggedIn;
                        System.out.println("Welcome "+ currentUser.getUsername());
                    }
                    System.out.println("---------------------------------------\n");
                    continue;
                }
                case 3: {
                    currentUser = dao.Logout();
                    continue;
                }
                case 4: {
                    System.out.println("Thank you for using our Application!");
                    sc.close();
                    System.exit(0);
                    break;
                }
            }
            System.out.println("Enter valid choice!");
        }
    }
}
