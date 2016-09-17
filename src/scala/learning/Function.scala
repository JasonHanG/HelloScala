package scala.learning

/**
  * Created by jason on 16-9-12.
  */
class Function {

}

/**
  * 时器函数被叫做oncePerSceond，它接受一个回调函数作为参数。
  * 这种函数的类型被写作 () => Unit ，他们不接受任何参数也没有任何返回（Unit关键字类似于C/C++中的void）。
  * 程序的主函数调用计时器并传递一个打印某个句子的函数作为回调。
  * 换句话说，这个程序永无止境的每秒打印一个“time flies like an arrow”。
  */
object Timer {
  def oncePerSecond(callback : () => Unit) {
    while (true) { callback(); Thread sleep 1000 }
  }

  def timeFlies(): Unit = {
    println("Time flies like an arrow!")
  }

  def main(args: Array[String]) {
    //oncePerSecond(timeFlies)

    // 给这种只用一次的函数命名似乎没有什么太大的必要，事实上我们可以在用到这个函数的时候再定义它。
    // 这些可以通过匿名函数在Scala中实现，匿名函数顾名 思义就是没有名字的函数。
    // 我们在新版的程序中将会使用一个匿名函数来代替原来的timeFlies函数
    // 本例中的匿名函数使用了一个箭头（=>）吧他的参数列表和代码分开。
    // 在这里参数列表是空的，所以我们在右箭头的左边写上了一对空括号。函数体内容与上面的timeFlies是相同的。
    oncePerSecond(() =>
    println("This is an anonymous function!"))

  }
}