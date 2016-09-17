package scala.learning

/**
  * Created by jason on 16-9-12.
  */
class ObjectOhObject {

}
//Scala作为一个纯面向对象的语言
// 于是在Scala中万物皆对象，包括数字和函数。
// 在这方面，Scala于Java存在很大不同：Java区分原生类型（比如boolean和int）和引用类型，并且不能把函数当初变量操纵。
object ObjectOhObject {
  def main(args: Array[String]) {

    for(i <- 1.to(10)) {
      println(i)
    }
    val x = 3
    //由于数字本身就是对象，所以他们也有方法。事实上我们平时使用的算数表达式（如下例）
    print(1 + 2 * 3 / x)
    //是由方法调用组成的。它等效于下面的表达式，我们在上一节见过这个描述。
    print((1).+(((2).*(3))./(x)))
  }
}
