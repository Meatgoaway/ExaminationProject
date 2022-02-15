import java.util.EnumSet;
import java.util.List;
import java.util.stream.Collectors;

public enum CurrencyName {
    RUB("RUB"),
    EUR("EUR");

    private String currencyName;

    CurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public static List<String> getCurrencyNameList() {
        return EnumSet.allOf(CurrencyName.class).stream().map(CurrencyName::getName).collect(Collectors.toList());
    }

    public static CurrencyName fromString(String cName) {
        if (cName == null || cName.isEmpty()) {
            return null;
        }

        for (CurrencyName currencyName : CurrencyName.values()) {
            if (currencyName.getName().equalsIgnoreCase(cName)) {
                return currencyName;
            }
        }
        return null;
    }

    public String getName() {
        return currencyName;
    }
}