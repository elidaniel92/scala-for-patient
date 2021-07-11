def solution(a: Array[Int], k: Int): Array[Int] = {
  k % a.length match {
    case(rotatedKTimes: Int) if rotatedKTimes > 0 => {
      val newArray = new Array[Int](a.length)
      for(i <- 0 until a.length) {
        newArray((i + rotatedKTimes) % a.length) = a(i)
      }
      newArray
    }
    case(java.lang.ArithmeticException) => a
    case(_) => a // case Zero or
  }
}
