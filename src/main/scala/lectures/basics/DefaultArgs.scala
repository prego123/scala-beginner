package lectures.basics

object DefaultArgs extends App {

  def fact(n: Int, acc: Int = 1): Int =
    if(n<=1) acc
    else fact(n-1, n*acc)

  var factorial = fact(10)

   def savePictures(format: String = "jpg", width: Int = 1920, height: Int = 1080): Unit = println("Saving pictures")

  savePictures(width=800)
  savePictures(width = 34, format = "href")
}
