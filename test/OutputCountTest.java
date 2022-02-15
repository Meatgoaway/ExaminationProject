public class OutputCountTest implements OutputCount {
    @Override
    public String outputMoneyCount() {
        return "TestCount1";
    }

    @Override
    public String outputMoneyCount(boolean stripkop) {
        return "TestCount2";
    }
}

