import scala.collection.mutable.ArrayBuffer

case class Person(name: String, gender: String, age: Int)
val people = Map[String, Person](
  "A" -> Person("John", "male", 30),
  "B" -> Person("Linda", "female", 25),
  "C" -> Person("Robert", "male", 15),
  "D" -> Person("Daniel", "male", 25),
  "E" -> Person("Susan", "female", 15),
  "F" -> Person("Anne", "female", 20))

val mutablePeople = scala.collection.mutable.Map[String, Person](
  "A" -> Person("John", "male", 30),
  "B" -> Person("Linda", "female", 25),
  "C" -> Person("Robert", "male", 15),
  "D" -> Person("Daniel", "male", 25),
  "E" -> Person("Susan", "female", 15),
  "F" -> Person("Anne", "female", 20))


/**
 * Map(key -> value, key -> value)
 * Map(Tuple2[KeyType, ValueType], Tuple2)
 */

val scores1 = Map("Alice" -> 10, "Bob" -> 3, "Cindy" -> 8) // The type is inferred.
val scores1Alternative = Map(("Alice", 10), ("Bob", 3), ("Cindy", 8))

val scores1 = Map[String, Int](
  new Tuple2[String, Int]("Alice", 10),
  new Tuple2[String, Int]("Bob", 3)
)


// An tuple3, an array of tuple2 or an Map???
val x = ("Alice" -> 10, "Bob" -> 3, "Cindy" -> 8)
x.getClass // tuple3
x._1.getClass // tuple2

// Note: "Alice" -> 10 is similar a function, "Alice" => 10

// Stupid code, just to think that map is similar a function
val scoresPatternMatching: String => Int = {
  case "Alice" => 10
  case "Bob" => 3
}
// get alice
scoresPatternMatching("Alice") // apply method

//scores1.put("Daniel", 27) // Not compile, immutable map

val scores2 = scala.collection.mutable.Map("Alice" -> 10, "Bob" -> 3, "Cindy" -> 8)
// add
scores2.put("Daniel", 27)
scores2 += "Anne" -> 27

// get
scores2("Ane") // A exception is thrown

scores2.contains("Ane")

// Return Any
scores2.getOrElse("Ane", "Not Found")

scores2.get("Anne") // Return Some[Int] or None

val scores3 = scores2.withDefaultValue(0)
val defaultScores = scores3("Name Not Found")

val newPeople1 = people.withDefaultValue(Person("Default", "male", 30))
val defaultValue = newPeople1("Z")

val newPeople2 = people.withDefault((key) => s"There is no $key")
val withValue = newPeople2("Z") // Return java.io.Serializable

/**
 * Note:
 * mutableMap += (key, value) // Add or Update
 * mutableMap(Key) = Value // Add or Update
 */

val mutableScores =  scala.collection.mutable.Map[String, Int]("Alice" -> 10, "Bob" -> 3, "Cindy" -> 8)
// Update, Not Add
mutableScores += "Bob" -> 0

// Update
mutablePeople("A") = Person("Default", "male", 30)
// Add
mutablePeople("Z") = Person("Default", "male", 30)

// Add
mutablePeople += ("W" -> Person("Default", "male", 30))
mutablePeople addOne ("U" -> Person("Default", "male", 30))

// Add multiples args
mutablePeople ++= scala.collection.mutable.Map("X" -> Person("Default", "male", 30), "Y" -> Person("Default", "male", 30))
mutablePeople.addAll(scala.collection.mutable.Map("J" -> Person("Default", "male", 30), "K" -> Person("Default", "male", 30)))

// Remove
mutablePeople -= "W"
mutablePeople remove "W" match {
  case(Some(person)) => println(s"${person.name} was removed")
  case(None) => println("Nobody was removed")
}

// Remove All
mutablePeople --= Array("A", "B")

// Add multiples args
mutablePeople ++= scala.collection.mutable.Map("X" -> Person("Default", "male", 30), "Y" -> Person("Default", "male", 30))
mutablePeople.addAll(scala.collection.mutable.Map("J" -> Person("Default", "male", 30), "K" -> Person("Default", "male", 30)))

// Deprecated
val newMutablePeople1 = mutablePeople + ("W" -> Person("Default", "male", 30)) // Add
val newMutablePeople2 = mutablePeople - "A" // Remove
// += and -= are also deprecated.

// Iterating over Maps

// Deconstruct a tuple2
for {
  (k, v) <- people
} println(s"Key: $k Name: ${v.name}")

/**
 * Note: people.keys and people.keySet has the same implementation
 */

// Only Key
for {
  k <- people.keySet // immutable.Set[String]
} println(s"Key: $k")

// Only Key
for {
  k <- people.keys // Iterable[String]
} println(s"Key: $k")

// Reverse
val simpleMap = Map("A" -> 1, "B" -> 2, "C" -> 3)
for {
  (k, v) <- simpleMap
} yield (v, k)

// Sorted Maps

// ASC
val sortedMap = scala.collection.immutable.SortedMap("B" -> 2, "C" -> 3, "A" -> 1)
val sortedMutableMap = scala.collection.mutable.SortedMap("B" -> 2, "C" -> 3, "A" -> 1)

// Convert Map to SortedMap
Map("B" -> 2, "C" -> 3, "A" -> 1).to(scala.collection.immutable.SortedMap)

// In insertion order
val insertionOrderMap = scala.collection.mutable.LinkedHashMap("B" -> 2, "C" -> 3, "A" -> 1)
insertionOrderMap.addOne("Z" -> 100)

/**
 * Interoperating with Java
 * If you get a Java map from calling a Java method, you may want to convert it to
 * a Scala map so that you can use the pleasant Scala map API. This is also useful
 * if you want to work with a mutable tree map, which Scala doesn’t provide.
 */
// Old Conversion
//val deprecatedConversion1: scala.collection.mutable.Map[String, Int] = scala.collection.JavaConversions.mapAsScalaMap(new java.util.TreeMap[String, Int])
// Also deprecated
val deprecatedConversion2: scala.collection.mutable.Map[String, Int] = scala.collection.JavaConverters.mapAsScalaMap(new java.util.TreeMap[String, Int])

// New version java to scala
val newConversionJavaToScala: scala.collection.mutable.Map[String, Int] = scala.jdk.CollectionConverters.MapHasAsScala(new java.util.TreeMap[String, Int]).asScala

// Zipping and Unzip

val names = Array[String]("Daniel", "John", "Anne")
val scores = Array[Int](10, 20, 30)

val zipNamesAndScores = names.zip(scores)

// Note: It is possible deconstruct list of tuples in a for
val (namesUnZip, scoresUnZip) = zipNamesAndScores.unzip

// Transform in a map[Key, Value] =  keys.zip(values).toMap
zipNamesAndScores.toMap

// Exercises

//1.
val products = Map[String, Double]("P1" -> 100, "P2" -> 200, "P3" -> 300)
val productsWithDiscount = products.map {
  case(k, v) => (k, v * 0.9)
}

//2.
val in = new java.util.Scanner(new java.io.File("myfile.txt"))
val wordFrequency = scala.collection.mutable.Map[String, Int]().withDefaultValue(0)
while (in.hasNext()) {
  in.next().split(' ').filterNot(_.eq(" ")).foreach {
    (word) => {
      wordFrequency(word) = wordFrequency(word) + 1
    }
  }
}
wordFrequency

// Pattern Matching Version
while (in.hasNext()) {
  in.next().split(' ').filterNot(_.eq(" ")).foreach {
    (word: String) => wordFrequency.get(word) match {
      case Some(occurs) => wordFrequency(word) = occurs + 1
      case None => wordFrequency.put(word, 0)
    }
  }
}

//3.

// Chaper 9 Version
def file = scala.io.Source.fromFile("myfile.txt", "UTF-8")

// Scala Version 2.12.12
(file.mkString.split("\\s+") match {
  // Pattern Matching for empty character stranger behavior
  case a: Array[String] if a(0).equals("") => a.tail
  case a =>  a
}).groupBy(identity).mapValues(_.length)


// Scala Version 2.13.3
(file.mkString.split("\\s+") match {
  // Pattern Matching for empty character stranger behavior
  case a: Array[String] if a(0).equals("") => a.tail
  case a =>  a
}).groupBy(identity).view.mapValues(_.length).toMap

//4.

(file.mkString.split("\\s+") match {
  // Pattern Matching for empty character stranger behavior
  case a: Array[String] if a(0).equals("") => a.tail
  case a =>  a
}).groupBy(identity).view.mapValues(_.length).toMap.to(scala.collection.immutable.SortedMap)

//6.

val weekdays = scala.collection.mutable.LinkedHashMap("Monday" -> java.util.Calendar.MONDAY,
  "Tuesday" -> java.util.Calendar.TUESDAY,
  "Wednesday" -> java.util.Calendar.WEDNESDAY,
  "Thursday" -> java.util.Calendar.THURSDAY,
  "Friday" -> java.util.Calendar.FRIDAY,
  "Saturday" -> java.util.Calendar.SATURDAY,
  "Sunday" -> java.util.Calendar.SUNDAY)
assert(weekdays.values.toArray.sameElements(Array(2 to 7: _*).concat(Array(1))))

//7.

// Convert Properties to Scala Map
scala.jdk.CollectionConverters.PropertiesHasAsScala(java.lang.System.getProperties).asScala match {
  case x: scala.collection.mutable.Map[String,String] => {
    val longestKey: Int = x.maxBy(_._1.length)._1.length
    x.foreach {
      // White Space Columns
      x => println(x._1.formatted("%" + (-longestKey) + "s") + " | " +  x._2)
    }
  }
}

//8.

def minMax(values: Array[Int]) = {
  (values.min, values.max)
}

//9.

// Count <, ==, > than v
def exerciseNine1(values: Array[Int], v: Int) = {
  values.foldLeft((0,0,0)) {
    case ((a, b, c), next) if next < v => ((a + 1), b, c)
    case ((a, b, c), next) if next == v => (a, (b + 1), c)
    case ((a, b, c), next) if next > v => (a, b, (c + 1))
  }
}

// Return <, ==, > than v
def exerciseNine2(values: Array[Int], v: Int) = {
  values.groupBy {
    x => {
      if(x < v) s"less than $v"
      else if (x == v) s"equal than $v"
      else s"greater than $v"
    }
  }
}