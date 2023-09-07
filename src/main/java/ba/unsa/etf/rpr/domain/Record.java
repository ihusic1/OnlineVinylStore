package ba.unsa.etf.rpr.domain;

import java.util.Objects;

/**
 * Class that gives me information about records
 * @author Ilma Husic
 **/
public class Record implements Idable {
    private int id;
    private String title;
    private String artist;
    private double price;
    private Genre genre;

    /**
     * Getter for Id
     * @return int
     */
    public int getId() {
        return id;
    }

    /**
     * Setter for id
     * @param id int
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Getter for title
     * @return String
     */
    public String getTitle() {
        return title;
    }

    /**
     * Setter for title
     * @param title String
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Getter for artist
     * @return String
     */
    public String getArtist() {
        return artist;
    }

    /**
     * Setter for artist
     * @param artist String
     */
    public void setArtist(String artist) {
        this.artist = artist;
    }

    /**
     * Getter for Price
     * @return double
     */
    public double getPrice() {return price;}

    /**
     * Setter for price
     * @param price double
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Getter for Genre
     * @return genre
     */
    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }
    @Override
    public String toString() {
        return "Record - " +
                "id=" + id +
                ", title='" + title + '\'' +
                ", artist=" + artist +
                ", price=" + price +
                ", genre=" + genre +
                '.';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Record record = (Record) o;
        return id == record.id;
    }
    @Override
    public int hashCode() {
        return Objects.hash(id, title, artist, price, genre);
    }



}