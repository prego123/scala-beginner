package lectures.oops

object Generics extends App {
  class MyList[+A] { //Generic type
    // use the type A
    def add[B >: A](element: B): MyList[B] = ???
    /*
     A = cat
     B = Animal
    */
  }
  // class MyList[+A] { //Generic type
  //    // use the type A
  //    def add(element: A): MyList[A] = ???
  //  }

  //  trait MyList[A] { can also be a Generic type
  //    // use the type A
  //  }

  class MyMap[Key, Value]
  val listOfIntegers = new MyList[Int]
  val listOfStrings = new MyList[String]


  // generic methods
  object MyList {
    def empty[A]: MyList[A] = ???
  }

  val emptyListOfIntegers = MyList.empty[Int] // return list of integers

  // variants problem
  class Animal
  class Cat extends Animal
  class Dog extends Animal

  // 1. yes List[Cat] extends List[Animals] = COVARIANT
  class CovariantList[+A]
  val animal: Animal = new Cat
  val animalList: CovariantList[Animal] = new CovariantList[Cat]
  // animalList.add(new Dog) ??? HARD QUESTION => we return list of animals

  // 2. no = INVARIANCE
  class InvariantList[A] // these classes are each in its own world and you can substitute one for another
  // val invariantList: InvariantList[Animal] = new InvariantList[Cat]   --- wrong -- error
  val invariantList: InvariantList[Animal] = new InvariantList[Animal]

  // 3. Hell no - CONTRAVARIANT
  class ContravariantList[-A]
  val contravariantList: ContravariantList[Cat] = new ContravariantList[Animal]  // cat cant be replaced by animal

  class Trainer[-A]
  val trainer: ContravariantList[Cat] = new ContravariantList[Animal] // but cat cn be trained by animal


  // bounded type ---- allow you to use your generic classes only for certain types that are either a subclass of a different type or a superclass of a different type
  class Cage[A <: Animal](animal: A)  // only accepts those types that are subtypes of animal
  val cage = new Cage(new Dog)

  // class Car
  // cage type needs proper bounded type
  // val newCage = new Cage(new Car) //inferred type arguments [lectures.oops.Generics.Car] do not conform to value <local Cage>'s type parameter bounds [A <: lectures.oops.Generics.Animal]

  // class Cage[A >: Animal](animal: A) // only accepts those types that are super types of animal
  // val cage = new Cage(new Dog)

  // expand MyList to be generic
}


/*
  Takeaways

    -  Use the same code on many (potentially unrelated) types
        trait List[T] {
          def add(ele: T)
        }


    -  Generics methods
        object List {
          def single[A](ele: T): List[A] = ???
        }

    - Variance: if B extends A, should List[B] extend List[A]?
        trait List[+A] - yes(Covariance)
        trait List[A] - no(Invariance) - default
        trait List[-A] - hell no (Contravariance)

    - Bounded types
        class Car
        class SuperCar extends Car
        class Garage[T <: Car](car: T)
*/
