package main.eventManager;
public abstract class Event {
    private final long date;

    public long getDate()
    {
        return this.date;
    }

    public Event(long date)
    {
        this.date = date;
    }

    public abstract void execute();
}
