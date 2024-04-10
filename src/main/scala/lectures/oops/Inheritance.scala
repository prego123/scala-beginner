package lectures.oops

object Inheritance extends App{

  // single class inheritance
  class Animal { // animal is a superclass of cat
    val creatureType = "wild creature"
    def eat = println("nomnom")
    // private def eat = println("nomnom") ---- only accessible to this class only
    // protected def eat = println("nomnom") ----- only accessible to this class only and subclasses only but not outside subclass
    // final def eat = println("nomnom")
  }

  class Cat extends Animal { // cat is a subclass of animal
    def crunch = {
      eat
      println("crunch crunch")
    }
  }

  val cat = new Cat
  cat.eat

  // constructors
  class Person(name: String, age: Int) {
    def this(name: String) = this(name, 0)
  }

  class Adult(name: String, age: Int, idCard: String) extends Person(name, age)
  // class Adult(name: String, age: Int, idCard: String) extends Person(name) --- calls auxiliary constructor
  // JVM rule - needs to call a constructor of person before you call constructor of adult
  // compiler forces you to guarantee that there is a correct super constructor to call when using such a derivable class

  // overriding -- method call goes to the most overridden version whenever possible
  class Dog(override val creatureType: String = "domestic") extends Animal {
    // override val creatureType: String = "domestic"
    override def eat = {
      super.eat
      println("Crunch crunch")
    }
  }

  //  class Dog(dogType: String) extends Animal {
  //    override val creatureType: String = dogType
  //  } -- same as above

  val dog = new Dog("K9")
  dog.eat // --- calls overriden method line 36
  println(dog.creatureType)



  // type substitution (broad: polymorphism)
  val unknownAnimal: Animal = new Dog("K9")
  unknownAnimal.eat

  // overRIDING (supplying a different implementation in derived classes) vs
  // overLOADING (supplying multiple methods with different signatures but with the same name in the same class)

  // super - reference a method or a field from a parent class

  // preventing overrides -
  // 1. final keyword on member
  // 2. use final on the entire class
  // 3. seal the class = extend classes in this file but prevent extensions in the other files -- sealed keyword
  // sealed keyword -- exhaustive in your hierarchy
}
