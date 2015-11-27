package dao;

import java.util.List;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import model.Event;
import model.User;

/* @author BertGoens */
public class UserDao extends BaseDao<User, Integer> {

    public UserDao() {
        entityClass = User.class;
    }

    public User findByEmail(String mail) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();

        CriteriaQuery<User> qryGetUser = cb.createQuery(User.class);
        Root<User> c = qryGetUser.from(User.class);
        ParameterExpression<String> pwd = cb.parameter(String.class);
        qryGetUser.where(cb.like(c.get("email"), pwd));

        TypedQuery<User> query = getEntityManager().createQuery(qryGetUser)
                .setFirstResult(0)
                .setMaxResults(1);
        query.setParameter(pwd, mail);

        User tryLogInUser = null;
        try {
            tryLogInUser = query.getSingleResult();
        } catch (javax.persistence.NoResultException ex) {
            return null;
        }
        return tryLogInUser;
    }

    public List<Event> getFutureRegistredEvents(User user) {
        String q = "{SELECT * FROM wc_Event WHERE (wc_Event.date > CURRENT_DATE()) "
                + "AND wc_Event.evt_id IN "
                + "( SELECT event_users.evt_id FROM event_users"
                + "WHERE event_users.usr_id = ?1)"
                + "ORDER BY wc_EVent.date}";
        Query query = getEntityManager().createNativeQuery(q, Event.class)
                .setParameter(1, user.getId());
//SELECT * FROM wc_Event WHERE (wc_Event.date > CURRENT_DATE())
        //AND wc_Event.evt_id IN
        //( SELECT event_users.evt_id FROM event_users
        //WHERE event_users.usr_id = 1)
        //ORDER BY Date
        /*Query query = getEntityManager().createNativeQuery(
                "SELECT * FROM wc_Event WHERE (wc_Event.date > CURRENT_DATE()) AND wc_Event.evt_id IN "
                + "( SELECT event_users.evt_id FROM event_users HERE event_users.usr_id = 1)"
                + "ORDER BY Date", Event.class);
        query.setParameter(1, user.getId());

        return query.getResultList();
         */
 /*Query query = getEntityManager().createNativeQuery("{call getRegisteredFutureEvents(?)}",
                Event.class)
                .setParameter(1, user.getId());
*/
        List<Event> result = query.getResultList();
        return result;

    }

    public List<Event> getCreatorEvents(User user) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();

        CriteriaQuery<Event> qryFindRegistredEventsFromUser = cb.createQuery(Event.class);
        Root<Event> c = qryFindRegistredEventsFromUser.from(Event.class);
        ParameterExpression<Integer> userId = cb.parameter(Integer.class);
        qryFindRegistredEventsFromUser.where(cb.equal(c.get("usr_id"), userId));

        TypedQuery<Event> query = getEntityManager().createQuery(qryFindRegistredEventsFromUser);
        query.setParameter(userId, user.getId());

        return query.getResultList();
    }

    public boolean loginUserCorrect(String email, String password) {
        User tryLogInUser = findByEmail(email);

        if (tryLogInUser != null) {
            return tryLogInUser.getPassword().equals(password);
        }

        return false;
    }
}
