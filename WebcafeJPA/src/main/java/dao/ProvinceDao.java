package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;
import model.City;
import model.Province;

/* @author BertGoens */
public class ProvinceDao extends BaseDao<Province, Integer> {

    public ProvinceDao() {
        entityClass = Province.class;
    }

    public Province findByName(String name) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();

        CriteriaQuery<Province> qryFindProvinceByName = cb.createQuery(Province.class);
        Root<Province> c = qryFindProvinceByName.from(Province.class);
        ParameterExpression<String> provname = cb.parameter(String.class);
        qryFindProvinceByName.where(cb.equal(c.get("name"), provname));
        TypedQuery<Province> query = getEntityManager().createQuery(qryFindProvinceByName);
        query.setParameter(provname, name);

        List<Province> results = query.getResultList();
        if (!results.isEmpty()) {
            return results.get(0);
        }
        return null;
    }

    public List<City> findCitiesInProvince(Province province) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();

        CriteriaQuery<City> qryFindCitiesFromProvince = cb.createQuery(City.class);
        Root<City> c = qryFindCitiesFromProvince.from(City.class);
        ParameterExpression<Province> provname = cb.parameter(Province.class);
        qryFindCitiesFromProvince.where(cb.equal(c.get("province"), provname));
        TypedQuery<City> query = getEntityManager().createQuery(qryFindCitiesFromProvince);
        query.setParameter(provname, province);

        return query.getResultList();
    }
}
