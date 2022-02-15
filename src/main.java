public class main {
    public static void main(String[] args) {

        CountCurrency countCurrency = new CountCurrency();
        String currencyInsertName = countCurrency.inputCurrency();
        countCurrency.writeNumber(countCurrency.currencyNameCheck(currencyInsertName));

    }
}

