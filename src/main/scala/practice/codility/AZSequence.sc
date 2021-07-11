def loopVersion(i: Int): Array[Int] = {
  var result = new Array[Int](0)
  var value = i - 1
  while(value >= 26) {
    result = Array(value % 26 + 1).concat(result);
    value = value / 26 - 1;
  }
  Array(value + 1).concat(result);
}

val intToAlphabetSequence: Int => Array[Int] = {
  case value if value <= 26 => Array(value)
//  case value => intToAlphabetSequence((value - 1) / 26).concat(Array((value - 1) % 26 + 1))
  case value => intToAlphabetSequence((value - 1) / 26).concat(intToAlphabetSequence((value - 1) % 26 + 1))
}

assert(intToAlphabetSequence(0) sameElements Array(0))
assert(intToAlphabetSequence(1) sameElements Array(1))
assert(intToAlphabetSequence(26) sameElements Array(26))
assert(intToAlphabetSequence(27) sameElements Array(1, 1))
assert(intToAlphabetSequence(52) sameElements Array(1, 26))
assert(intToAlphabetSequence(53) sameElements Array(2, 1))
assert(intToAlphabetSequence(78) sameElements Array(2, 26))
assert(intToAlphabetSequence(26*26+26) sameElements Array(26, 26))
assert(intToAlphabetSequence(26*26+26+1) sameElements Array(1, 1, 1))
assert(intToAlphabetSequence(26+26*26+26*26*26) sameElements Array(26, 26, 26))
assert(intToAlphabetSequence(26+26*26+26*26*26+1) sameElements Array(1, 1, 1, 1))
assert(intToAlphabetSequence(26+26*26+26*26*26+26*26*26*26) sameElements Array(26, 26, 26, 26))

assert(loopVersion(0) sameElements Array(0))
assert(loopVersion(1) sameElements Array(1))
assert(loopVersion(26) sameElements Array(26))
assert(loopVersion(27) sameElements Array(1, 1))
assert(loopVersion(52) sameElements Array(1, 26))
assert(loopVersion(53) sameElements Array(2, 1))
assert(loopVersion(78) sameElements Array(2, 26))
assert(loopVersion(26*26+26) sameElements Array(26, 26))
assert(loopVersion(26*26+26+1) sameElements Array(1, 1, 1))
assert(loopVersion(26+26*26+26*26*26) sameElements Array(26, 26, 26))
assert(loopVersion(26+26*26+26*26*26+1) sameElements Array(1, 1, 1, 1))
assert(loopVersion(26+26*26+26*26*26+26*26*26*26) sameElements Array(26, 26, 26, 26))