public class Main {
    public static void main(String[] args) {
        IntegerList integerList = new IntegerListImpl();
        integerList.add(1);
        integerList.add(2);
        integerList.add(3);
        integerList.add(4);
        integerList.add(5);
        integerList.remove(3);
        integerList.clear();
    }
}
