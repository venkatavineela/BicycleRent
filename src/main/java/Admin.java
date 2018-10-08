import java.util.ArrayList;
import java.util.List;

class Admin {

    private List<Customer> customerList = new ArrayList<>();

    void addCustomerRecordToInvoice(Customer customer, CustomerRecord record) {
        if (!customerList.contains(customer)) {
            customerList.add(customer);
        }
        customer.invoice.add(record);
    }

}
