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

    public List<Event> getRegistredEvents(User user) {
        Query query = getEntityManager().createQuery(
                "SELECT e FROM wc_Event e WHERE Event.evt_id IN "
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

    public boolean loginUserCorrect(String email, String password) {
        User tryLogInUser = findByEmail(email);

        if (tryLogInUser != null) {
            return tryLogInUser.getPassword().equals(password);
        }

        return false;
    }
}
