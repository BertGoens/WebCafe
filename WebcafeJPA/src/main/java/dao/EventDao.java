package dao;

import java.util.List;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
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

    /**
     * The closest event
     * @return
     */
    public Event getNextEvent() {
        //SELECT * FROM Event WHERE (date > CURRENT_DATE()) ORDER BY date ASC LIMIT 1
        // MySQL date = 'YYYY-MM-DD'

        String qryGetNextEvent = "SELECT * FROM Event WHERE (date > CURRENT_DATE()) ORDER BY date ASC LIMIT 1";
        Query query = getEntityManager().createQuery(qryGetNextEvent);

        Object resultEvent = query.getResultList();

        return (Event) resultEvent;
    }

    /**
     * List of all coming events, sorted on time.
     * Closest events first
     * @return
     */
    public List<Event> getComingEvents() {
        String qryGetComingEvents = "SELECT * FROM Event WHERE (date > CURRENT_DATE()) ORDER BY date";
        Query query = getEntityManager().createQuery(qryGetComingEvents);

        List<Event> resultEvent = query.getResultList();

        return resultEvent;
    }

    /**
     * List of all past events, sorted on time.
     * Closest events first
     * @return
     */
    public List<Event> getPastEvents() {
        String qryGetPastEvents = "SELECT * FROM Event WHERE (date < CURRENT_DATE()) ORDER BY date DESC";
        Query query = getEntityManager().createQuery(qryGetPastEvents);

        List<Event> resultEvent = query.getResultList();

        return resultEvent;
    }
}
