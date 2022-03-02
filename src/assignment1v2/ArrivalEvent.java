package assignment1v2;

/**
 *
 * @author Guan Wang, 12141158
 */
//Arrival Event is used to simulate the scene that customer arrived at the shop
//It is a trigger to the follow up Order Event.
public class ArrivalEvent extends Event {

    private int groupLowerBound = 1;
    private int groupGeneratorBound = 4;
    private int orderLowerBound = 1;
    private int orderGeneratorBound = 5;

    //constructor of ArrivalEvent class, time presents the time this event happens
    public ArrivalEvent(int time) {
        super(time);
    }

    //In Arrival Event's process method, create a object of customer group.Print its attributes, and log it in the history.
    //make a evaluation: if the number of group people can fit into the seats number, print a message, log them in the "in shop" list
    //then create the corresponding order event, assign its time at 1 time units later and schedule it.
    //if the number of people is larger than available seats, print a message 
    //at the end of the process method, schedule next arrival event in 2 time units
    @Override
    public void process(ShopModel shopmodel, IScheduler scheduler) {
        //Customer group's number of people attribute now is assigned with integer in range from 1-4
        CustomerGroup g = new CustomerGroup(shopmodel.getNextId(), super.getGenerator().nextInt(groupGeneratorBound) + groupLowerBound, super.getTime());
        System.out.printf("t = %d : group %d <%d people> arrived\n", g.getArrivalTime(), g.getId(), g.getNumberInGroup());
        shopmodel.logGroup(g);
        if (shopmodel.canSeat(g.getArrivalTime(), g) == true) {
            // System.out.printf("t = %d : Group %d (%d in group) seated\n", g.getArrivalTime(), g.getId(), g.getNumberInGroup());
            shopmodel.addGroup(g);
            //create next Arrival Event in 2 time units later.
            OrderEvent orderEvent = new OrderEvent(g.getArrivalTime() + super.getGenerator().nextInt(orderGeneratorBound) + orderLowerBound, g);
            scheduler.schedule(orderEvent);
        }
        ArrivalEvent arrivalEvent = new ArrivalEvent(super.getTime() + 2);//create next Arrival Event in 2 time units later.
        scheduler.schedule(arrivalEvent);
    }

}
