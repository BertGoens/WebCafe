package model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

/* @author BertGoens */
@Entity
public class City implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "city_id")
    private int id;

    @Temporal(TemporalType.DATE)
    private Date creationDate;

    @Column()
    private String alpha = "";

    @Column()
    private String code = "";

    @Column()
    private long latitude = 0;

    @Column()
    private long longitude = 0;

    @Column()
    private String name = "";

    @ManyToOne(targetEntity = Province.class)
    @JoinColumn(name = "prov_id")
    private Province province;

    public City() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getAlpha() {
        return alpha;
    }

    public void setAlpha(String alpha) {
        this.alpha = alpha;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public long getLatitude() {
        return latitude;
    }

    public void setLatitude(long latitude) {
        this.latitude = latitude;
    }

    public long getLongitude() {
        return longitude;
    }

    public void setLongitude(long longtitude) {
        this.longitude = longtitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }

    @Override
    public String toString() {
        return "City{" + "id=" + id + ", creationDate=" + creationDate + ", alpha=" + alpha
                + ", code=" + code + ", latitude=" + latitude + ", longitude=" + longitude
                + ", name=" + name + ", province=" + province + '}';
    }

}
