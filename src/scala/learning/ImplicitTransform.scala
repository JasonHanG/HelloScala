package scala.learning

/**
  * Created by jason on 16-9-8.
  */
class ImplicitTransform {

}
class A {

}
class ExtraA(a :A) {
  def extraMethod (): Unit ={
    println("I can do more")
  }
}
object  ImplicitTransform extends App {
  //隐式方法
  implicit  def A2ExtraA(a : A) = new ExtraA(a);

  var a = new A
  a.extraMethod()

  //隐式参数
  def implicitParam(implicit name :String): Unit = {
    println(name)
  }

  implicit val name = "jason"

  implicitParam
  implicitParam("jack")

  //隐式类
  implicit class Calculator(int : Int) {
    def add(a :Int) :Int = a+1
  }

  println(1.add(2))
}