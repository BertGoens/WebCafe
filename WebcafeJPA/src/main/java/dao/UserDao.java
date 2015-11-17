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

        CriteriaQuery<User> qryFindUserByEmail = cb.createQuery(User.class);
        Root<User> c = qryFindUserByEmail.from(User.class);
        ParameterExpression<String> email = cb.parameter(String.class);
        qryFindUserByEmail.where(cb.equal(c.get("email"), email));

        TypedQuery<User> query = getEntityManager().createQuery(qryFindUserByEmail);
        query.setParameter(email, mail);

        List<User> results = query.getResultList();
        if (!results.isEmpty()) {
            return results.get(0);
        }
        return null;
    }

    public List<Event> getRegistredEvents(User user) {
        Query query = getEntityManager().createQuery(
                "SELECT e FROM Event e WHERE Event.evt_id IN "
                + "(SELECT event_users.usr_id FROM `event_users` WHERE `usr_id` = ?1)");
        query.setParameter(1, user.getId());

        return query.getResultList();
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

    public void UnregisterEvent(User user, Event event) {
        Query query = getEntityManager().createQuery(
                "DELETE FROM `event_users`\n"
                + "WHERE event_users.evt_id = ?1 "
                + "AND event_users.evt_id = ?2");
        query.setParameter(1, user.getId());
        query.setParameter(2, event.getId());

        query.executeUpdate();
    }

    public void RegisterEvent(User user, Event event) {
        Query query = getEntityManager().createQuery(
                "INSERT INTO event_users (usr_id, evt_id) VALUES (?1, ?2);");
        query.setParameter(1, user.getId());
        query.setParameter(2, event.getId());

        query.executeUpdate();
    }

    public boolean loginUserCorrect(String email, String password) {
        Query query = getEntityManager().createQuery(
                "SELECT password FROM User WHERE email ?1;");
        query.setParameter(1, email);

        String dbPass = (String) query.getSingleResult();

        if (dbPass != null) {
            if (dbPass.equals(password)) {
                return true;
            }
        }

        return false;
    }
}
