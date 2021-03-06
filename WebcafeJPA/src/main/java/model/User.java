package model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.*;

/* @author BertGoens */
/**
 User class
 */
@Entity
@Table(name = "wc_User")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usr_id")
    private int id;

    @Column
    private boolean isAdmin = false;
    
    @Column(length = 30)
    private String password = "";

    @Column(length = 50, unique = true)
    private String email = "";

    @Column(length = 30)
    private String name = "";

    @Column(length = 30)
    private String forename = "";

    @Temporal(TemporalType.DATE)
    private Date birthday;

    @Column(length = 30)
    private String firm = "";

    @Column(length = 30)
    private String firmFunction = "";

    @Column()
    private String imagePath = "";

    public User() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getForename() {
        return forename;
    }

    public void setForename(String forename) {
        this.forename = forename;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getFirm() {
        return firm;
    }

    public void setFirm(String firm) {
        this.firm = firm;
    }

    public String getFirmFunction() {
        return firmFunction;
    }

    public void setFirmFunction(String firmFunction) {
        this.firmFunction = firmFunction;
    }

    public String getImagePath() {
        return this.imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", email=" + email + ", name=" + name + ", forename=" + forename + ", birthday=" + birthday + ", firm=" + firm + ", function=" + firmFunction + ", imagePath=" + imagePath + '}';
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + this.id;
        hash = 47 * hash + Objects.hashCode(this.password);
        hash = 47 * hash + Objects.hashCode(this.email);
        hash = 47 * hash + Objects.hashCode(this.name);
        hash = 47 * hash + Objects.hashCode(this.forename);
        hash = 47 * hash + Objects.hashCode(this.birthday);
        hash = 47 * hash + Objects.hashCode(this.firm);
        hash = 47 * hash + Objects.hashCode(this.firmFunction);
        hash = 47 * hash + Objects.hashCode(this.imagePath);
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
        final User other = (User) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.password, other.password)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.forename, other.forename)) {
            return false;
        }
        if (!Objects.equals(this.firm, other.firm)) {
            return false;
        }
        if (!Objects.equals(this.firmFunction, other.firmFunction)) {
            return false;
        }
        if (!Objects.equals(this.imagePath, other.imagePath)) {
            return false;
        }
        return Objects.equals(this.birthday, other.birthday);
    }

    public boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

}
