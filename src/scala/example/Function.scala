package scala.example

/**
  * Created by jason on 16-9-12.
  */
class Function {

}
object Function {

  //求 a 和 b 之间所有整数的和
  def sumInts(a: Int, b: Int): Int =
    if (a > b) 0 else a + sumInts(a + 1, b)

  //求 a 和 b 之间所有整数的平方的和
  def square(x: Int): Int = x * x
  def sumSquares(a: Int, b: Int): Int =
    if (a > b) 0 else square(a) + sumSquares(a + 1, b)

  //对介于 a 和 b 之间所有整数 n,求2的n次幂的和
  def powerOfTwo(x: Int): Int = if (x == 0) 1 else 2 * powerOfTwo(x - 1)
  def sumPowersOfTwo( a: Int, b: Int): Int =
  if (a > b) 0 else powerOfTwo(a) + sumPowersOfTwo(a + 1, b)


  //以上三个函数,都是连续加和的不同实例。因此我们可以抽出一个公共函数因子
  //“ Int => Int ” 定义了一类函数,这类函数接受一个 Int 型参数,并返回 Int 类型的值。
  // 所以,sum 的参数是另一个函数,也就是说, sum 是 高阶函数 。
  def sum(f: Int => Int, a: Int, b: Int): Int =
    if (a > b) 0 else f(a) + sum(f, a + 1, b)

  //重写前述的求和函数如下:
  def id(x: Int): Int = x

  def newSumInts(a: Int, b: Int): Int = sum((x: Int) => x, a, b)
  def newSumSquares(a: Int, b: Int): Int = sum(x => x * x, a, b)
  def newSumPowersOfTwo(a: Int, b: Int): Int = sum(powerOfTwo, a, b)


  //Anonymous Function
  //匿名函数被“ => ”分割成两部分,前面是函数的参数列表,后面是函数体。
  (x: Int) => x * x
  (x: Int, y: Int) => x * y



}

//Currying这个词有人翻译成柯里化,这是一个数学概念
//大概是把一个有多个参数的函数,转化成一系列单参数函数的嵌套调用的过程。
//函数 sum 返回另一个函数(也就是 sumF )作为值,实际的工作全都由后者完成。
//a 和 b 两个参数都转到了新的函数 sumF 上,该函数使用外层的 f 参数,执行从 a 到 b 求和递归。

object  Currying {
  def sum(f: Int => Int): (Int, Int) => Int = {
    def sumF( a: Int, b: Int ): Int =
      if ( a > b ) 0 else f ( a ) + sumF ( a + 1, b )
    sumF
  }

  //这种“返回函数的函数”的用途是如此之广,以至于 Scala 语言中,为这种情况设计了专门语法。
  // 例如,下面就是用这种专门的语法,对 sum 函数所做的进一步简化:
  def simplifySum(f: Int => Int)(a: Int, b: Int): Int =
    if (a > b) 0 else f(a) + simplifySum(f)(a + 1, b)

  def powerOfTwo(x: Int): Int = if (x == 0) 1 else 2 * powerOfTwo(x - 1)


 /*
  sumInts 、 sumSquares 和 sumPowersOfTwo 这三个函数可以像任何其它函数一样被调用
  (虽然sumInts 等函数是无参的,但作为值返回的函数有参数,而正是这个函数负责接手后面的实参,这正是函数式编程的魅力和神奇之处)
 */
  val sumInts = sum(x => x)
  def sumSquares = sum(x => x * x)
  def sumPowersOfTwo = sum(powerOfTwo)




  def main( args: Array[String] ) {
    //返回函数的函数在被调用的时候,是什么样的工作原理呢?
    //sum(x => x * x) 先行计算,得到一个函数,于是,表达式实际上变成 sumF(1, 10)
    //sum(x => x * x)(1, 10) 等价于: (sum(x => x * x))(1, 10)
    println(sumInts(1,10))
  }
}