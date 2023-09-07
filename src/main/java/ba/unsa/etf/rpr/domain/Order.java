package ba.unsa.etf.rpr.domain;
import java.util.Objects;

/**
 * Class that contains information about record orders
 * @author Ilma Husic
 */
public class Order implements Idable {
    private int id;

    private double paymentAmount;

    private String address;

    //private int phone;
    private User user;


    /**
     * Getter for Id
     * @return int
     */
    public int getId() {
        return id;
    }

    /**
     * Setter for Id
     * @param id int
     */
    public void setId(int id) {
        this.id = id;
    }


    /**
     * Getter for Payment
     * @return double
     */
    public double getPayment_amount() {
        return paymentAmount;
    }

    /**
     * Setter for Payment
     * @param paymentAmount double
     */
    public void setPayment_amount(double paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    /**
     * Getter for user
     * @return User
     */
    public User getUser() {
        return user;
    }

    /**
     * Setter for User
     * @param user User
     */
    public void setUser(User user) {
        this.user = user;
    }

    public String getAddress() {
        return address;
    }

    // public void setAddress(String address) {
    //    this.address = address;
    //}

    /**
     * Getter for Phone
     * @return int
     */
    //public int getPhone_number() {
    //   return phone;
    //  }

    /**
     * Setter for Phone
     // * @param phone_number int
     */
    //public void setPhone_number(int phone_number) {
    //   this.phone = phone;
    // }

    @Override
    public String toString() {
        return "Order: " +
                "id=" + id +

                ", payment=" + paymentAmount +
                ", user=" + user +
                '.';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id == order.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, paymentAmount, user);
    }
}
