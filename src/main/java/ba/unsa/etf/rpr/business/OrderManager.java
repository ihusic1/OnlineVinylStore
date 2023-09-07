package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.Record;
import ba.unsa.etf.rpr.domain.Order;
import ba.unsa.etf.rpr.exceptions.RecordException;

import java.util.List;

public class OrderManager {

    public Order add(Order o) throws RecordException
    {
        return DaoFactory.orderDao().add(o);
    }

    public List<Order> getAll() throws RecordException
    {
        return DaoFactory.orderDao().getAll();
    }

}
