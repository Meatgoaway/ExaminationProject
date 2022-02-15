import java.util.Scanner;

public class CountCurrency {
    Scanner scanner = new Scanner(System.in);
    InputCount inputCount = new InputCountImpl();
    OutputCount outputCount;

    String inputCurrency() {
        System.out.println("Выберите валюту из предложенного списка: " + String.join(",", CurrencyName.getCurrencyNameList()));
        return scanner.nextLine();
    }


    OutputCount currencyNameCheck(String cName) {
        CurrencyName currencyName = CurrencyName.fromString(cName);
        if (currencyName == null) {
            System.out.println("Выберите валюту из предложенного списка: " + String.join(",", CurrencyName.getCurrencyNameList()));
            return null;
        }

        switch (currencyName) {
            case RUB:
                return outputCount = new OutputCountRubImpl(inputCount.inputMoneyCount());
            case EUR:
                return outputCount = new OutputCountEurImpl(inputCount.inputMoneyCount());
        }
        return null;
    }

    void writeNumber(OutputCount currency) {
        System.out.println(currency.outputMoneyCount());
        scanner.close();

    }
}