import java.util.Scanner;

public class BicycleRentApplication {
    public static void main(String args[]) {
        InputOutput io = new ConsoleInputOutput(new Scanner(System.in));
        BicycleVendor vendor = new BicycleVendor(io);
        vendor.processUserRequest();
    }
}
