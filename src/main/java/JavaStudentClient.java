public class JavaStudentClient {
    public static void main(String[] args) {
        ScalaStudent student = new ScalaStudent("Anne", 20);

        // get and set
        System.out.println(student.getName());
        student.setName("John");
        System.out.println(student.getName());
        student.name_$eq("Daniel");
        System.out.println(student.name());
    }
}
