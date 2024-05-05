package main.eventManager;

import java.util.ArrayList;


public class EventManager {
    private long currentDate;

    private final ArrayList<Event> EventList;

    public ArrayList<Event> getEventList() {
        return EventList;
    }

    public EventManager(ArrayList<Event> eventList) {
        EventList = eventList;
    }

    public void addEvent(Event e)
    {
        EventList.add(e);
    }

    /**
     * permet d'incrémenter la date et de traiter les evenements associer
     */
    public void next()
    {
        currentDate += 1;
        for (Event currentEvent : EventList) {
            if (currentEvent.getDate() == currentDate) {
                currentEvent.execute();
            }
        }

        if(isFinished())
        {
            restart();
        }
    }

    private boolean isFinished()
    {
        for (Event currentEvent : EventList) {
            if (currentEvent.getDate() > currentDate) {
                return false;
            }
        }

        return true;
    }

    public void restart()
    {
        this.currentDate = 0;
    }
}
