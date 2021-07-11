package runnable

object Main {
  println("def main")

  def main(args: Array[String]): Unit = {
    println("def main")
    args foreach println
  }
}
