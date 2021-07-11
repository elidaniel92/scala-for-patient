public class MutableParameters {

    public static void main(String[] args) {
        new MutableParameters().method(0, "A", new Model(0));
    }

    public void method(int intParameter, String strParameter, Model objectParameter) {
        intParameter = 100;
        strParameter = "B";
        objectParameter = new Model(100);

        System.out.println(intParameter + " " + strParameter + " " + objectParameter.attribute);
    }

    public static class Model {
        public Model(int attribute) {
            this.attribute = attribute;
        }
        int attribute = 0;
    }
}
