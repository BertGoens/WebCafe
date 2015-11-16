package dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

/* @author BertGoens */
public abstract class BaseDao<E, Key extends Serializable> implements Dao<E, Key> {

    private EntityManagerFactory emf;
    @PersistenceContext
    private static EntityManager em;
    
    public EntityManager getEntityManager() {
        return em;
    }

    protected Class entityClass;

    public BaseDao() {
        ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
        this.entityClass = (Class) genericSuperclass.getActualTypeArguments()[1];

        if (em == null) {
            emf = Persistence.createEntityManagerFactory("persistence");
            em = emf.createEntityManager();
        }
    }

    /**
     * Updates (and creates) entities in the database if the object isn't
     * already in persistence context To change fields use em.begin() ->
     * em.commit()
     *
     * @param entity
     */
    @Override
    /// em.merge does not track updates, use persist
    /// Also, just do em.begin() -> em.commit();
    public void update(E entity) {
        if (!em.contains(entity)) {
            em.getTransaction().begin();
            em.persist(entity);
            em.getTransaction().commit();
            em.detach(entity);
        }
    }

    public void merge(E entity) {
        em.getTransaction().begin();
        em.merge(entity);
        em.getTransaction().commit();
    }

    /**
     * Creates object in database
     *
     * @param entity
     */
    @Override
    public void persist(E entity) {
        if (!em.contains(entity)) {
            em.getTransaction().begin();
            em.persist(entity);
            em.getTransaction().commit();
        }
    }

    /**
     * Deletes object from database
     *
     * @param entity
     */
    @Override
    public void delete(E entity) {
        em.getTransaction().begin();
        em.remove(entity);
        em.getTransaction().commit();
    }

    /**
     * Finds an object by its key (Id)
     *
     * @param id
     * @return
     */
    @Override
    public E findById(Key id) {
        return (E) em.find(entityClass, id);
    }

    /**
     * Returns all objects from a table. Watch out, this can turn out huge...
     *
     * @return
     */
    @Override
    public List<E> findAll() {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<E> criteriaQuery = criteriaBuilder.createQuery(entityClass);
        criteriaQuery.from(entityClass);
        TypedQuery<E> typedQuery = em.createQuery(criteriaQuery);
        return typedQuery.getResultList();

        //return em.createQuery("Select t from " + entityClass.getSimpleName() + " t").getResultList();
    }
}
