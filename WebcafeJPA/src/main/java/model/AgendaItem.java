package model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

/* @author BertGoens */
@Entity
public class AgendaItem implements Serializable {

    @Id
    @Column(name = "agendaId", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int agendaId;

    @Temporal(TemporalType.TIME)
    @Column(name = "startDate") // !TODO , nullable = false
    private Date startDate;

    @Temporal(TemporalType.TIME)
    @Column(name = "endDate") // !TODO , nullable = false
    private Date endDate;

    @Column()
    private String themeTitel;

    @Column(name = "description", length = 2000)
    private String description;

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

}
