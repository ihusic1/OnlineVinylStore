package ba.unsa.etf.rpr.domain;

/**
 *  This class is Intermediate table - one record can be in more than one order, and one order can contain more than one record
 * @author Ilma Husic
 **/
public class IntermediateTable implements Idable {



    private int id;
    private Record record;
    private Order order;


    /**
     * This class provides getters and setters for accessing and modifying the record and order associated with the intermediate table entry.
     */
    public Record getRecord() {
        return record;
    }

    public void setRecord(Record record) {
        this.record = record;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }


    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

}
