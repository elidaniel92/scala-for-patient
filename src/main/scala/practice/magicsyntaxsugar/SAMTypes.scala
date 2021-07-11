package practice.magicsyntaxsugar

object SAMTypes {
  // Single Abstract Method Types (SAM Types)

  trait Hello {
    def apply(name: String): Unit
  }

  // Implement Hello using Lambda
  val h1: Hello = (name: String) => println(s"Hello $name") // Optional parameter type

  val function = (name: String) => println(s"Hello $name")
//  val h3: Hello = function //  found String => Unit, required: Hello

  // found String => Unit, required: Hello
  /*val h2: Hello = new Function1[String, Unit] {
    override def apply(name: String): Unit = println(s"Hello $name")
  }*/

  // The type can optionally have other non-abstract members
  // but only one abstract method
  trait Hi {
    def run(name: String): Unit
    def concrete: Int = 42
  }

  val h2: Hi = (name) => println(s"Hi $name")

  // Two not implemented method
  trait Hey {
    def apply(name: String): Unit // abstract
    def run(name: String): Unit // abstract
    def concrete: Int = 42
  }
//  val hey: Hey = (name: String) => println(s"Hey $name") // Not compile


}
