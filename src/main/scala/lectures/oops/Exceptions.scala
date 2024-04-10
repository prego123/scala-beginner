package lectures.oops

object Exceptions extends App {

  val x: String = null
  // println(x.length) - crashed with a null pointer exception

  // 1. throwing and catching exceptions

  // throw new NullPointerException --- expressions
  // val aWeirdValue: String = throw new NullPointerException

  // throwable classes extend the Throwable class
  // Exception and Error are the major Throwable subtype

  // 2. Catch exceptions
  def getInt(withExceptions: Boolean): Int =
    if (withExceptions) throw new RuntimeException("No int for you")
    else 42

  try {
    // code that might fail
    getInt(true)
  } catch {
    case e: RuntimeException => println("Caught a runtime exception")
    // case e: NullPointerException => println("Caught a runtime exception") --- crashes wrong exception
    // jvm related
  } finally {
    // code will executed no matter what
    println("Finally!")
  }

  val potentialFail = try {
    // code that might fail
    getInt(true)
  } catch {
    case e: RuntimeException => 43
    // case e: NullPointerException => println("Caught a runtime exception") --- crashes wrong exception
    // jvm related
  } finally {
    // code will executed no matter what
    // optional
    // does not influence the return type of this expression
    // use finally only for the side effects
    println("Finally!")
  }
  println(potentialFail)

  // 3. custom exceptions
  class MyException extends Exception // have all the fields/methods like normal class
  val exception = new MyException

  // throw exception

  /*
    1. Crash your program with an OutOfMemoryError
    2. Crash with SOError
    3. PocketCalculator
      - add(x, y)
      - subtract(x, y)
      - multiply(x, y)
      - divide(x, y)

      Throw
        - OverflowException if add(x, y) exceeds Int.MAX_VALUE
        - UnderFlowException if subtract(x, y) exceeds Int.MIN_VALUE
        - MathCalculationException for division by 0
  */

  // 1.  val array = Array.ofDim(Int.MaxValue) -- OOM

  // 2.
  // def infinite: Int = 1 + infinite
  // val noLimit = infinite

  //3.
  private class OverflowException extends RuntimeException
  private class UnderFlowException extends RuntimeException
  private class MathCalculationException extends RuntimeException("Division by 0")

  object PocketCalculator {
    def add(x: Int, y: Int): Int = {
      if (x > 0 && y > 0 && x+y < 0) throw new OverflowException
      else if (x < 0 && y < 0 && x+y > 0) throw new UnderFlowException
      else x + y
    }

    def subtract(x: Int, y: Int): Int = {
      if (x > 0 && y < 0 && x - y  < 0) throw new OverflowException
      else if (x < 0 && y > 0 && x - y > 0) throw new UnderFlowException
      else x - y
    }

    def multiply(x: Int, y: Int): Int = {
      if (x > 0 && y > 0 && x * y < 0) throw new OverflowException
      else if (x < 0 && y < 0 && x * y < 0) throw new OverflowException
      else if (x > 0 && y < 0 && x * y > 0) throw new UnderFlowException
      else if (x < 0 && y > 0 && x * y > 0) throw new UnderFlowException
      else x * y
    }

    def divide(x: Int, y: Int): Int =
      if (y == 0) throw new MathCalculationException
      else x / y
  }

  println(PocketCalculator.add(Int.MaxValue, 10))
  println(PocketCalculator.divide(2, 0))
}

/*
  Takeaways

  1. Exceptions crash your program

  2. Throw exceptions
    throwing returns Nothing
    val someValue = throw new RunTimeException

  3. Catch exceptions
    try {
      // compute a value
    } catch {
      case e: RunTimeException => /* another value */
    } finally {
      // block for side effects
    }

  4. Custom exceptions
    class MyKnife extends Exception
*/
