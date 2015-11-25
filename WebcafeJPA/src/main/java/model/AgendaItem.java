package model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.*;

/* @author BertGoens */
@Entity
@Table(name = "wc_AgendaItem")
public class AgendaItem implements Serializable {

    @Id
    @Column(name = "agendaId", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int agendaId;

    @Temporal(TemporalType.TIME)
    @Column(name = "startDate") // !TODO , nullable = false
    private Date startDate = new Date();

    @Temporal(TemporalType.TIME)
    @Column(name = "endDate") // !TODO , nullable = false
    private Date endDate = new Date();

    @Column()
    private String themeTitel = "";

    @Column(name = "description", length = 2000)
    private String description = "";

    @ManyToOne
    private Event eventAgenda;

    public AgendaItem() {
    }

    public int getAgendaId() {
        return agendaId;
    }

    public void setAgendaId(int agendaId) {
        this.agendaId = agendaId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "AgendaItem{" + "agendaId=" + agendaId + ", eventId=" + eventAgenda.getId() + ", startDate=" + startDate
                + ", endDate=" + endDate + ", longDescription=" + description + '}';
    }

    public Event getEventAgenda() {
        return eventAgenda;
    }

    public void setEventAgenda(Event eventAgenda) {
        this.eventAgenda = eventAgenda;
    }

    public String getThemeTitel() {
        return themeTitel;
    }

    public void setThemeTitel(String themeTitel) {
        this.themeTitel = themeTitel;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + this.agendaId;
        hash = 29 * hash + Objects.hashCode(this.startDate);
        hash = 29 * hash + Objects.hashCode(this.endDate);
        hash = 29 * hash + Objects.hashCode(this.themeTitel);
        hash = 29 * hash + Objects.hashCode(this.description);
        hash = 29 * hash + Objects.hashCode(this.eventAgenda);
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
        final AgendaItem other = (AgendaItem) obj;
        if (this.agendaId != other.agendaId) {
            return false;
        }
        if (!Objects.equals(this.themeTitel, other.themeTitel)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.startDate, other.startDate)) {
            return false;
        }
        if (!Objects.equals(this.endDate, other.endDate)) {
            return false;
        }
        if (!Objects.equals(this.eventAgenda, other.eventAgenda)) {
            return false;
        }
        return true;
    }

}
