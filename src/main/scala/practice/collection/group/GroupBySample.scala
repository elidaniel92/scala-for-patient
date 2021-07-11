package practice.collection.group

import scala.collection.mutable.ArrayBuffer
import scala.util.Try

object GroupBySample {
  case class Person(name: String, gender: String, age: Int)
  val people = Array[Person](
    Person("John", "male", 30),
    Person("Linda", "female", 25),
    Person("Robert", "male", 15),
    Person("Daniel", "male", 25),
    Person("Susan", "female", 15),
    Person("Anne", "female", 20))

  val peopleX = Array[Person](
    Person("John", "male", 30),
    Person("Linda", "female", 25),
    Person("Robert", "male", 15),
    Person("Daniel", "male", 25),
    Person("Susan", "female", 15),
    Person("Anne", "female", 20),
    Person("X Name", "X Gender", 20))

  case class Trade(operation: String, price: Double)
  val trades = Array[Trade](
    Trade("Buy", 100),
    Trade("Sell", 150),
    Trade("Buy", 50),
    Trade("Buy", 200),
    Trade("Sell", 300),
    Trade("Sell", 10))

  val numbers = Array(1 to 25: _*)
  val numbers2 = (0 to 25).toArray

  /**
   * GroupBy return a immutable.Map[K, Array[A]]
   * K is a key, A is a list type
   * Each element in a map is a Tuple2[Key, Value]
   */

  // Group By Gender
  people.groupBy(_.gender) // Note: Person Object still has a gender (ambiguity)

  // Print all genders (keys)
  people.groupBy(_.gender).foreach(x => println(x._1))

  // Print all
  people.groupBy(_.gender).foreach(x => println("Gender: " + x._1 + " Total: " + x._2.length))

  // Remove ambiguous column, transforming Person Object in a Tuple2(name, age)
  // Note: check groupMap version
  people.groupBy(_.gender).map {
    x: (String, Array[Person]) => {
      (x._1, (x._2.map {
        y => {
          (y.name, y.age)
        }
      }))
    }
  }
  // Remove all columns except name
  // Note: check groupMap version
  people.groupBy(_.gender).map {
    x: (String, Array[Person]) => {
      (x._1, (x._2.map {
        _.name
      }))
    }
  }

  // Another stupid version, remove the key
  // Note: here map function f: ((String, Array[Person])) => B
  people.groupBy(_.gender).map {
    _._2.map(_.name)
  }

  // Case is a function that receive values of a Tuple2
  // Below return immutable.Map because I return key and value
  // Note: here map function f: ((String, Array[Person])) => (K2, V2)
  people.groupBy(_.gender).map {
    case (key, personArray) => (key, personArray.map(_.name))
  }

  // Below return immutable.Iterable[Array[String]] because I return only value
  // Note: here map function f: ((String, Array[Person])) => B
  people.groupBy(_.gender).map {
    case (key, personArray) => (personArray.map(_.name))
  }

  // Remove key, return two Array[Person]
  people.groupBy(_.gender).map {
    case (p1, p2) => p2
  }

  /**
   * GroupMap
   * "It is equivalent to `groupBy(key).mapValues(_.map(f))`, but more efficient." by ScalaDoc
   * Note: mapValues is deprecated
   */

  // groupMap version
  people.groupMap(_.gender)(x => (x.name, x.age))
  people.groupMap(_.gender)(_.name)

  // GroupBy trade operation (buy/sell)
  trades.groupMap(_.operation)(_.price)

  /**
   * Trade Balance
   */

  val balance = {
    // Total Sell and Total Buy
    val sellAndBuy = trades.groupMapReduce(_.operation)(_.price)(_ + _)
    val totalSell = sellAndBuy.get("Sell") match { case Some(v) => v; case None => 0; }
    val totalBuy = sellAndBuy.get("Buy") match { case Some(v) => v; case None => 0; }
    totalSell - totalBuy
  }

  /**
   * Median Age by Gender
   * Note: partition is a alternative to groupMap if there are two gender
   */

  // Total Age by gender. Not a median
  people.groupMapReduce(_.gender)(_.age)(_ + _)

  // Note: this not return a Array of tuple! Return a immutable.Map[String,Int]
  people.groupMap(_.gender)(_.age).map {
    (x) => (x._1, x._2.sum / x._2.length) // Not a Array[(String, Int)]
  }

  // GroupMap.Map
  val medianAgeByGenderGroupMapImpl: Array[Person] => Map[String,Int]  = (xs) => {
    xs.groupMap(_.gender)(_.age).map {
      case(gender, ageArray) => (gender, ageArray.sum / ageArray.length)
    }
  }

  // foldLeft.map
  val medianAgeByGenderFoldLeftMapImpl: Array[Person] => Map[String,Int]  = (xs) => {
    (xs.foldLeft(scala.collection.mutable.Map.empty[String, (Int, Int)]) {
      (acc, elem) => {
        acc.get(elem.gender) match {
          case Some(v) => acc.update(elem.gender, (v._1 + elem.age, v._2 + 1)); acc;
          case None => acc(elem.gender) = (elem.age, 1); acc;
        }
      }
    }.map((x) => (x._1, x._2._1 / x._2._2))).toMap
  }

  // foldLeft.map Array version
  val medianAgeFoldArray: Array[Person] => Array[(String, Int)] = (xs: Array[Person]) => {
    xs.foldLeft(new Array[(String, Int, Int)](0)) {
      (acc, elem) => {
        val index = acc.indexWhere(_._1 == elem.gender)
        if(index != -1) {
          val (gender, totalAge, count) = acc(index)
          acc(index) = (gender, totalAge + elem.age, count + 1)
          acc
        } else {
          acc.appended((elem.gender, elem.age, 1))
        }
      }
    }.map {
      case (gender, totalAge, count) => (gender, totalAge / count)
    }
  }

  // foldLeft.map ArrayBuffer version
  val medianAgeFoldArrayBuffer: Array[Person] => ArrayBuffer[(String, Int)] = (xs: Array[Person]) => {
    xs.foldLeft(new ArrayBuffer[(String, Int, Int)](0)) {
      (acc, elem) => {
        val index = acc.indexWhere(_._1 == elem.gender)
        if(index != -1) {
          val (gender, totalAge, count) = acc(index)
          acc(index) = (gender, totalAge + elem.age, count + 1)
          acc
        } else {
          acc.appended((elem.gender, elem.age, 1))
          //acc.addOne((elem.gender, elem.age, 1))
        }
      }
    }.map {
      case (gender, totalAge, count) => (gender, totalAge / count)
    }
  }

  // Performance Test Benchmark
  val medianAgeByGenderPerformance: (Array[Person] => Any) => Long = (f) => {
    val start: Long = java.lang.System.nanoTime()
    val people2 = for(i <- 0 to 100000) yield {
      Person("Name",
        if(scala.util.Random.nextInt(2) == 0) "female" else "male"
        ,scala.util.Random.nextInt(80))
    }
    f(people2.toArray)
    val end: Long = java.lang.System.nanoTime()
    end - start
  }

  {
    println("GroupMap " + ((for (i <- 1 to 25) yield {
      medianAgeByGenderPerformance(medianAgeByGenderGroupMapImpl)
    }).sum / 25).formatted("%,d"))

    println("FoldLeft " + ((for (i <- 1 to 25) yield {
      medianAgeByGenderPerformance(medianAgeByGenderFoldLeftMapImpl)
    }).sum / 25).formatted("%,d"))

    println("FoldLeft Array Impl " + ((for (i <- 1 to 25) yield {
      medianAgeByGenderPerformance(medianAgeFoldArray)
    }).sum / 25).formatted("%,d"))

    println("FoldLeft ArrayBuffer Impl " + ((for (i <- 1 to 25) yield {
      medianAgeByGenderPerformance(medianAgeFoldArrayBuffer)
    }).sum / 25).formatted("%,d"))
  }

  // create a Array[Array[Person]], each position has two person
  people.grouped(2).toArray
  numbers.grouped(5).toArray

  numbers.sliding(5,5).toArray // like grouped(5)

  // (1, 6, 11, 16 ...)
  numbers.grouped(5).toArray.map(_.head)
  numbers.grouped(5).toArray.map(_.take(1))
  numbers.sliding(1,5).toArray

  // Multiplication Table for 5
  numbers2.grouped(5).toArray.map(_.head)
  numbers2.grouped(5).toArray.map(_.take(1))
  numbers2.sliding(1,5).toArray

  numbers.toArray.sliding(2,1).toArray
  numbers.toArray.sliding(5,2).toArray
}
