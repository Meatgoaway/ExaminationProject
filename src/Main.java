import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        InputCount inputCount = new InputCountImpl(scanner);
        CountCurrency countCurrency = new CountCurrency(scanner, inputCount.inputMoneyCount());
        String currencyInsertName = countCurrency.inputCurrency();

        try {
            countCurrency.writeNumber(countCurrency.currencyNameCheck(currencyInsertName));
        } catch (CurrencyChangeException e) {
            System.out.println("Exception: " + e.toString());
            System.out.println("Список валют: " + String.join(",", CurrencyName.getCurrencyNameList()));
        }

        scanner.close();
    }
}

