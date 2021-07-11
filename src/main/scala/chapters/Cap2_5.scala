package chapters

object Cap2_5 extends App {
  try {
    val x = 1/0
  } finally {
    println("Do something")
  }
  println("Do otherthing")
}
