package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.domain.*;
import ba.unsa.etf.rpr.domain.Record;

public class Model {
    private static Model instance;
    private Record record;
    private User user;
    private Order order;
    private Genre genre;
    private IntermediateTable intermediateTable;

    private Model() {}

    public static Model getInstance() {
        if (instance == null) {
            instance = new Model();
        }
        return instance;
    }

    public Record getRecord() {
        return record;
    }

    public void setMedicine(Record record) {
        this.record = record;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Genre getGenre() {
        return genre;
    }
    public IntermediateTable getIntermediateTable() {
        return intermediateTable;
    }

    public void setIntermediateTable(IntermediateTable intermediateTable) {
        this.intermediateTable = intermediateTable;
    }

}
