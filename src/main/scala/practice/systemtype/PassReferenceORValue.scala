package practice.systemtype

object PassReferenceORValue extends App {
  var str: String = "Str"

  def method(str: String): Unit = {
//    str = "new value"
  }
}
