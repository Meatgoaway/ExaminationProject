import java.util.Scanner;

public class InputCountImpl implements InputCount {
    Scanner scanner;

    public InputCountImpl(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public String inputMoneyCount() {
        System.out.print("введите число: ");
        String count = scanner.nextLine();
        return count;
    }
}
