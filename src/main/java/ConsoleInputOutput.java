import java.util.Scanner;

public class ConsoleInputOutput implements InputOutput{
    private Scanner scanner;
    ConsoleInputOutput(Scanner scanner) {
        this.scanner = scanner;
    }

    public void display(String message) {
        System.out.println(message);
    }

    public String getInput() {
        return scanner.nextLine();
    }
}
