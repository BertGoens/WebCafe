package model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AgendaItem.class)
public abstract class AgendaItem_ {

	public static volatile SingularAttribute<AgendaItem, String> themeTitel;
	public static volatile SingularAttribute<AgendaItem, Date> endDate;
	public static volatile SingularAttribute<AgendaItem, Event> eventAgenda;
	public static volatile SingularAttribute<AgendaItem, String> description;
	public static volatile SingularAttribute<AgendaItem, Integer> agendaId;
	public static volatile SingularAttribute<AgendaItem, Date> startDate;

}

