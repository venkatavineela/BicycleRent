import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

class Customer {
    private int id;
    List<CustomerRecord> invoice = new ArrayList<>();

    Customer(int id, List<CustomerRecord> invoice) {
        this.id = id;
        this.invoice = invoice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return id == customer.id &&
                Objects.equals(invoice, customer.invoice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, invoice);
    }
}
