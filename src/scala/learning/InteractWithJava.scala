package scala.learning

import java.util.{Date, Locale}
import java.text.DateFormat._
import java.util.Locale

/*
  Scala的import语句看上去与Java的非常相似，但是它更加强大。
  你可以使用大括号来导入同一个包里的多个类，就像上面代码中第一行所做的那样。
  另一个不同点是当导入一个包中所有的类或者符号时，你应该使用下划线（_）而不是星号（*）。
  这是由于星号在Scala中是一个有效的标识符（例如作为方法名称）。这个例子我们稍后会遇到。
*/

/**
  * Created by jason on 16-9-12.
  */
class InteractWithJava {

}

object InteractWithJava {
  def main(args: Array[String]) {
    val now = new java.util.Date()
    val df = getDateInstance(LONG, Locale.FRANCE)
    //两种调用方法
    //这看起来是一个语法细节，但是它导致一个重要的后果，我们将在下一节进行说明。另外，我们还应当注意到Scala中可以直接继承或者实现Java中的接口和类。
    println(df format now)
    println(df.format(now))
  }
}