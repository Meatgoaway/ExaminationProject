import java.util.Scanner;

public class InputCountImpl implements InputCount {
    Scanner scanner = new Scanner(System.in);

    @Override
    public String inputMoneyCount() {
        System.out.print("введите число: ");
        String count = scanner.nextLine();
        return count;
    }
}
