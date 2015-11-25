package model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.*;

/* @author BertGoens */
@Entity
@Table(name = "wc_City")
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

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 83 * hash + this.id;
        hash = 83 * hash + Objects.hashCode(this.creationDate);
        hash = 83 * hash + Objects.hashCode(this.alpha);
        hash = 83 * hash + Objects.hashCode(this.code);
        hash = 83 * hash + (int) (this.latitude ^ (this.latitude >>> 32));
        hash = 83 * hash + (int) (this.longitude ^ (this.longitude >>> 32));
        hash = 83 * hash + Objects.hashCode(this.name);
        hash = 83 * hash + Objects.hashCode(this.province);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final City other = (City) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.latitude != other.latitude) {
            return false;
        }
        if (this.longitude != other.longitude) {
            return false;
        }
        if (!Objects.equals(this.alpha, other.alpha)) {
            return false;
        }
        if (!Objects.equals(this.code, other.code)) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.creationDate, other.creationDate)) {
            return false;
        }
        if (!Objects.equals(this.province, other.province)) {
            return false;
        }
        return true;
    }

}
