package ba.unsa.etf.rpr.business;

import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.domain.IntermediateTable;
import ba.unsa.etf.rpr.domain.Record;
import ba.unsa.etf.rpr.exceptions.RecordException;

import java.util.List;

public class IntermediateManager {
    public IntermediateTable add(IntermediateTable i) throws RecordException
    {
        return DaoFactory.intermediateDao().add(i);
    }
    public List<IntermediateTable> getAll() throws RecordException {
        return DaoFactory.intermediateDao().getAll();
    }

    public List<IntermediateTable> getByUser(int idUser) throws RecordException {
        return DaoFactory.intermediateDao().getByUser(idUser);
    }

}