package assignment1v2;

/**
 *
 * @author Guan Wang, 12141158
 */
import java.util.ArrayList;
import java.util.Formatter;
import java.util.FormatterClosedException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The ShopModel class is to present the specific shop which run the whole
 * business. It have 2 arraylists to record the customer group currently in the
 * shop and customer groups which has been to this shop. The shop has a numSeats
 * variable, to present how many customers this shop can contain. When a group
 * of customer arrives at the shop, program will check if the number of people
 * can fit in the shop If yes, program will run follow up procedures, if no,
 * this will be recorded as lost business. The customers who get their ice cream
 * will leave in 10 time units after order.
 *
 * *
 */
public class ShopModel {

    Formatter output;
    private ArrayList groups = new ArrayList<CustomerGroup>();
    private ArrayList history = new ArrayList<CustomerGroup>();
    private int nextId = 0;//presents the ID for customer group
    private int numGroups = 0;//presents number of group currently in the shop
    private int numSeats;//presents the seat number of this shop
    private int lostBusiness = 0;//records the customer number who come to the shop but did not get ice cream 
    private int numServed = 0;

    //Constractor of ShopModel, numSeats present the seat number that shop contain
    public ShopModel(int numSeats) {
        this.numSeats = numSeats;
    }

    //addGroup method will add the CustomerGroup to the arraylist which records the customers currently in the shop.
    //then increment the numGroups variable which records the group number has been to this shop
    public void addGroup(CustomerGroup g) {
        groups.add(g);
        numGroups = numGroups + 1;
    }

    //this method will add the CustomerGroup to the arraylist which records the customers been to the shop.
    public void logGroup(CustomerGroup g) {
        history.add(g);
    }

    //method to assign ID for customer group
    public int getNextId() {
        return nextId++;
    }

    //method to get the number of lostBusiness
    public int getLostBusiness() {
        return lostBusiness;
    }

    //method to get the number of how many people get their ice cream.
    public int getNumberServed() {
        return numServed;
    }

    //method to print message of cunstomer group information currently in the shop
    //parameter output is used to represent destination file
    public void showGroups(Formatter output) {

        //print to specific document
        try {
            output.format("the following groups are in the shop:\n====================================\n");
            for (int i = 0; i < groups.size(); i++) {
                output.format("%s%n", groups.get(i));
                output.flush();
            }
        } catch (FormatterClosedException e) {
            Logger.getLogger(ShopModel.class.getName()).log(Level.SEVERE, null, e);
        }

    }

    //method to print message of cunstomer group information which has been to the shop
    public void showLog(Formatter output) {

        try {
            output.format("the following groups are in the history/log:\n====================================\n");
            for (int i = 0; i < history.size(); i++) {
                output.format("%s%n", history.get(i));
                output.flush();
            }
            output.close();
        } catch (FormatterClosedException e) {
            Logger.getLogger(ShopModel.class.getName()).log(Level.SEVERE, null, e);
        }

    }

    //This method is to print out a message to indicate the time that the group involved has been given their ice cream
    public void serOrder(int time, CustomerGroup g) {
        System.out.println("t = " + time + " : Order served for Group " + g.getId());
        numServed = numServed + g.getNumberInGroup();//increase the number of people who get ice cream
    }

    //This method is to print out a message to indicate the time that the group involved has finished their ice cream and leave the shop
    public void leave(int time, CustomerGroup g) {
        System.out.println("t = " + (time) + " : Group " + g.getId() + " leaves");
        groups.remove(g);//remove this group from shop
        numGroups--;//decrement the number of groups
        numSeats = numSeats + g.getNumberInGroup();// increase the number of seats available

    }

    //method to check if there are enough seats
    public boolean canSeat(int time, CustomerGroup g) {
        if (g.getNumberInGroup() <= numSeats) {
            numSeats = numSeats - g.getNumberInGroup();//decrease the seats available, because they are occupied
            System.out.printf("t = %d : Group %d (%d in group) seated\n", time, g.getId(), g.getNumberInGroup());
            return true;
        } else {
            lostBusiness = lostBusiness + g.getNumberInGroup();//increase the lost business number
            System.out.printf("t = %d : Group %d leaves as there are insufficient seats for the group\n", time, g.getId());
            return false;
        }

    }

}
