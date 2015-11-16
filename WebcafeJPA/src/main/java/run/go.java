package run;

import dao.*;
import model.Event;

public class go {

    public static void main(String[] args) {
        EventDao daoEvent = new EventDao();

        Event event = new Event();
        event.setName("test");

        daoEvent.persist(event);
        System.out.println(event);
        System.out.println("gedaan");
    }
}
