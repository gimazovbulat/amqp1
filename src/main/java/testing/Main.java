package testing;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        MyClass myClass = new MyClass(2);
        MyClass myClass2 = new MyClass(3);
        MyClass myClass3 = new MyClass(4);
        MyClass myClass4 = new MyClass(5);
        MyClass myClass5 = new MyClass(6);

        MyClass[] arr = new MyClass[5];
        arr[0] = myClass;
        arr[1] = myClass2;
        arr[2] = myClass3;
        arr[3] = myClass4;
        arr[4] = myClass5;

        Filter<MyClass> filter = (myClassss) -> myClassss.getNum() % 2 == 0;

        Utils utils = new Utils();
        Arrays.stream(utils.filter(arr, filter)).forEach(m -> System.out.println(m.getNum()));
    }
}
