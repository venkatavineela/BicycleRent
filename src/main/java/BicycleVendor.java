import java.util.*;

import static java.lang.Integer.parseInt;

class BicycleVendor {


    private Map<Integer, Customer> customerList = new HashMap<>();
    private Map<Integer, Bicycle> bicycleList = new HashMap<>();
    Map<Integer, Customer> checkedOutBicycleList = new HashMap<>();
    private Map<String, CustomerMenuOption> customerMenu = new HashMap<>();

    private InputOutput io;


    BicycleVendor(InputOutput io) {
        this.io = io;
        addCustomers();
        addBicycles();
    }

    private void addCustomers() {
        List<CustomerRecord> invoice1 = new ArrayList<>();
        List<CustomerRecord> invoice2 = new ArrayList<>();
        List<CustomerRecord> invoice3 = new ArrayList<>();
        List<CustomerRecord> invoice4 = new ArrayList<>();
        List<CustomerRecord> invoice5 = new ArrayList<>();
        List<CustomerRecord> invoice6 = new ArrayList<>();
        List<CustomerRecord> invoice7 = new ArrayList<>();


        customerList.put(1, new Customer(1, invoice1));
        customerList.put(2, new Customer(1, invoice2));
        customerList.put(3, new Customer(1, invoice3));
        customerList.put(4, new Customer(1, invoice4));
        customerList.put(5, new Customer(1, invoice5));
        customerList.put(6, new Customer(1, invoice6));
        customerList.put(7, new Customer(1, invoice7));
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
        if ((checkedOutBicycleList.containsKey(cycleId)) && (checkedOutBicycleList.get(cycleId) == (customer))) {
            validBicycleToReturn(customer, cycleId, totalHours);
        } else {
            io.display(Constants.THIS_IS_NOT_THE_VALID_CYCLE_TO_RETURN);
        }
    }

    private void validBicycleToReturn(Customer customer, int cycleId, int totalHours) {
        Bicycle bicycle = bicycleList.get(cycleId);
        BicycleRent rent = new BicycleRent(bicycle, totalHours);
        addCustomerRecordToInvoice(customer, new CustomerRecord(cycleId, totalHours, bicycle.model.getRentPerHour(), rent.calculate()));
        checkedOutBicycleList.remove(cycleId);
        io.display(Constants.THANK_YOU_FOR_RETURNING_THE_BICYCLE);
    }

    private void addCustomerRecordToInvoice(Customer customer, CustomerRecord record) {
        customer.invoice.add(record);
    }


    void processInvoice(Customer customer) {
        for (int i = 0; i < customer.invoice.size(); i++) {
            io.display(customer.invoice.get(i).toString());
        }
    }

    void optionIsInValid() {
        io.display(Constants.SELECT_A_VALID_OPTION);
    }

    void processCustomerRequest() {
        io.display(Constants.ENTER_USER_ID);
        int userId = parseInt(io.getInput());
        if (customerList.containsKey(userId)) {
            customerMenuMap();
            io.display(Constants.USER_MENU);
            io.display(Constants.ENTER_YOUR_OPTION);
            String option = io.getInput();
            serveExistingCustomer(userId, option);
        } else {
            io.display(Constants.INVALID_USER);
        }
    }

    private void serveExistingCustomer(int userId, String option) {
        while (!option.equals("0")) {
            if (customerMenu.containsKey(option)) {
                customerMenu.get(option).execute(this, customerList.get(userId));
            } else {
                customerMenu.get("invalid").execute(this, customerList.get(userId));
            }
            io.display(Constants.USER_MENU);
            option = io.getInput();
        }
    }

    void processUserRequest() {
        io.display(Constants.MAIN_MENU);
        String option = io.getInput();
        while (!option.equals("0")) {
            switch (option) {
                case "1":
                    processOwnerRequest();
                    break;
                case "2":
                    processCustomerRequest();
                    break;
                default:
                    optionIsInValid();
            }
            io.display(Constants.MAIN_MENU);
            option = io.getInput();
        }

    }

    void processOwnerRequest() {
        String customers = "";
        for (int i = 1; i <= customerList.size(); i++) {
            customers += i;
            customers += "\n";
        }
        io.display(customers);
        io.display(Constants.ENTER_CUSTOMER_ID_TO_SEE_THEIR_INVOICE);
        int id = parseInt(io.getInput());
        if (customerList.containsKey(id)) {
            for (int i = 0; i < customerList.get(id).invoice.size(); i++) {
                io.display(customerList.get(id).invoice.get(i).toString());
            }
        } else {
            io.display(Constants.INVALID_ID);
        }
    }

    private void customerMenuMap() {
        customerMenu.put("1", CustomerMenuOption.CheckOutBicycle);
        customerMenu.put("2", CustomerMenuOption.ReturnBicycle);
        customerMenu.put("3", CustomerMenuOption.Invoice);
        customerMenu.put("invalid", CustomerMenuOption.Invalid);
    }

}
