package lectures.oops

object AnonymousClasses extends App {

  abstract class Animal {
    def eat: Unit
  }

  // anonymous class
  val funnyAnimal: Animal = new Animal {
    override def eat: Unit = println("hahahahahahha")
  } // code inside {} - anonymous class

  // compiler takes whatever is inside {} = AnonymousClasses$$anon$1
  //  class AnonymousClasses$$anon$1 extends Animal {
  //    override def eat: Unit = println("hahahahahahha")
  //  }
  // val funnyAnimal: Animal = new AnonymousClasses$$anon$1

  println(funnyAnimal.getClass)

  class Person(name: String) {
    def sayHi: Unit = println(s"My name is $name, how can i help")
  }

  val jim = new Person("Jim") {
    override def sayHi: Unit = println(s"My name is name, how can i help")
  }

  // anonymous classes work for both abstract and non-abstract classes
}

/*
Takeaways

- we can instantiate types and override fields or methods on the spot
 trait Animal {
  def eat: Unit
 }

 val predator = new Animal {
    override def eat: Unit = println("RAWR!")
 }

 Rules
 - pass in required constructor arguments if needed
 - implement all abstract field/methods


Works for trait and classes (abstract or not)
*/



