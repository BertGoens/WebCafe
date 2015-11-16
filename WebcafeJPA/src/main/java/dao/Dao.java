package dao;

import java.io.Serializable;
import java.util.List;

/* @author BertGoens */
public interface Dao<E, Key extends Serializable> {

    public void persist(E entity);

    public void update(E entity);

    public void delete(E entity);

    E findById(Key id);

    public List<E> findAll();
}
