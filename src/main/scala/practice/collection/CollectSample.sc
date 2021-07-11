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

// Multiples Filters
peopleX.collect {
  case(person: Person) if person.gender == "male" => person
  case(person: Person) if person.gender == "female" => person
}

// One Not Filter
peopleX.collect {
  case(person: Person) if person.gender == "X Gender" => None
  case(person: Person) => person
}

val newPeople: Array[Person] = peopleX.collect {
  case(person: Person) if person.gender == "X Gender" => person.copy(gender = "Non-normative gender")
  case(person: Person) => {
    person.copy(gender = "Heterosexual")
  }
}

val soldierList: Array[String] = peopleX.collect {
  case(person: Person) if person.gender == "male" && person.age >= 18 => s"Soldier: ${person.name}"
}