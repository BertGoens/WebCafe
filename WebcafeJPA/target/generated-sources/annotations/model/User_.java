package model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(User.class)
public abstract class User_ {

	public static volatile SingularAttribute<User, Date> birthday;
	public static volatile SingularAttribute<User, String> firmFunction;
	public static volatile SingularAttribute<User, String> forename;
	public static volatile SingularAttribute<User, String> firm;
	public static volatile SingularAttribute<User, String> password;
	public static volatile SingularAttribute<User, String> imagePath;
	public static volatile SingularAttribute<User, String> name;
	public static volatile SingularAttribute<User, Integer> id;
	public static volatile SingularAttribute<User, String> email;

}

