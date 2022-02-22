import java.util.Scanner;

public class CountCurrency {
    Scanner scanner;
    String inputCount;
    OutputCount outputCount;

    public CountCurrency(Scanner scanner, String inputCount) {
        this.scanner = scanner;
        this.inputCount = inputCount;
    }

    String inputCurrency() {
        System.out.println("Выберите валюту из предложенного списка: " + String.join(",", CurrencyName.getCurrencyNameList()));
        return scanner.nextLine();
    }


    OutputCount currencyNameCheck(String cName) throws CurrencyChangeException {
        CurrencyName currencyName = CurrencyName.fromString(cName);
        if (currencyName == null) {
            throw new CurrencyChangeException();
        }
        switch (currencyName) {
            case RUB:
                return outputCount = new OutputCountRubImpl(inputCount);
            case EUR:
                return outputCount = new OutputCountEurImpl(inputCount);
        }
        return null;
    }

    void writeNumber(OutputCount currency) {
        System.out.println(currency.outputMoneyCount());
    }

}