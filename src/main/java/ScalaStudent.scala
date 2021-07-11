import scala.beans.BeanProperty

class ScalaStudent(@BeanProperty var name: String, age: Int) {
  // There is no age field only age parameter
  println(age)
}
