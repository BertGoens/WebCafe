package model;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;


@Entity
public class Event implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(insertable = true, name = "evt_id")
    private int id;

    /*
    The maximum users that can attend the event
     */
    @Column()
    private int maxUsers = 10;

    /*
    The current count of users planning to attend the event
     */
    @Column()
    private int currentUsers;

    @Column(name = "eventDescription", length = 2000)
    private String eventDescription = "";

    /*
    The name of the event
     */
    @Column()
    private String name = "";

    /*
    The street & number
    ex: Galloperstraat 39
     */
    @Column()
    private String location = "";

    /*
    The venue where the event takes place
    ex: The party room
     */
    @Column()
    private String venue = "";

    @ManyToOne(targetEntity = City.class)
    @JoinColumn(name = "city_id")
    private City city;

    @Temporal(TemporalType.DATE)
    private Date date;

    @Temporal(TemporalType.TIME)
    private Date startTime;

    @Temporal(TemporalType.TIME)
    private Date endTime;

    @ManyToMany
    @JoinTable(name = "event_users",
            joinColumns
            = @JoinColumn(name = "evt_id", referencedColumnName = "evt_id"),
            inverseJoinColumns
            = @JoinColumn(name = "usr_id", referencedColumnName = "usr_id")
    )
    private List<User> visitorsList;

    @ManyToMany(targetEntity = Tag.class)
    @JoinTable(name = "event_tag",
            joinColumns
            = @JoinColumn(name = "evt_id", referencedColumnName = "evt_id"),
            inverseJoinColumns
            = @JoinColumn(name = "tag_id", referencedColumnName = "tag_id")
    )
    private List<Tag> tagList;

    @Column()
    private String imagePath = "";

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "usr_id")
    private User creator;

    @OneToMany(mappedBy = "eventAgenda", targetEntity = AgendaItem.class)
    private List<AgendaItem> agendaItemList;

    /*
    The entry price for the event
     */
    @Column
    private double fee = 0;

    /*
    A description of who might be interested in partaking
    ex: Programmers, Enthousiasts, ..
     */
    @Column
    private String forWho = "";

    public Event() {
        visitorsList = new ArrayList<>();
        tagList = new ArrayList<>();
        agendaItemList = new ArrayList<>();
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public List<Tag> getTagList() {
        return tagList;
    }

    public void setTagList(List<Tag> tagList) {
        this.tagList = tagList;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public List<AgendaItem> getAgendaItemList() {
        return agendaItemList;
    }

    public void setAgendaItemList(List<AgendaItem> agendaItemList) {
        this.agendaItemList = agendaItemList;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    public List<User> getVisitorsList() {
        return visitorsList;
    }

    public void setVisitorsList(List<User> visitorsList) {
        this.visitorsList = visitorsList;
    }

    @Override
    public String toString() {
        return "Event{" + "id=" + id + ", name=" + name + ", location=" + location
                + ", city=" + city + ", date=" + date + ", startTime=" + startTime + ", endTime="
                + endTime + ", registeredUsers=" + visitorsList + ", tags=" + tagList + ", imagePath="
                + imagePath + ", admin=" + creator + ", agenda=" + agendaItemList + ", fee=" + fee + '}';
    }

    public int getMaxUsers() {
        return maxUsers;
    }

    public void setMaxUsers(int maxUsers) {
        this.maxUsers = maxUsers;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public int getCurrentUsers() {
        return currentUsers;
    }

    public void setCurrentUsers(int currentUsers) {
        this.currentUsers = currentUsers;
    }

    public String getForWho() {
        return forWho;
    }

    public void setForWho(String forWho) {
        this.forWho = forWho;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

}
