package test;

public class Test {

    public static void main(String[] args) {
        for (CategoryName myEnum : CategoryName.values()) {
            myEnum.toString();
            System.out.println();
        }
        System.out.println(CategoryName.AA);

    }
}
