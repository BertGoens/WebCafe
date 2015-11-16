package dao;

import java.util.List;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import model.AgendaItem;
import model.Event;

/* @author BertGoens */
public class AgendaItemDao extends BaseDao<AgendaItem, Integer> {

    public AgendaItemDao() {
        entityClass = AgendaItem.class;
    }

    public List<AgendaItem> findAllForEvent(Event event) {
        CriteriaBuilder cb = super.getEntityManager().getCriteriaBuilder();

        CriteriaQuery<AgendaItem> qryFindAgendasByEvent = cb.createQuery(AgendaItem.class);
        Root<AgendaItem> c = qryFindAgendasByEvent.from(AgendaItem.class);
        ParameterExpression<Integer> eventId = cb.parameter(Integer.class);
        qryFindAgendasByEvent.where(cb.equal(c.get("evt_id"), eventId));
        TypedQuery<AgendaItem> query = super.getEntityManager().createQuery(qryFindAgendasByEvent);
        query.setParameter(eventId, event.getId());

        return query.getResultList();

    }
}
