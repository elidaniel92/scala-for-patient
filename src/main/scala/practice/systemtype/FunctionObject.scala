package practice.systemtype

object FunctionObject {
  val funcClass1: (Int, Int) => String = new Function2[Int, Int, String] {
    override def apply(v1: Int, v2: Int): String = (v1 / v2).toString
  }

  // ((Int, Int) => String) == Function2[Int, Int, String]
  val funcClass2: (Int, Int) => String = new ((Int, Int) => String) {
    override def apply(v1: Int, v2: Int): String = (v1 / v2).toString
  }

  // Lambda Expression
  val funcLiteral: (Int, Int) => String = (v1: Int, v2: Int) => (v1 / v2).toString
  val funcPatternMatching: (Int, Int) => String = { case(v1, v2) => (v1 / v2).toString }

  funcClass2 // (Int, Int) => String = <function2>
  funcClass2.getClass // Class[_ <: (Int, Int) => String] = class $anon$1

  funcLiteral // (Int, Int) => String = $Lambda$1448/0x00000001007eb840@16ab59b4
  funcLiteral.getClass // Class[_ <: (Int, Int) => String] = class $Lambda$1448/0x00000001007eb840

  funcPatternMatching // (Int, Int) => String = $Lambda$1458/0x00000001007f2040@37934691
  funcPatternMatching.getClass // Class[_ <: (Int, Int) => String] = class $Lambda$1458/0x00000001007f2040

  // Apply syntax sugar
  funcClass1(1,2)
}
