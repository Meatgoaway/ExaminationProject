import java.math.BigDecimal;
import java.sql.Struct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OutputCountRubImpl implements OutputCount {
    private final String[][] SEX = {
            {"", "один", "два", "три", "четыре", "пять", "шесть", "семь", "восемь", "девять"},
            {"", "одна", "две", "три", "четыре", "пять", "шесть", "семь", "восемь", "девять"},
    };
    private final String[] STR100 = {"", "сто", "двести", "триста", "четыреста", "пятьсот", "шестьсот", "семьсот", "восемьсот", "девятьсот"};
    private final String[] STR11 = {"", "десять", "одиннадцать", "двенадцать", "тринадцать", "четырнадцать", "пятнадцать", "шестнадцать", "семнадцать", "восемнадцать", "девятнадцать", "двадцать"};
    private final String[] STR10 = {"", "десять", "двадцать", "тридцать", "сорок", "пятьдесят", "шестьдесят", "семьдесят", "восемьдесят", "девяносто"};
    private final String[][] FORMS = {
            {"копейка", "копейки", "копеек", "1"},
            {"рубль", "рубля", "рублей", "0"},
            {"тысяча", "тысячи", "тысяч", "1"},
            {"миллион", "миллиона", "миллионов", "0"},
            {"миллиард", "миллиарда", "миллиардов", "0"},
            {"триллион", "триллиона", "триллионов", "0"},
    };
    private final BigDecimal amount;
    String concatenationMoneyText = "";
    List segments = new ArrayList();
    private long rub;
    private long rub_tmp;
    private long cent;
    private String cents;


    public OutputCountRubImpl(long l) {
        String s = String.valueOf(l);
        if (!s.contains("."))
            s += ".0";
        this.amount = new BigDecimal(s);
    }

    public OutputCountRubImpl(double l) {
        String s = String.valueOf(l);
        if (!s.contains("."))
            s += ".0";
        this.amount = new BigDecimal(s);
    }

    public OutputCountRubImpl(String s) {
        if (!s.contains("."))
            s += ".0";
        this.amount = new BigDecimal(s);
    }

    @Override
    public String outputMoneyCount() {
        return outputMoneyCount(false);
    }

    @Override
    public String outputMoneyCount(boolean stripkop) {
        splitMoney();
        return textFormBuilder(stripkop);
    }

    private void splitMoney() {
        rub = amount.longValue();
        String[] moi = amount.toString().split("\\.");
        cent = Long.parseLong(moi[1]);
        if (!moi[1].substring(0, 1).equals("0")) {
            if (cent < 10)
                cent *= 10;
        }

        cents = String.valueOf(cent);
        if (cents.length() == 1)
            cents = "0" + cents;
        rub_tmp = rub;

        while (rub_tmp > 999) {
            long seg = rub_tmp / 1000;
            segments.add(rub_tmp - (seg * 1000));
            rub_tmp = seg;
        }
        segments.add(rub_tmp);
        Collections.reverse(segments);
    }

    private String textFormBuilder(boolean stripkop) {
        if (rub == 0) {
            concatenationMoneyText = "ноль " + morphology(0, FORMS[1][0], FORMS[1][1], FORMS[1][2]);
            if (stripkop)
                return concatenationMoneyText;
            else
                return concatenationMoneyText + " " + cent + " " + morphology(cent, FORMS[0][0], FORMS[0][1], FORMS[0][2]);
        }

        int lev = segments.size();
        for (int i = 0; i < segments.size(); i++) {
            int sexi = Integer.parseInt(FORMS[lev][3]);
            int ri = (int) Integer.parseInt(segments.get(i).toString());
            if (ri == 0 && lev > 1) {
                lev--;
                continue;
            }
            String rs = String.valueOf(ri);

            if (rs.length() == 1) rs = "00" + rs;
            if (rs.length() == 2) rs = "0" + rs;

            int r1 = (int) Integer.parseInt(rs.substring(0, 1));
            int r2 = (int) Integer.parseInt(rs.substring(1, 2));
            int r3 = (int) Integer.parseInt(rs.substring(2, 3));
            int r22 = (int) Integer.parseInt(rs.substring(1, 3));

            if (ri > 99) concatenationMoneyText += STR100[r1] + " ";
            if (r22 > 20) {// >20
                concatenationMoneyText += STR10[r2] + " ";
                concatenationMoneyText += SEX[sexi][r3] + " ";
            } else { // <=20
                if (r22 > 9) concatenationMoneyText += STR11[r22 - 9] + " ";
                else concatenationMoneyText += SEX[sexi][r3] + " ";
            }

            concatenationMoneyText += morphology(ri, FORMS[lev][0], FORMS[lev][1], FORMS[lev][2]) + " ";
            lev--;
        }

        if (stripkop) {
            concatenationMoneyText = concatenationMoneyText.replaceAll(" {2,}", " ");
        } else {
            concatenationMoneyText = concatenationMoneyText + "" + cents + " " + morphology(cent, FORMS[0][0], FORMS[0][1], FORMS[0][2]);
            concatenationMoneyText = concatenationMoneyText.replaceAll(" {2,}", " ");
        }
        return concatenationMoneyText;
    }

    private String morphology(long n, String f1, String f2, String f5) {
        n = Math.abs(n) % 100;
        long n1 = n % 10;
        if (n > 10 && n < 20) return f5;
        if (n1 > 1 && n1 < 5) return f2;
        if (n1 == 1) return f1;
        return f5;
    }


}






