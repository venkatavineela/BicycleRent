import java.util.*;

import static java.lang.Integer.parseInt;

class BicycleVendor {


    private List<Integer> customerList = new ArrayList<>();
    private Map<Integer, Bicycle> bicycleList = new HashMap<>();
    Map<Integer, Customer> checkedOutBicycleList = new HashMap<>();
    private InputOutput io;


    BicycleVendor(InputOutput io) {
        this.io = io;
        addCustomers();
        addBicycles();
    }

    private void addCustomers() {
        customerList.add(1);
        customerList.add(2);
        customerList.add(3);
        customerList.add(4);
        customerList.add(5);
        customerList.add(6);
        customerList.add(7);
    }

    private void addBicycles() {
        bicycleList.put(1, new Bicycle(1, Model.Hero));
        bicycleList.put(2, new Bicycle(2, Model.Hercules));
        bicycleList.put(3, new Bicycle(3, Model.Atlas));
        bicycleList.put(4, new Bicycle(4, Model.BSA));
        bicycleList.put(5, new Bicycle(5, Model.Avon));
        bicycleList.put(6, new Bicycle(6, Model.Montra));
        bicycleList.put(7, new Bicycle(7, Model.Kross));

    }

    private void addCustomerRecordToInvoice(Customer customer, CustomerRecord record) {
        customer.invoice.add(record);
    }

    void processCheckOut(Customer customer) {
        io.display(Constants.CYCLES_FOR_RENT);
        int cycleId = parseInt(io.getInput());
        if (checkedOutBicycleList.containsKey(cycleId)) {
            io.display(Constants.THAT_BICYCLE_IS_NOT_AVAILABLE);
        } else {
            checkedOutBicycleList.put(cycleId, customer);
            io.display(Constants.ENJOY_THE_RIDE);
        }
    }

    void processReturnBicycle(Customer customer) {
        io.display(Constants.ENTER_THE_ID_OF_CYCLE_YOU_WANT_TO_RETURN);
        int cycleId = parseInt(io.getInput());
        io.display(Constants.ENTER_NUMBER_OF_HOURS);
        int totalHours = parseInt(io.getInput());
        if ((checkedOutBicycleList.containsKey(cycleId)) && (checkedOutBicycleList.get(cycleId).equals(customer))) {
            Bicycle bicycle = bicycleList.get(cycleId);
            BicycleRent rent = new BicycleRent(bicycle, totalHours);
            addCustomerRecordToInvoice(customer, new CustomerRecord(cycleId, totalHours, bicycle.model.getRentPerHour(), rent.calculate()));
            checkedOutBicycleList.remove(cycleId);
            io.display(Constants.THANK_YOU_FOR_RETURNING_THE_BICYCLE);
        } else {
            io.display(Constants.THIS_IS_NOT_THE_VALID_CYCLE_TO_RETURN);
        }
    }

    void processInvoice(Customer customer) {
        for (int i = 0; i < customer.invoice.size(); i++) {
            io.display(customer.invoice.get(i).toString());
        }
    }
}
