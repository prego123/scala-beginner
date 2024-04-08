package lectures.basics

import scala.annotation.tailrec

object Recursion extends App {

  def fact(n: Int): Int =
    if(n<=1) 1
    else {
      println("Computing factorial "+ n + " " + (n-1))
      var result = n * fact(n - 1)
      println("Computed factorial "+ n)

      result
    }

  println(fact(10))

  def anotherFact(n: Int): BigInt = {
    @tailrec
    def factHelper(x: Int, accumulator: BigInt): BigInt =
      if(x<=1) accumulator
      else factHelper(x-1, x*accumulator)  // tail recursion = use recursion call as the LAST expression

    factHelper(n, 1)
  }

  println(anotherFact(5000))

  //when use loops - tail recursion

  //1. Concatenates a string n times
  //2. IsPrime func tail recur
  //3. Fibonacci tail recur

  //1.
  def concatStrings(s: String, n: Int): String = {
    @tailrec
    def concat(result: String, n: Int): String =
      if(n<=1) result
      else concat(s+result, n-1)

    concat(s, n)
  }
  println(concatStrings("Hello", 4))


  //2.
  def isPrime(n: Int): Boolean = {
    @tailrec
    def isPrimeUtil(i: Int, check: Boolean): Boolean = {
      if(!check) false
      else if(i<=1) true
      else isPrimeUtil(i - 1, (n%i != 0) && check)
    }

    isPrimeUtil(n/2, true)
  }

  println(isPrime(7))
  println(isPrime(9))

  //3.
  def fibonacci(n: Int): Int = {
    @tailrec
    def fiboUtil(i: Int, prev: Int, next: Int): Int = {
      if(i>=n) next
      else fiboUtil(i+1, next, next+prev)
    }

    if(n<=2) 1
    else fiboUtil(2, 1, 1)
  }

  println(fibonacci(5))
}
