package model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Event.class)
public abstract class Event_ {

	public static volatile SingularAttribute<Event, Date> date;
	public static volatile SingularAttribute<Event, Integer> maxUsers;
	public static volatile SingularAttribute<Event, User> creator;
	public static volatile SingularAttribute<Event, City> city;
	public static volatile SingularAttribute<Event, String> imagePath;
	public static volatile SingularAttribute<Event, Double> fee;
	public static volatile ListAttribute<Event, Tag> tagList;
	public static volatile SingularAttribute<Event, String> eventDescription;
	public static volatile SingularAttribute<Event, String> name;
	public static volatile SingularAttribute<Event, String> location;
	public static volatile SingularAttribute<Event, Date> startTime;
	public static volatile SingularAttribute<Event, Integer> id;
	public static volatile SingularAttribute<Event, Date> endTime;
	public static volatile ListAttribute<Event, AgendaItem> agendaItemList;
	public static volatile ListAttribute<Event, User> visitorsList;

}

