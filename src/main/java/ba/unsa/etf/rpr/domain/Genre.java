package ba.unsa.etf.rpr.domain;
import java.util.Objects;

/**
 *  Show a list of possible categories for genres
 *
 * @author Semina Muratović
 */
public class Genre implements Idable{
    private int id;
    private String name;


    public Genre() {
    }

    public Genre( String name) {

        this.name = name;
    }

    /**
     * Getter for id
     * @return int
     */
    public int getId()
    {
        return id;
    }

    /**
     * Setter for id
     * @param id int
     */
    public void setId(int id)
    {
        this.id = id;
    }


    /**
     * Getter for name
     * @return string
     */
    public String getName()
    {
        return name;
    }

    /**
     * Setter for name
     * @param name string
     */
    public void setName(String name)
    {
        this.name = name;
    }

    @Override
    public String toString()
    {
        return name;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Genre genre = (Genre) o;
        return id == genre.id;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id, name);
    }
}