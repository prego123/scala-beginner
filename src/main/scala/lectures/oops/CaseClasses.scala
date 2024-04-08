package lectures.oops

object CaseClasses extends App {
  /*
    equals, hashCode, toString

    Case classes are an exceptionally useful shorthand
    for defining a class and a companion object and a lot of sensible
    defaults in one go.

    They are really perfect for creating this kind of lightweight data
    holding classes with really minimum of hassle
  */

  case class Person(name: String, age: Int)

  // 1. class parameters are fields
  val jim = new Person("Jim", 34)
  println(jim.name)

  // 2. sensible toString
  println(jim.toString)

  println(jim) // delegates to toString method
  // println(instance) = println(instance.toString) -- syntactic sugar

  // 3. equals and hashCode implemented OOTB
  val jim2 = new Person("Jim", 34)
  println(jim == jim2) // true
  // in case, case was not used - prints false
  // jim and jim2 - two diff instances of class Person but equals method was not implemented
  // so, the default from any ref was picked which by default for diff references

  // 4. CCs have handy copy method
  val jim3 = jim.copy(age = 45) // copy creates a new instance of jim class with age 45
  println(jim3)

  // 5. CCs have companion objects
  val thePerson = Person
  val mary = Person("Mary", 23)

  // 6. CCs are serializable - send instances of ccs through the n/w and in btw JVMs (Akka framework)
  // Akks - deals with sending serializable messages through the n/w and our messages
  // are in general in practice CCs

  // 7. CCs have extractor patterns - CCs used in PATTERN MATCHING

  case object UnitedKingdom {
    def name: String = "The UK of GB and NI"
  }
  // case objects have the same property as CCs
  // except - they don't get companion objects because they are their own companion objects

  // without using case in the above class the two print line would be cryptic


  /*
    Expand MyList = use case classes and case objects
  */
}


/*
  Takeaways

  1. Quick lightweight data structures with little boilerplate
    case class Person(name: String, age: Int)

  2. Companions already implemented
    val bob = Person("Bob", 26)

  3. Sensible equals, hashCode, toString

  4. Auto-promoted params to fields
    bob.name

  5. Cloning

  6. Serializable and extractable in pattern matching

  case objects acts same as case classes only that they are objects
*/
