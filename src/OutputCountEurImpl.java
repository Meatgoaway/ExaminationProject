import java.math.BigDecimal;

public class OutputCountEurImpl  implements OutputCount{

    private BigDecimal amount;

    public OutputCountEurImpl(String s) {
        if (!s.contains(".") )
            s += ".0";
        this.amount = new BigDecimal( s );
    }


    @Override
    public String outputMoneyCount() {
        return "EURO";
    }

    @Override
    public String outputMoneyCount(boolean stripkop) {
        return "Euro.cent";
    }

}
