package lectures.oops

object MethodNotations extends App {

  class Person(val name: String, favoriteMovie: String, val age: Int = 0) {
    def likes(movie: String): Boolean = movie == favoriteMovie

    def hangOutWith(person: Person): String = s"${this.name} hangs out with ${person.name}"

    def +(person: Person): String = s"${this.name} hangs out with ${person.name}"

    def unary_! : String = s"$name, what the heck!!"

    def isAlive: Boolean = true

    def apply(): String = s"Hi!, my name is $name and fav movie $favoriteMovie"

    // Question - Answer
    def +(string: String): Person = new Person(name + " (" +string+")", favoriteMovie)

    def unary_+ : Person = new Person(name, favoriteMovie, age + 1)

    def learns(thing: String): String = s"${name} learns ${thing}"
    def learnsScala = this learns "Scala"

    def apply(value: Int) = s"Mary watched ${favoriteMovie} ${value} times"
  }

  var mary = new Person("Mary", "Inception")

  // equivalent
  println(mary.likes("Inception"))
  println(mary likes "Inception")

  // 1) infix notation = operator notation (syntactic sugar)
  // (syntactic sugar) - nicer ways of writing code that are equivalent to more complex or cumbersome ways of writing code
  // more resemblance of natural language
  // --- only works with one parameter


  // 2) "operators" in Scala

  val tom = new Person("Tom", "Fight Club")

  // works when method has only one parameter
  println(mary hangOutWith tom)

  // equivalent
  println(mary + tom)
  println(mary.+(tom))

  // equivalent
  println(1+2)
  println(1.+(2))

  // all methods are operators in Scala

  // 3) prefix notation

  // equivalent
  val x = -1
  val y = 1.unary_-
  // unary_ prefix only works with - + ~ !

  // equivalent
  println(!mary)
  println(mary.unary_!)

  // 4) postfix notation -- functions that do not receive any parameters have the property that they can be used in a postfix
  println(mary.isAlive)
  //  println(mary isAlive)

  // 5) apply

  // equivalent
  println(mary.apply())
  println(mary())


  /*
   1. Overload the + operator
      mary + "the rockstar" => new person "Mary (the rockstar)"

   2. Add an age to the Person class
      Add a unary + operator => new person with the age + 1
      +mary => mary with the age incrementer

   3. Add a "learns" method in the Person class = Mary learns scala
      Add a learnsScala method, calls learns method with "Scala"

   4. Overload the apply method
      mary.apply(2) => "Mary watched inception 2 times"
  */

  println((mary + "the rockstar")())

  println((+mary).age)
  println((+(+mary)).age)

  println(mary.learnsScala)

  println(mary(2))
}
