package assignment1v2;

/**
 *
 * @author Guan Wang, 12141158
 */
//Leave Event is to simulate the scene that customers finished their ice-cream and left the shop
//It is the end of a serial of events
public class LeaveEvent extends Event {

    private CustomerGroup group;

    //constructor of LeaveEvent,time presents the time this event happens
    //CustomerGroup presents the customers who wants to leave.
    public LeaveEvent(int time, CustomerGroup group) {
        super(time);
        this.group = group;
    }

    //Leave Event's process method is to invoke the leave method
    //it will not create more events.
    @Override
    public void process(ShopModel shopmodel, IScheduler scheduler) {
        shopmodel.leave(super.getTime(), group);
    }

}
