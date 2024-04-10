package lectures.oops

object OopsBasics extends App {
  val person = new Person("John", 26)
  println(person.age)
  println(person.x)
  person.greet("Daniel")
  person.greet()

  val author = new Writer("Charles", "Dicken", 1812);
  val imposter = new Writer("Charles", "Dicken", 1812);
  val novel = new Novel("Great Expectations", 1861, author)

  println(novel.authorAge())
  println(novel.isWrittenBy(author)) // true
  println(novel.isWrittenBy(imposter)) // false --- two instances of the same class with actually the same data --- equality

  val counter = new Counter
  counter.increment.print
  counter.increment.increment.print

  counter.increment(10).print
}

// constructor
class Person(name: String, val age: Int = 0) {
  // body
  val x = 2

  println(1+3)

  def greet(name: String): Unit = println(s"${this.name} says: Hi, $name")

  // overloading
  def greet(): Unit = println(s"Hi, I am $name")
  // def greet(): Int = 47 ------ not subject to overloading

  // multiple constructors
  def this(name: String) = this(name, 0)
  def this() = this("John")
}
// class parameters are not fields

/*
  Novel and writer

  write: first name, surname, year
    - method - full name

  novel: name, year of release, author
    - method - authorAge
             - isWrittenBy(author)
             - copy (new year of release) = new instance of Novel
*/

class Writer(firstName: String, lastName: String, val year: Int) {
  def fullName(): String = firstName+" "+lastName
}

class Novel(name: String, year: Int, author: Writer) {
  def authorAge(): Int = year - author.year

  def isWrittenBy(author: Writer): Boolean = author == this.author

  def copy(newYear: Int): Novel = new Novel(name, year, author)
}

/*
  Counter class
    - receives an int value
    - method current count
    - method to increment/decrement => new Counter
    - overload inc/dec to receive an amount
*/

class Counter(val count: Int = 0) {
  // immutability - declaring vals for primitive types but extend it to the classes and objects --
  // - instances are fixed and cannot be modified inside
  // - modify an instance you actually need to return a new instance

  def increment = {
    println("Incrementing")
    new Counter(count + 1)
  }

  def decrement = {
    println("Decrementing")
    new Counter(count - 1)
  }

  def increment(n : Int): Counter = {
    if(n <= 0) this
    else increment.increment(n-1)
  }

  def decrement(n: Int): Counter = {
    if (n <= 0) this
    else decrement.decrement(n - 1)
  }

  def print = println(count)
}

