package practice.systemtype

object Test {
  class Item(val description: String, var price: Double) {
    final override def equals(obj: Any): Boolean = obj match {
      case that: Item => description == that.description// Do not use equals! Equals will throw NPE for null description
      case _ => false
    }
    final override def hashCode(): Int = description.## // null safe version of hashCode
  }
  def main(args: Array[String]): Unit = {
    val itemWithNullValue = new Item(null, 0)
    println(itemWithNullValue.hashCode)
  }
}