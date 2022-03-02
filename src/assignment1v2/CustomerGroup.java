package assignment1v2;

/**
 *
 * @author Guan Wang, 12141158
 */
//This class stores the information about a customer group that has arrived at the shop.
public class CustomerGroup {

    private int id = 0;
    private int numberInGroup;
    private int arrivalTime = 0;

    //constractor of CustomerGroup, parameter presents:
    //identifier for the group,the number of people in the group, and the arrival time of the group.
    public CustomerGroup(int id, int number, int time) {
        this.id = id;
        this.numberInGroup = number;
        this.arrivalTime = time;
    }

    //method to get ID
    public int getId() {
        return id;
    }

    //method to get numberInGroup
    public int getNumberInGroup() {
        return numberInGroup;
    }

    //method to get arrival time
    public int getArrivalTime() {
        return arrivalTime;
    }

    //override toString method, display groupid number in group and arrival time
    @Override
    public String toString() {
        return "Group" + getId() + "( " + getNumberInGroup() + " people) arrived at t =" + getArrivalTime();
    }

}
