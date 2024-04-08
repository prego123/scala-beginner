package lectures.functional

object AnonymousFunctions extends App {

  val doubler = new Function1[Int, Int] {
    override def apply(x: Int): Int = x * 2
  }

  // anonymous function (LAMBDA)
  val doublersimplified: Int => Int = x => x * 2

  // multiple parameters
  val adder: (Int, Int) => Int = (a: Int, b: Int) => a + b

  // no param
  val justDoSomething: () => Int = () => 3
  println(justDoSomething) // function itself
  println(justDoSomething()) // lambda needs to be called with ()

  // curly braces with lambda
  val stringToInt = { (str: String) =>
    str.toInt
  }

  // MOAR syntactic sugar -- higher order calls
  // val niceIncrementer: Int => Int = (x: Int) => x + 2
  val niceIncrementer: Int => Int = _ + 1 // equivalent to x => x + 1
  val niceAdder: (Int, Int) => Int = _ + _ // equivalent to (a, b) => a + b

  /*
     1. MyList: Replace all functionX calls with lambda
     2. Define or Rewrite the "special" adder as an anonymous one
  */

  val superAdder = (a: Int) => (b: Int) =>  a + b
  println(superAdder(3)(4))
}

/*
  Takeaways

  Instead of passing anonymous FunctionX instances every time
  1) cumbersome
  2) still object-oriented

  (x, y) => x+y  --- lambda

  (name: String, age: Int) => name + " is " + age + " years old"

  parameter --- name: String
  parentheses mandatory for more than one parameter
  type: optional
  return type always inferred
  after arrow --- implementation (expression)
*/
