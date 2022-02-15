public class AllTest {

    public static void main(String[] args) {
        boolean a = true;
        OutputCount out1 = new OutputCountTest();
        OutputCount out2 = new OutputCountTest();
        InputCount in = new InputCountImplTest();
        CountCurrency countCurrency = new CountCurrency();

        Assertions.assertEquals("123", in.inputMoneyCount());
        System.out.println("Test inputCount Complete");
        Assertions.assertEquals("ok", countCurrency.inputCurrency());
        System.out.println("Test inputCurrency Complete");
        Assertions.assertEquals("TestCount1", out1.outputMoneyCount());
        System.out.println("Test OutputCount1 Complete");
        Assertions.assertEquals("TestCount2", out2.outputMoneyCount(a));
        System.out.println("Test OutputCount2 Complete");
        countCurrency.writeNumber(out1);
        System.out.println("Test writeNumder Complete");

    }
}
