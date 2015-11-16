package dao;

import java.util.List;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;
import model.City;

/* @author BertGoens */
public class CityDao extends BaseDao<City, Integer> {

    public CityDao() {
        entityClass = City.class;
    }

    public City findCityByName(String name) {
        CriteriaBuilder cb = super.getEntityManager().getCriteriaBuilder();

        CriteriaQuery<City> qryFindCityByName = cb.createQuery(City.class);
        Root<City> c = qryFindCityByName.from(City.class);
        ParameterExpression<String> cityName = cb.parameter(String.class);
        qryFindCityByName.where(cb.equal(c.get("name"), cityName));
        TypedQuery<City> query = super.getEntityManager().createQuery(qryFindCityByName);
        query.setParameter(cityName, name);

        List<City> results = query.getResultList();
        if (!results.isEmpty()) {
            return results.get(0);
        }
        return null;
    }

    public List<City> findCityByCode(String code) {
        CriteriaBuilder cb = super.getEntityManager().getCriteriaBuilder();

        CriteriaQuery<City> qryFindCityByCode = cb.createQuery(City.class);
        Root<City> c = qryFindCityByCode.from(City.class);
        ParameterExpression<String> cityCode = cb.parameter(String.class);
        qryFindCityByCode.where(cb.equal(c.get("code"), cityCode));
        TypedQuery<City> query = super.getEntityManager().createQuery(qryFindCityByCode);
        query.setParameter(cityCode, code);

        return query.getResultList();
    }
}
