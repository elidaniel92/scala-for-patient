public class StringBehavior {
    public static void main(String[] args) {
        /**
         * Strings are immutable.
         * Literal String use Pool
         */

        String str = "A";
        String myCopy = str;
        str = "Change";
        System.out.println("myCopy = " + myCopy);
        System.out.println("str == \"Change\" -> " + (str == "Change"));

        str = new String("A");
        myCopy = str;
        System.out.println(str == myCopy);

        char value[] = new char[3];

        for (char c: value) {
            System.out.println("c = " + c);
        }
        value[0] = 'Z';

        for (char c: value) {
            System.out.println("c = " + c);
        }
    }
}
