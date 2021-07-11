Array(1 to 5: _*).mkString
Array(1 to 5: _*).mkString("-")
Array(1 to 5: _*).mkString("-Infinity... ", "-", " ...Infinity")

val txt = "Word1 Word2 0   Word3  1     "

// Split regex
val wordsAndNumbers = txt.split("\\s+")
assert(wordsAndNumbers.length == 5)

// Stranger Behavior when first character is empty.
" A B      C    ".split("\\s+")


scala.io.Source.fromFile("myfile.txt", "UTF-8").mkString.split("\\s+") match {
  // Pattern Matching for empty character stranger behavior
  case a: Array[String] if a(0).equals("") => a.tail
  case a =>  a
}