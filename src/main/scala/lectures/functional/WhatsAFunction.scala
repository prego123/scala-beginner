package lectures.functional

object WhatsAFunction extends App {

  // use functions as first class elements
  // problem: oop ---- JVM was designed as the instances for the classes and nothing else

  val doubler = new MyFunction[Int, Int] {
    override def apply(element: Int): Int = element * 2
  }

  println(doubler(2))

  // function types = Function[A, B]
  val stringToIntConverter = new Function1[String, Int] {
    override def apply (string: String): Int = string.toInt
  }

  println(stringToIntConverter("3") + 4)

  val adder: ((Int, Int) => Int) = new Function2[Int, Int, Int] {
    override def apply(a: Int, b: Int): Int = a + b
  }

  // Function types Function2[A, B, R] === (A, B) => R

  // ALL SCALA FUNCTIONS ARE OBJECTS OR INSTANCES OF CLASSES DERIVING FROM FUNCTION1, FUNCTION2

  // exercise
  // 1.
  def concatenator: (String, String) => String = new Function2[String, String, String] {
    override def apply(a: String, b: String): String = a + b
  }

  println(concatenator("abc", "def"))

  // 3.
  val superAdder: Function1[Int, Function1[Int, Int]] = new Function1[Int, Function1[Int, Int]] {
    override def apply(a: Int): Function1[Int, Int] = new Function1[Int, Int] {
      override def apply(b: Int): Int = a + b
    }
  }

  val adder3 = superAdder(3)
  println(adder3(4))
  println(superAdder(3)(4)) // curried function -- can be called ny multiple function lists
}

class Action1 {
  def execute (element: Int): String = ???
}

trait Action2[A, B] {
  def execute (element: A): B = ???
}


trait MyFunction[A, B] {
  def apply (element: A): B
}


/*
  1. Function takes two strings and concatenate them
  2. transform the MyPredicate and MyTransformer into function types
  3. define a function which takes an int and returns another function which takes an int and returns an int
    - what's the type of this function
    - how to do it
*/

// higher order function ----- either receive functions as parameters or return other functions as a result


/*
  Functions:
    - pass functions as parameters
    - use functions as values

  Problem: Scala works on top of the JVM
    - designed for JAVA
    - first-class elements L objects(instances of classes)

  Solution: All Scala functions are objects
    - functions traits, up to 22 params

      trait Function[-A, +B] {
        def apply (element: A): B
      }

    - syntactic sugar function types

      Function2[Int, String, Int]

      (Int, String) => Int
*/
