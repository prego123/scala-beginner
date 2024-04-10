package lectures.oops

object AbstractDataTypes extends App {

  // need when you want some fields and methods blank and unimplemented

  // abstract -- when you dont supply values so that subclass do it for you
  abstract class Animal {
    val creatureType: String
    def eat: Unit
  }

  class Dog extends Animal {
    override val creatureType = "Canine"
    override def eat = println("Crunch crunch")
  }

  // traits -- created by keyword traits
  // they can be inherited along classes
  trait Carnivore {
    def eat(animal: Animal): Unit
    val preferredMeal: String = "meat"
  }

  // mix multiple trait
  trait ColdBlooded
  class Crocodile extends Animal with Carnivore with ColdBlooded {
    val creatureType = "croc"
    def eat = println("nom nom")
    def eat(animal: Animal): Unit = s"I'm a croc and I'm eating ${animal.creatureType}"
  }

  val dog = new Dog
  val croc = new Crocodile
  croc.eat(dog)

  /*
    Takeaways

       abstract ---- can have both abstract and non abstract types
       ex -
        abstract class Animal {
          val creatureType: String = "abcd"
          def eat: Unit = println("123")
        }
       1. do have constructor parameters
       3. is a type of thing

       traits ---- can have both abstract and non abstract types
       1. do not have constructor parameters
       2. multiple traits may be inherited by the same class
       3. traits are behaviour

       all classes that we use like string like set and all user derived classes will extend any ref
  */
}
