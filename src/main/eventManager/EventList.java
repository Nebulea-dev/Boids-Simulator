package main.eventManager;

import java.util.ArrayList;
import java.util.Iterator;

public class EventList implements Iterable<Event>{
    private final ArrayList<Event> eventList;

    public EventList(ArrayList<Event> eventList) {
        this.eventList = eventList;
    }
    public EventList() {
        this.eventList = new ArrayList<>();
    }

    /**
     * ajoute un événement
     * @param e un evenement
     */
    public void add(Event e)
    {
        eventList.add(e);
    }

    public ArrayList<Event> getEventList() {
        return eventList;
    }

    @Override
    public Iterator<Event> iterator() {
        return eventList.iterator();
    }
}
