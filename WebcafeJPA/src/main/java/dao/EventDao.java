package dao;

import java.util.List;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;
import model.Event;

/* @author BertGoens */
public class EventDao extends BaseDao<Event, Integer> {

    public EventDao() {
        entityClass = Event.class;
    }

    public Event findByName(String name) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();

        CriteriaQuery<Event> qryFindEventByName = cb.createQuery(Event.class);
        Root<Event> c = qryFindEventByName.from(Event.class);
        ParameterExpression<String> evtName = cb.parameter(String.class);
        qryFindEventByName.where(cb.equal(c.get("name"), evtName));
        TypedQuery<Event> query = getEntityManager().createQuery(qryFindEventByName);
        query.setParameter(evtName, name);

        List<Event> results = query.getResultList();
        if (!results.isEmpty()) {
            return results.get(0);
        }
        return null;
    }

    public Event findByLocation(String location) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();

        CriteriaQuery<Event> qryFindEventByLocation = cb.createQuery(Event.class);
        Root<Event> c = qryFindEventByLocation.from(Event.class);
        ParameterExpression<String> evtLocation = cb.parameter(String.class);
        qryFindEventByLocation.where(cb.equal(c.get("location"), evtLocation));
        TypedQuery<Event> query = getEntityManager().createQuery(qryFindEventByLocation);
        query.setParameter(evtLocation, location);

        List<Event> results = query.getResultList();
        if (!results.isEmpty()) {
            return results.get(0);
        }
        return null;
    }
}
