 package assignment1v2;

import java.util.ArrayList;

/**
 *
 * @author Guan Wang, 12141158
 */
//Simlulator class is like a "switch" of the shop, it did preparation work for the shop
public class Simulator implements IScheduler {

    private ArrayList<Event> events; // The ordered ArrayList of    
    // events – the event queue

    private int clock = 0; // A “clock”  to track the time in the simulation 
    // To be initialized to 0 at the start.

    private ShopModel model; //A reference to the shop model 

    //This method adds an event to the event queue, i.e. to the ArrayList of Events. 
    //The event queue is to be maintained as an ordered list (in ascending order of the time the event is scheduled to occur)
    //The event to be scheduled is passed into the schedule() method.
    @Override
    public void schedule(Event event) {
        int length = events.size();
        if (length == 0) {//if the length is 0, means there is no event in the queue, put the event in the first position
            events.add(event);
        }
        for (int i = 0; i < length; i++) {//go through the event queue
            if (event.getTime() < events.get(i).getTime()) {//compare the event time
                events.add(i, event);//insert event to correct position
                break;
            } else if (i == length - 1) {
                events.add(event);//when reaches the last element, means the other event is earlier than this one, put it at the end of queue.
            }
        }
    }

    //constractor of Simulator class
    public Simulator(ShopModel shopmodel) {
        //pass the reference of ShopModel to this class
        this.model = shopmodel;
    }

    //The ArrayList of events (called the event queue) is passed into the simulator using this initialize method.
    //The initial event queue will be created in the program’s main() method. 
    public void initialize(ArrayList<Event> event) {
        this.events = event;
    }

    /**
     * This is the method that runs the simulation. It is passed the time to
     * stop the simulation and executes a loop that takes events from the event
     * queue and processes them until either there are no events on the queue or
     * the time/clock has reached the stop time.  
     */
    public void run(int stop) {

        if ((events == null) || events.isEmpty()) {
            return;
        }
        Event e = events.remove(0);
        clock = e.getTime();
        // events queue  will never become empty as after the first event is added,  
        // every arrival event will generate a new arrival event (which may be
        // greater than the stop time)
        while (clock <= stop) {
            e.process(model, this);//pass a reference of this class, so that other classes can invoke schedule method
            e = events.remove(0);
            clock = e.getTime();
        }
    }
}
