def solution(n: Int): Int = {
  val binaryStr = n.toBinaryString
  (binaryStr.foldLeft((0,0)) {
    (acc, x) => {
      if(x == '1') {
        if(acc._2 > acc._1) {
          (acc._2, 0)
        } else {
          (acc._1, 0)
        }
      } else {
        (acc._1, acc._2 + 1)
      }
    }
  })._1
}


def solution2(n: Int): Int = {
  val binaryStr = n.toBinaryString
  val (maxGap, count) = (binaryStr.foldLeft((0,0)) {
    case((maxGap, newGap), '1') => if(newGap > maxGap) (newGap, 0) else (maxGap, 0)
    case((maxGap, newGap), zero) => (maxGap, newGap + 1)
  })
  maxGap
}