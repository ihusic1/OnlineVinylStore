package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.business.GenreManager;
import ba.unsa.etf.rpr.business.UserManager;
import ba.unsa.etf.rpr.controllers.*;
import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.dao.UserDao;
import ba.unsa.etf.rpr.dao.UserDaoSQLImpl;
import ba.unsa.etf.rpr.domain.Genre;
import ba.unsa.etf.rpr.domain.Record;
import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.exceptions.RecordException;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static ba.unsa.etf.rpr.controllers.RecordsController.record;
import static org.mockito.Mockito.when;

/**
 * *Tests for the App OnlineRecordStore
 * @author Ilma Husic
 */

public class AppTest {

    /**
     * Tests if setters and getters in 'Record' are valid
     */
    @Test
    public void getTitle() {
        record.setTitle("Record A");
        assertEquals("Record A", record.getTitle());
    }

    @Test
    public void getPrice() {
        record.setPrice(10);
        assertEquals(10, record.getPrice());
    }

    @Test
    public void equals() {
        record.setId(1);
        Record otherRecord = new Record();
        otherRecord.setId(1);
        assertTrue(record.equals(otherRecord));
    }

    //test za user konstruktor
    @Test
    public void testUserConstructor() {
        User user = new User(1, "Ilma", "Husic", "project123", "ihusic1@etf.unsa.ba", 61509381, "Rate Dugonjica 182");
        assertEquals(1, user.getId());
        assertEquals("Ilma", user.getFirstName());
        assertEquals("Husic", user.getLastName());
        assertEquals("project123", user.getPassword());
        assertEquals("ihusic1@etf.unsa.ba", user.getEmail());
        assertEquals(61509381, user.getPhone_number());
        assertEquals("Rate Dugonjica 182", user.getAddress());
    }


    @Test
    public void testUserSetterGetter() {
        User user = new User();
        user.setId(2);
        user.setFirstName("Ajla");
        user.setLastName("Dzinic");
        user.setAddress("Hadzici 22");
        user.setPhone_number(62345678);
        user.setEmail("adzinic1@etf.unsa.ba");
        user.setPassword("rpr123");
        assertEquals(2, user.getId());
        assertEquals("Ajla", user.getFirstName());
        assertEquals("Dzinic", user.getLastName());
        assertEquals("Hadzici 22", user.getAddress());
        assertEquals(62345678, user.getPhone_number());
        assertEquals("adzinic1@etf.unsa.ba", user.getEmail());
        assertEquals("rpr123", user.getPassword());
    }


    @Test
    public void validateGenreName() throws RecordException {
        // Test for a valid genre name
        GenreManager genreManager = new GenreManager();
        genreManager.validateGenreName("Blues");
        // Test for a name that is too short
        try {
            genreManager.validateGenreName("B");
            fail("Expected RecordException was not thrown");
        } catch (RecordException e) {
            assertEquals("Genre name must be between 3 and 50 chars", e.getMessage());
        }
    }
}