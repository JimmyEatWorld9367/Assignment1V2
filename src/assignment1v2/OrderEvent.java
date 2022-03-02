package assignment1v2;

/**
 *
 * @author Guan Wang, 12141158
 */
//Order Event is to simulate the scene that customers place an order for ice-cream
//It will create an LeaveEvent in the end.
public class OrderEvent extends Event {

    private CustomerGroup group;//represents the customer group which will place order
    //variables to define the range of time 
    private int leaveLowerBound = 5;
    private int leaveGeneratorBound = 8;

    //constrator of OrderEvent, time presents the time this event happens
    //CustomerGroup presents the customers who wants to order
    public OrderEvent(int time, CustomerGroup group) {
        super(time);
        this.group = group;
    }

    //OrderEvent's process will invoke serveOrder method to print a message that shows the order time and order group
    //It will create a LeaveEvent in the end
    @Override
    public void process(ShopModel shopmodel, IScheduler scheduler) {
        shopmodel.serOrder(super.getTime(), group);
        //create a leave event in a range(5-12) of random number
        LeaveEvent leaveEvent = new LeaveEvent(super.getTime() + super.getGenerator().nextInt(leaveGeneratorBound) + leaveLowerBound, group);
        scheduler.schedule(leaveEvent);
    }

}
