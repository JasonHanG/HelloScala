package scala.learning

/**
  * Created by jason on 16-9-12.
  */
class Generics {

}
//Reference类具有一个叫做T的类型参数来表示他说引用的对象的类型。这个类型在Reference中作为了变量和函数的参数或者返回类型。
class Reference[T] {
  private var contents: T = _
  def set(value: T) { contents = value }
  def get: T = contents
}

object IntegerReference {
  def main(args: Array[String]) {
    val cell = new Reference[Int]
    cell.set(13)
    println("Reference contains the half of " + (cell.get * 2))
    //不需要吧get的返回值强制转换成Int，而且由于它被声明成Int，你不可能在这个引用中放置其他类型的对象。
  }
}