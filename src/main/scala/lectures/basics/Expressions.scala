package lectures.basics

object Expressions extends App {
  val x= 1+2 //expression
  println(x);

  //Instructions && Expressions
  var i=0
  while(i<10) {
    print(i)
    i+=1
  }

  // side effects: println(), whiles, reassigning --- return unit
  val cond: Int = if (5>2) 42 else 0

  // code block: expression
  val aCodeBlock = {
    val y=2;
    val z=3;

    if(z>y) "hello" else "goodbye"
  }

  //1.  difference between "hello world" vs println("hello word")
  // println() -- print in the console and "hello world" --- string literal
  // return type -- unit (side effect)                   string

  //2.
  val someVal = {
    2 < 3
  }
  //true

  val b = {
    if(someVal) 239 else 908
    42
  }
  //42
}

//Expressions vs Instructions

//instructions are executed, expressions are evaluated (Scala)
//in Scala, we will think in terms of expressions
