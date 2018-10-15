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
        when(io.getInput()).thenReturn("3").thenReturn("3").thenReturn("2");

        vendor.processCheckOut(customer);
        vendor.processReturnBicycle(customer);

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

    @Test
    public void optionInvalidMethodShouldReturnSelectValidOption() {
        vendor.optionIsInValid();
        verify(io).display("Select a valid option");
    }

    @Test
    public void processCustomerRequestShouldreturnInvalidUserMessageForInvalidCustomer() {
        when(io.getInput()).thenReturn("8");

        vendor.processCustomerRequest();

        verify(io).display("Enter user ID");
        verify(io).display("Invalid user");
    }

    @Test
    public void processCustomerRequestShouldReturnUserMenuAndAskForOptionForValidCustomer() {
        when(io.getInput()).thenReturn("3").thenReturn("2").thenReturn("0");

        vendor.processCustomerRequest();

        verify(io).display("Enter user ID");
        verify(io,times(2)).display("1.CheckOut\n2.Return\n3.Invoice\n0.Quit\n");
        verify(io).display("Enter your option");
    }

    @Test
    public void processUserRequestShouldProcessCustomerRequestAsWellAsOwnerRequest() {
        List<CustomerRecord> list = new ArrayList<>();
        Customer customer = new Customer(1, list);
        when(io.getInput()).thenReturn("2").thenReturn("4").thenReturn("1").thenReturn("3").thenReturn("2").thenReturn("3").thenReturn("2").thenReturn("0").thenReturn("1").thenReturn("4").thenReturn("0");

        vendor.processUserRequest();

        verify(io,times(3)).display("1.Owner\n2.User\n0.Quit\n");
        verify(io).display("Enter user ID");
        verify(io,times(3)).display("1.CheckOut\n2.Return\n3.Invoice\n0.Quit\n");
        verify(io).display("Enter your option");

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

        verify(io).display("1\n2\n3\n4\n5\n6\n7\n");
        verify(io).display("Enter customer Id to see their invoice");
        verify(io).display("\nbicycleId= 3" +
                " totalHours= 2" +
                " rentPerHour= 4.5" +
                " totalRent= 9.0");
    }

}


