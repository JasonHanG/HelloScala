package scala.example

/**
  * Created by jason on 16-9-12.
  */
class QuickSort {

}

/*

命令式和函数式两种实现具有相同的时间复杂度-平均为 O(N log (N )),最差为 O(N 2)。不过,
命令式例子是在原数组上直接操作来实现排序,而函数式例子则不改变原数组,返回的已排序
数组都是新创建的,所以,函数式编程实现的快速排序,需要更多的transient(临时)内存。

*/

object QuickSort {
  /*
  该函数的返回值就是最后一行表达式的值, return 不是必须的。
  对于显式定义了返回值的函数,在函数体之前(类型和函数体之间)必须要加上 “=”。
  */
  def sort(xs: Array[Int]): Array[Int] = {
    if ( xs.length <= 1)
      xs
    else {
      val pivot = xs(xs.length / 2)
      Array.concat(
//      def filter(p: T => Boolean): Array[T]
//      其中, T => Boolean 定义了这样一类函数:以类型 t 为参数并返回 Boolean 值。像 filter 这样
//      把另一个函数当作参数或者返回值的函数,被称为 高阶函数(higher-order functions)。
//      pivot > 为例,它代表这样一个函数:接受参数 x 并返回表达式 “ pivot > x” 的值,它实
//      际上是“ x => pivot > x ”的简化版,而这种简化语法,通常被称为 偏应用函数 (partially applied
//       function)。函数是匿名的,而且参数 x 的类型也省略了,因为编译器可以从上下文自动推断
//      类型。总而言之, xs.filter(pivot >) 返回由 xs 中所有小于 pivot 的元素组成的新列表。
        sort(xs.filter(pivot > )),
        xs.filter(pivot == _),
        sort(xs.filter(x => pivot < x))
      )
    }

  }

  def main( args: Array[String] ): Unit ={

    val arr = Array(2, 5, 7, 10)
    val sorted = sort(arr)
    sorted.foreach(
      x => println(x)
    )
  }
}
