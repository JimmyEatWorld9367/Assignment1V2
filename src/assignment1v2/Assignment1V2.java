package assignment1v2;

import java.util.ArrayList;
import java.io.*;
import java.util.Formatter;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Assignment1V2 {

    public static void main(String[] args) {
        ArrayList<Event> eventqueue = new ArrayList<>();//initial list, contain first arrival event
        Formatter output = null;
        try {
            output = new Formatter("statistics.txt");//declare file name, which will record history
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Assignment1V2.class.getName()).log(Level.SEVERE, null, ex);
        }
        ShopModel shopmodel = new ShopModel(8);// create an object of shopmodel, set seats available 8
        Simulator simulator = new Simulator(shopmodel);// create an object of simulator, pass the shopmodel reference as parameter
        ArrivalEvent arrivalEvent = new ArrivalEvent(0);//first arrival event, at time 0
        eventqueue.add(arrivalEvent);
        simulator.initialize(eventqueue);
        System.out.println("Simulation Trace:\n=================");
        simulator.run(20);//set business time, 20 time units
        //print out number of people get served and lost businesses
        output.format("Statistics\n=============\nThe number of people served = %d\n The number of lost business = %d\n\n",
                shopmodel.getNumberServed(), shopmodel.getLostBusiness());//output to specific file
        shopmodel.showGroups(output);
        shopmodel.showLog(output);

    }

}
