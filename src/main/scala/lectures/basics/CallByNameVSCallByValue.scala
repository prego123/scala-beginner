package lectures.basics

object CallByNameVSCallByValue extends App {

  def callByValue(x: Long): Unit = {
    println("By value: " + x)
    println("By value: " + x)
  }

  def callByName(x: => Long): Unit = {
    println("By value: " + x)
    println("By value: " + x)
  }
  // => by name

  callByValue(System.nanoTime())
  callByName(System.nanoTime())

  // call by value
  //  By value: 375083338761361
  //  By value: 375083338761361
  //  exact value of expression is computed before the function is evaluated

  // call by name
  //  =>
  //  By value: 375083390568265
  //  By value: 375083390611914
  //  values are executed everytime
  // def callByName(x: => Long): Unit = {
  //    println("By value: " + System.nanoTime())
  //    println("By value: " + System.nanoTime())
  //  }

  def infinite(): Int = 1 + infinite()
  def printFirst(x: Int, y: => Int) = println(x)

 // printFirst(infinite(), 34) - first calculated then passed hence resulted in infinity
  printFirst(34, infinite()) // calculated after passing to the parameter
}
