/**
 * Enclosing Class Outer Class
 * Nested Class Inner Class
 */

import scala.collection.mutable.ArrayBuffer
class Sport(name: String) { mySport => // This syntax makes the val outer refer to Sport3.this
  override def toString: String = name
  class Athlete(val name: String, val born: Int) {
    //ArrayBuffer[All Class Athlete3 from Sport Instance
    val inspirations = new ArrayBuffer[Sport#Athlete]  //  “An Athlete of Any Sport.”
    override def toString: String = s"name: $name, born: $born"

    // mySport is an alias for Enclosing Class Instance
    def sport: Sport = mySport // Access the this reference of the enclosing class
    def inspirationsFrom(sportInspiration: Sport): Array[sportInspiration.Athlete] = inspirations.filter(_.sport == sportInspiration).map(_.asInstanceOf[sportInspiration.Athlete]).toArray

    // Not compile in Intellij
    def inspirationsFromMySport: Array[mySport.Athlete] = inspirationsFrom(mySport)
  }
  val athletes: ArrayBuffer[Athlete] = new ArrayBuffer[Athlete]
  def addAthlete(name: String, born: Int): Athlete = {
    val athlete = new Athlete(name, born)
    athletes += athlete
    athlete
  }
}

val football = new Sport("Football")
val pele = football.addAthlete("Pele", 1940)
val basketball = new Sport("Basketball")
val jordan = basketball.addAthlete("Michael Jordan", 1963)
val leBron = basketball.addAthlete("LeBron James", 1984)

leBron.inspirations += jordan
leBron.inspirations += pele
//football.athletes += leBron // Not Compile, leBron is not a football player!

// 
def method = {
  val leBronInspiration: ArrayBuffer[Sport#Athlete] = leBron.inspirations
  val leBronBasketballInspiration: Array[basketball.Athlete] = leBron.inspirationsFromMySport
  // Not Compile in Intellj
  val leBronFootballInspiration: Array[football.Athlete] = leBron.inspirationsFrom(football)
  (leBronInspiration, leBronBasketballInspiration, leBronFootballInspiration)
}


import scala.collection.mutable.ArrayBuffer
import scala.collection.immutable.Seq
class Country(val name: String) {
  private val personList = new ArrayBuffer[Person]
  class Person(val name: String) {
    private val friendList = new scala.collection.mutable.HashSet[Country#Person]()
    override def toString: String = name
    def friends = friendList.toSeq
    def addFriend(friend: Country#Person): Unit = friendList.addOne(friend)
    def friendsFrom(country: Country): Seq[country.Person] = friendList.withFilter(_ match { case person: country.Person => true; case _ => false}).map(_.asInstanceOf[country.Person]).toSeq
    def friendsFromMyCountry: Seq[Country.this.Person] = friendsFrom(Country.this)
//    def friendsFromAbroad:
  }
  def newPerson(name: String): Person = {
    val person = new Person(name)
    personList.addOne(person)
    person
  }
  def people = personList.toSeq
  override def toString: String = name
}
val usa = new Country("USA")
val brazil = new Country("Brazil")
val germany = new Country("Germany")

val david = usa.newPerson("David")
val susan = usa.newPerson("Susan")
val joao = brazil.newPerson("João")
val maria = brazil.newPerson("Maria")
val klaus = germany.newPerson("Klaus")

susan.addFriend(david)

joao.addFriend(susan)
joao.addFriend(david)
joao.addFriend(maria)
joao.addFriend(klaus)

val usaFriends: Seq[usa.Person] = joao.friendsFrom(usa)


// Enum

abstract class Enum {
  class Value(val value: String) { // Run Primary Constructor for each instance, each instance has your own class Value
    def apply(): String = value
  }
  def Value(value: String) = new Value(value)
}

object Type1 extends Enum {
  val v1 = Value("A")
  val v2 = Value("B")
}

object Type2 extends Enum {
  type Type2 = Value
  val v1 = Value("A")
  val v2 = Value("B")
}

val Type3 = new Enum {
  val v1 = Value("A")
  val v2 = Value("B")
}

def method1(x: Type1.Value) = x()
def method2(x: Type3.Value) = x()

// Alias Sample
import Type2._
def method3(x: Type2) = x()

def compile = {
  method1(Type1.v2)
  method2(Type3.v2)
}

def notCompile1 = {
  method1(Type2.v2)
}

def notCompile2 = {
  method2(Type2.v2)
}