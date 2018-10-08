import java.util.ArrayList;
import java.util.List;

class Customer {
    private int id;
    List<CustomerRecord> invoice = new ArrayList<>();

    Customer(int id, List<CustomerRecord> invoice) {
        this.id = id;
        this.invoice = invoice;
    }

}
