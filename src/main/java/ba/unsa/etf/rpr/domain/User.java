package ba.unsa.etf.rpr.domain;
import java.util.Objects;

/**
 * Class that contains information about users
 * @author Ilma Husic
 */
public class User implements Idable {
    private int id;
    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private int phone_number;
    private String address;

    /**
     * Construtor with parametres
     * @param id
     * @param firstName
     * @param lastName
     * @param password
     * @param email
     * @param phone_number
     * @param address
     */
    public User(int id,String firstName, String lastName, String password, String email, int phone_number, String address) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
        this.phone_number = phone_number;
        this.address = address;
    }

    /**
     * Default constructor
     */
    public User() {
    }

    public User(int id, String Ilma, String Husic, String project123, String email, String s, String address) {
    }

    /**
     * Setter for id
     * @param id int
     */
    @Override
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Getter for id
     * @return int
     */
    public int getId() {
        return id;
    }

    /**
     * Getter for First name
     * @return String
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Setter for First name
     * @param firstName String
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Getter for Last name
     * @return String
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Setter for Last name
     * @param lastName String
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    /**
     * Getter for address
     * @return String
     */
    public String getAddress() {
        return address;
    }


    /**
     * Setter for address
     * @param address String
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Getter for phone
     * @return int
     */
    public int getPhone_number() {
        return phone_number;
    }

    /**
     * Setter for phone
     * @param phone_number String
     */
    public void setPhone_number(int phone_number) {
        this.phone_number = phone_number;
    }

    /**
     * Getter for email
     * @return String
     */
    public String getEmail() {
        return email;
    }

    /**
     * Setter for email
     * @param email String
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Getter for password
     * @return String
     */
    public String getPassword() {
        return password;
    }

    /**
     * Setter for password
     * @param password String
     */
    public void setPassword(String password) {
        this.password = password;
    }
    @Override
    public String toString() {
        return "User - " +
                "id=" + id +
                "first name=" + firstName +
                "last name=" + lastName +
                "email=" + email +
                "phone_number=" + phone_number +
                "address=" + address +
                '.';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id,firstName,lastName,password,email,phone_number,address);
    }


}

