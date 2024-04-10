package lectures.oops

object Objects {

  // SCALA does not have class-level functionality ('static')

  object Person {  // type + its only instance
    // 'static/class' - level functionality
    val N_EYES = 2

    def canFly: Boolean = false

    // factory method in a singleton object --- sole purpose is to build person given some parameters
    def apply(mother: Person, father: Person): Person = new Person("Bobbie")
  }

  // we write objects and class with the same name
  class Person(val name: String) {
    // instance-level functionality
  }

  // COMPANIONS -- OBJECT AND CLASS IS IN SAME SCOPE ---
  // whole code that we write will reside in either a class and we can access it
  // from an instance or it will reside inside of a singleton class

  def main(args: Array[String]): Unit = {
    // objects have values, vars and method definitions
    // exceptions - objects does not receive parameters
    println(Person.N_EYES)
    println(Person.canFly)

    // Scala object = SINGLETON INSTANCE
    val mary = Person
    val john = Person
    println(mary == john) // true --- points to same instance


    // instantiate two diff instances of the class person
    val prego = new Person("Prego")
    val kartik = new Person("Kartik")
    println(prego == kartik) // false

    val bobbie = Person(prego, kartik) // calls apply method in object
  }

  // Scala application = Scala objects with
  // def main(args: Array[String]): Unit

  // Scala app is turned into JVM app whose entry point have to be static public void main,
}

/*
  Takeaways

    Scala doesnt have static values/methods

    Scala Objects
      - are in their own class
      - are the only instances
      - singleton pattern in one line

    ex -
    object Person {
      val N_EYES = 2
      def canFly: Boolean = false
    }

    Scala Companions
      - can access each others private members
      - Scala is more OO than JAVA


    Scala app
      - def main(args: Array[String]): Unit
      - object Person extends App
*/
