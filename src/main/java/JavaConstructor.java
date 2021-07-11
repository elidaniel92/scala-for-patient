public class JavaConstructor {
    public String str = "";

    public JavaConstructor(String strParameter) {
        str = "String = " + strParameter;
    }
    public JavaConstructor(int intParameter) {
        str = "Integer = " + String.valueOf(intParameter);
    }
    public JavaConstructor(double doubleParameter) {
        str = "Double = " + String.valueOf(doubleParameter);
    }

    public static void main(String[] args) {
        JavaConstructor test = new JavaConstructor(2.0d);
        System.out.println(test.str);
    }
}
