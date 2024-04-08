package lectures.basics

object ValuesVariableTypes extends App {
  val x = 42;
  println(x);

  //val are immutable
  //compiler can infer the type

  val str: String="hello";
  val bool: Boolean=true;
  val ch: Char='a';
  val sh: Short=122;
  val lg: Long=233344;
  val ft: Float=2.0f;
  val db: Double=3.14;


  // variables are mutable

  var avar: Int=4;
  avar=5;

  //prefer vals over vars
  //all vals and vars have types
  //compiler automatically infers types when omitted

}
