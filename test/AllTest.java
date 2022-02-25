import java.io.*;
import java.util.Scanner;

public class AllTest {

    public static void main(String[] args)  {

        boolean a = true;
        Scanner scanner = new Scanner("ok\nok");
        OutputCount out1 = new OutputCountTest();
        OutputCount out2 = new OutputCountTest();
        InputCount inputCount = new InputCountImplTest();
        InputCount inp = new InputCountImpl(scanner);
        CountCurrency countCurrency = new CountCurrency(scanner, inputCount.inputMoneyCount());

        Assertions.assertEquals("123", inputCount.inputMoneyCount());
        System.out.println("Test inputCount interface Complete");

        Assertions.assertEquals("ok", countCurrency.inputCurrency());
        System.out.println("Test inputCurrency Complete");
        
        Assertions.assertEquals("ok", inp.inputMoneyCount());
        System.out.println("Test inputCountImpl Complete");

        Assertions.assertEquals("TestCount1", out1.outputMoneyCount());
        System.out.println("Test OutputCount1 Complete");

        Assertions.assertEquals("TestCount2", out2.outputMoneyCount(a));
        System.out.println("Test OutputCount2 Complete");

        countCurrency.writeNumber(out1);
        System.out.println("Test writeNumber Complete");

        scanner.close();
    }
}
