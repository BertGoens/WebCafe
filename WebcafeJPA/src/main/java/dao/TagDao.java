package dao;

import java.util.List;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;
import model.Tag;

/* @author BertGoens */
public class TagDao extends BaseDao<Tag, Integer> {

    public TagDao() {
        entityClass = Tag.class;
    }

    public Tag findTagByName(String name) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();

        CriteriaQuery<Tag> qryFindTagByName = cb.createQuery(Tag.class);
        Root<Tag> c = qryFindTagByName.from(Tag.class);
        ParameterExpression<String> tagname = cb.parameter(String.class);
        qryFindTagByName.where(cb.equal(c.get("name"), tagname));

        TypedQuery<Tag> query = getEntityManager().createQuery(qryFindTagByName);
        query.setParameter(tagname, name);

        List<Tag> results = query.getResultList();
        if (!results.isEmpty()) {
            return results.get(0);
        }
        return null;
    }

    public List<Tag> findTagsByName(String name) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();

        CriteriaQuery<Tag> qryFindTagsByName = cb.createQuery(Tag.class);
        Root<Tag> c = qryFindTagsByName.from(Tag.class);
        ParameterExpression<String> tagname = cb.parameter(String.class);
        qryFindTagsByName.where(cb.like(c.get("name"), tagname));

        TypedQuery<Tag> query = getEntityManager().createQuery(qryFindTagsByName);
        query.setParameter(tagname, name);

        return query.getResultList();
    }
}
