import com.sun.xml.internal.ws.policy.privateutil.RuntimePolicyUtilsException;

public class JavaObjectMethods {
    static int i = 0;

    public static int getI() {
        i = i + 1;
        return i;
    }

    public static void main(String[] args) {
        JavaObjectMethods obj = new JavaObjectMethods();
        System.out.println(getI());
    }

    @Override
    public String toString() {
        int i = getI();
        System.out.println("Executed");
        throw new RuntimeException();
    }
}
