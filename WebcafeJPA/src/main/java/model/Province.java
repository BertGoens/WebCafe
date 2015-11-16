package model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

/* @author BertGoens */
@Entity
public class Province implements Serializable {

    @Temporal(TemporalType.DATE)
    private Date creationDate;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "prov_id")
    private int id;

    @Column()
    private String name;

    public Province() {

    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Province{" + "creationDate=" + creationDate + ", id=" + id + ", name=" + name + '}';
    }

}
