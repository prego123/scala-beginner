package lectures.basics

object Functions extends App {

  def aFunc(a: String, b: Int) =
    a + " " + b

  def bFunc(a: String, b: Int): String = {
    a + " " + b
  }

  println(aFunc("aa", 3))

  println(bFunc("bb", 3))


  def para(): Int = 42
  println(para())
  println(para)


  def aRepeFunc(a: String, n: Int): String = {
    if(n==1) a
    else a + aRepeFunc(a, n-1)
  }

  println(aRepeFunc("hello", 3))


  def funcWithSideEffects(a: String): Unit = println(a)

  def bigFunc(n: Int): Int = {
    def smallFunc(a: Int, b: Int): Int = a + b

    smallFunc(n, n-1)
  }

  // 1. a greeting func (name, age): => "Hello name is Sam and age is 3 years
  // 2. Factorial func 1*2*3*....n
  // 3. Fibonacci func
  // 4. test is a funct is prime


  // 1.
  def greeting(name: String, age: Int): String = "Hello, my name is " + name + ". My age is " + age
  println(greeting("Pragati", 25))


  // 2.
  def factorial(n: Int): Int = {
    var i = 1
    var fact = 1
    while(i<n) {
      fact *= i
      i+=1
    }

    fact
  }

  println(factorial(5))

  // 3.
  def fibonacci(n: Int): Int = {
    if(n == 1) 1
    else if(n == 2) 1
    else fibonacci(n-1) + fibonacci(n-2)
  }

  println(fibonacci(5))

  // 4.
  def isPrime(n: Int): Boolean = {
    def isPrimeUtil(t: Int): Boolean = {
      if(t<=1) true
      else n%t != 0 && isPrimeUtil(t-1)
    }

    isPrimeUtil(n/2)
  }

  println(isPrime(9))
}

