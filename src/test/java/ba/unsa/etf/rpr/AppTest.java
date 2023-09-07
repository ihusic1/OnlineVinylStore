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
}