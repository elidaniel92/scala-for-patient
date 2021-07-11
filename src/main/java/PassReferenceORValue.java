import scala.Int;

import java.util.ArrayList;
import java.util.List;

public class PassReferenceORValue {
    // pass-by-reference or pass-by-value?
    public static void main(String[] args) {
        main1();
    }

    private static void main1() {
        Model model = new Model();

        method1(model.integer, model.str, model.list);
        System.out.println("Method 1");
        System.out.println("int: " + model.integer);
        System.out.println("String: " + model.str);
        System.out.println("List");
        model.list.forEach(System.out::println);

        method2(model.integerObject, model.strObject);
        System.out.println("\nMethod 2");
        System.out.println("int Object: " + model.integerObject);
        System.out.println("String Object: " + model.strObject);

        method3(model.integerObject);
        System.out.println("\nMethod 3");
        System.out.println("int Object: " + model.integerObject);

        method4(model);
        System.out.println("\nMethod 4");
        System.out.println("int Object: " + model.integerObject);
        System.out.println("String Object: " + model.strObject);
    }

    public static void method1(int integer, String str, List<String> list) {
        integer = 1000;
        str = "Update Str";
        list.remove(0);
        list.set(0, "Update");
        list.set(1, "Update");
    }

    public static void method2(int integer, String str) {
        integer = 1000;
        str = "Update Str";
    }

    public static void method3(Integer integer) {
        integer = 1000;
    }

    private static void method4(Model model) {
        model.strObject = "Update Str Object";
        model.integerObject = 1000;
    }

    public static class Model {
        int integer = 0;
        String str = "Str";

        List<String> list = new ArrayList();
        Integer integerObject = new Integer(0);
        String strObject = new String("Str Object");

        public Model() {
            list.add("elem Str1");
            list.add("elem Str2");
            list.add("elem Str3");
        }
    }
}

