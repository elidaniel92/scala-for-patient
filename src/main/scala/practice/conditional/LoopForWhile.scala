package practice.conditional

object LoopForWhile {
  // 0, 0+2, 2+2, 4+2 ...
  for(i <- 0 until 10 by 2) {
    println(i)
  }

  // 1, 1+2, 3+2, 5+2 ...
  for(i <- 1 until 10 by 2) {
    println(i)
  }

  // Only even
  for(i <- 0 until 10 if i % 2 == 0) {
    println(i)
  }

  // Only odd
  for(i <- 0 until 10 if i % 2 != 0) {
    println(i)
  }

  // IndexedSeq[(Int, Int)]
  val tuples = for(i <- 1 to 10) yield (i, -i)

  // Deconstruct Tuples
  for((a, b) <- tuples) yield a + b
}
