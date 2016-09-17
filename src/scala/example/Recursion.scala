package scala.example

/**
  * Created by jason on 16-9-12.
  */
class Recursion {

}
object Recursion {
  /*
  重要的差异: gcd 函数的演算过程中,一遍遍的 出现相似的模式,所以它用到的空间是一个有限的常量;
  而 factorial 函数的演算式却越来越长(同时占用更多空间),其中的一长串操作数,直到最后才乘起来得出结果。
  虽然 Scala 的内部实现未必与我们的手工演算过程完全一致,但对空间的需求量级,却与我们的对比结论一致。
  观察 gcd 函数的实现,我们会注意到,对 gcd 的递归调用是该函数的最后一个动作,这种情况叫做“尾递归(tail-recursive)”。
  尾递归的内部实现,可以变成直接跳转到当前函数的入口处,并且修改函数的实参,这样就不需要分配新的栈空间。
  因此,尾递归函数实际上是迭代过程,执行所需的空间是恒定的。
  相反, factorial 函数中的递归调用紧跟着一个乘法运算,所以它不是尾递归,因此需要为每次递归分配新的栈空间,并在递归返回后释放。
  所以, factorial 函数执行所需的空间和输入参数成正比。
*/
  def gcd(a: Int, b: Int): Int = if (b == 0) a else gcd(b, a % b)
  def factorial(n: Int): Int = if (n == 0) 1 else n * factorial(n - 1)
  def fibonacci(n: Int): Int = {
    if (n <= 2) 1
    else fibonacci(n - 1) + fibonacci(n - 2)
  }

  /**
    * 通常来说,如果一个函数的最后一个动作是调用另一个函数(包括调用自身),
    * 那么两个函数就可以共享同一个stack frame,而这种调用就被称为“尾调用”。
    */
  def factorialTailrec(n: BigInt, acc: BigInt): BigInt = {
    if(n <= 1) acc
    else factorialTailrec(n-1, acc * n)
  }

  //  调用序列：
  //  fibonacciTailrec(5,0,1)
  //  fibonacciTailrec(4,1,1)
  //  fibonacciTailrec(3,1,2)
  //  fibonacciTailrec(2,2,3)
  //  fibonacciTailrec(1,3,5)
  //  5
  def fibonacciTailrec(n: Int, acc1: Int, acc2: Int): Int = {
    if (n < 2) acc2
    else fibonacciTailrec(n - 1, acc2, acc1 + acc2)
  }

  def main( args: Array[String] ) {
//    println(factorialTailrec(6,1))
    println(gcd(42,21))
  }
}