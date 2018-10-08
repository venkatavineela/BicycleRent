import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

public class AdminTest {

    @Test
    public void addMethodShouldAddTheRecordToUsersInvoice() {
        Admin admin = new Admin();
        List<CustomerRecord> list = new ArrayList<>();
        Bicycle bicycle = mock(Bicycle.class);
        Customer customer = new Customer(1,list);
        CustomerRecord record = new CustomerRecord(bicycle,new Date(2018,12,12,12,00),
                new Date(2018,12,12,13,00),12f);

        admin.addCustomerRecordToInvoice(customer,record);

        assertTrue(list.contains(record));

    }
}
