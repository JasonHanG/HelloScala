package scala.example

import scala.math._

/**
  * Created by jason on 16-9-12.
  *
  * 求平方根最常见的办法是使用牛顿的逐步逼近法:首先,选择一个初始值 y (比如 y=1 );
  * 然后,通过反复的求 y 和 x/y 的平均值来优化初始值。接下来我们按照该算法,推演 2 的平方根;
  * 下面的三列分别代表本轮的猜测值 y , x/y 的商和前两者平均值(也就是进一步逼近的值)
  */

class Sqrt {
//  def sqrtIterator(guess: Double, x: Double): Double =
//    if (isGoodEnough(guess, x)) guess
//    else sqrtIterator(improve(guess, x), x)
//
//  def improve(guess: Double, x: Double) =
//    (guess + x / guess) / 2
//  def isGoodEnough(guess: Double, x: Double) =
//    abs(guess*guess -x) < 0.001
//  def sqrt(x: Double) = sqrtIterator(x/2, x)
}
/*
在函数式编程风格的代码中,鼓励大量的使用短小的辅助函数来共同完成一件事。
上一个例子中的 sqrt 函数,用到的 sqrtIterator 、 improve 和 isGoodEnough ,都可以看成是辅助函数,
而这些函数都只跟 sqrt 的内部实现有关,所以我们不希望调用 sqrt 函数的代码也能直接访问这些辅助方法。
为了确保这一点,我们可以把辅助函数定义在主调函数内部
(这样做,还可以避免命名空间污染:name-space pollution)
*/
object Sqrt {


  // 由大括号 { ... } 包起来的部分称为 block。Scala 中,blocks 本身也是表达式,表达式的值取决于 block 内最后的 result expression。
  // 在result expression 之前,还可以有其它的附加定义(auxiliary definitions),但这些定义只在block 内可见
  def sqrt(x: Double) = {
    def sqrtIterator(guess: Double, x: Double): Double =
      if (abs(guess*guess - x) < 0.001 ) guess
      else sqrtIterator((guess + x / guess) / 2, x)
    sqrtIterator(x/2, x)
  }

  def main( args: Array[String] ) {
    println(sqrt(10000))
  }
}