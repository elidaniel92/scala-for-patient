class X {
  override def toString: String = { println("Executed toStr"); super.toString; }
  override def hashCode(): Int = { println("Executed hC"); super.hashCode; }
}
val x = new X