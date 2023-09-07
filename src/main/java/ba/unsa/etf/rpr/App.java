package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.business.UserManager;
import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exceptions.RecordException;

import java.util.Scanner;


public class App
{
    public static void main( String[] args ) throws RecordException
    {
        System.out.println( "Welcome to my little record store!");
        System.out.println("If you don't have a account type 1, if you already have one type 2.");
        Scanner scanner = new Scanner(System.in);
        int type = scanner.nextInt();
        if (type == 2) {
            String email;
            String password;
            System.out.println("Email: ");
            Scanner scanner1 = new Scanner(System.in);
            email = scanner1.next();
            System.out.println("Password: ");
            password = scanner1.next();

            User user = DaoFactory.userDao().findEmail(email);
            while (true) {
                if (user != null && user.getPassword().equals(password)) {
                    System.out.println("Login successful\n");
                    break;
                }
                System.out.println("Invalid username or password.");

                System.out.println("Email: ");
                Scanner scanner2 = new Scanner(System.in);
                email = scanner2.next();
                System.out.println("Password: ");
                password = scanner2.next();
                user = DaoFactory.userDao().findEmail(email);

            }
        }
        else  if (type == 1) {
            try {
                String firstName;
                String lastName;
                String password;
                String email;
                String phone_number;
                String address;

                System.out.println("First Name: ");
                Scanner firstNameSc = new Scanner(System.in);
                firstName = firstNameSc.next();
                System.out.println("Last Name: ");
                Scanner lastNameSc = new Scanner(System.in);
                lastName = lastNameSc.next();
                System.out.println("Password: ");
                Scanner passwordSc = new Scanner(System.in);
                password = passwordSc.next();
                System.out.println("E-mail: ");
                Scanner emailSc = new Scanner(System.in);
                email = emailSc.next();
                System.out.println("Phone number: ");
                Scanner phone_numberSc = new Scanner(System.in);
                phone_number = phone_numberSc.next();
                System.out.println("Address: ");
                Scanner addressSc = new Scanner(System.in);
                address = addressSc.next();
                User user = new User();
                user.setFirstName(firstName);
                user.setLastName(lastName);
                user.setPassword(password);
                user.setEmail(email);
                user.setPhone_number(Integer.parseInt(phone_number));
                user.setAddress(address);
                UserManager.add(user);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}