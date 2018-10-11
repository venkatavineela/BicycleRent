import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;


public class BicycleVendorTest {

    InputOutput io = mock(InputOutput.class);
    BicycleVendor vendor = new BicycleVendor(io);

    @Test
    public void processCheckoutShouldReturnCyclesForRentAndShouldReturnEnjoyTheRide() {
        Customer customer = mock(Customer.class);
        when(io.getInput()).thenReturn("3");
        vendor.processCheckOut(customer);
        verify(io).display("1.Hero(3/hr)\n" +
                "   2.Hercules(2.5/hr)\n" +
                "    3.Atlas(4.5/hr)\n" +
                "    4.BSA(4/hr)\n" +
                "    5.Avon(5/hr)\n" +
                "    6.Montra(5.5/hr)\n" +
                "    7.Kross(6/hr)");
        verify(io).display("Enjoy the ride");
    }

    @Test
    public void processCheckoutShouldReturnCyclesForRentAndShouldReturnThatBicycleIsNotAvailable() {
        Customer customer = mock(Customer.class);
        vendor.checkedOutBicycleList.put(3, customer);
        Customer otherCustomer = mock(Customer.class);
        when(io.getInput()).thenReturn("3");
        vendor.processCheckOut(otherCustomer);
        verify(io).display("1.Hero(3/hr)\n" +
                "   2.Hercules(2.5/hr)\n" +
                "    3.Atlas(4.5/hr)\n" +
                "    4.BSA(4/hr)\n" +
                "    5.Avon(5/hr)\n" +
                "    6.Montra(5.5/hr)\n" +
                "    7.Kross(6/hr)");
        verify(io).display("That bicycle is not available");
    }

    @Test
    public void processReturnBicycleShouldReturnInvalidReturnMessage() {

        List<CustomerRecord> list = new ArrayList<>();
        Customer customer = new Customer(1, list);
        when(io.getInput()).thenReturn("3").thenReturn("2");

        vendor.processReturnBicycle(customer);

        verify(io).display("Enter the id of cycle you want to return");
        verify(io).display("Enter number of hours");
        verify(io).display("This is not the valid cycle to return");

    }

    @Test
    public void processReturnBicycleShouldReturnThanksMessageForValidCustomer() {

        List<CustomerRecord> list = new ArrayList<>();
        Customer customer = new Customer(1, list);
        vendor.checkedOutBicycleList.put(3, customer);
        when(io.getInput()).thenReturn("3").thenReturn("2");

        vendor.processReturnBicycle(customer);

        verify(io).display("Enter the id of cycle you want to return");
        verify(io).display("Enter number of hours");
        verify(io).display("Thank you for returning the bicycle");

    }

    @Test
    public void processInvoiceShouldReturnCustomerInvoice() {
        List<CustomerRecord> list = new ArrayList<>();
        Customer customer = new Customer(1, list);
        when(io.getInput()).thenReturn("3").thenReturn("3").thenReturn("2");
        vendor.processCheckOut(customer);
        vendor.processReturnBicycle(customer);
        vendor.processInvoice(customer);

        verify(io).display("1.Hero(3/hr)\n" +
                "   2.Hercules(2.5/hr)\n" +
                "    3.Atlas(4.5/hr)\n" +
                "    4.BSA(4/hr)\n" +
                "    5.Avon(5/hr)\n" +
                "    6.Montra(5.5/hr)\n" +
                "    7.Kross(6/hr)");
        verify(io).display("Enjoy the ride");
        verify(io).display("Enter the id of cycle you want to return");
        verify(io).display("Enter number of hours");
        verify(io).display("Thank you for returning the bicycle");
        verify(io).display("\nbicycleId= 3" +
                " totalHours= 2" +
                " rentPerHour= 4.5" +
                " totalRent= 9.0");


    }
}
