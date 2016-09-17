package scala.learning

/**
  * Created by jason on 16-9-12.
  */
class ScalaBasic {

}

/**
  * Created by jason on 16-9-8.
  */
object BasicLeanring {
  //默认参数
  def hello(name: String = "Jack"): String = {
    "hello :" + name
  }

  //将匿名函数赋值给add
  def add = (x: Int, y: Int) => x + y
  //柯里化
  def addCurrying(x: Int)(y: Int) = x + y
  //可变参数
  def printEveryChar(s : String*) = {
    s.foreach(c => println(c))
  }
  //Scala是完全面向对象的语言,也就是说,Scala 中的任何值(Value)都是对象,包 括整型、布尔型等基本的原子类型。
  // 实际上,Scala 中的原子类型是 Predef 模块下相应类的 别名
  //main函数
  def main(args: Array[String]) {

    println(hello("jason"))
    //使用默认参数
    println(hello())

    //函数引用重新赋给addNew
    val addNew = add
    println(addNew(1, 2))
    println(addCurrying(1)(2))
    printEveryChar("scala","java","python","php")
    //def 只做定义,但并不求右边表达式的值,比如: def x=e ,其中 e 的值在定义的时候不计算,
    //而是 e 被引用的时候才计算。不过,Scala 还提供了一种定义值的手段: val x=e ,这种方式在
    //定义的时候就求出 e 的值赋给 x ,接下来用到 x 的时候,就直接用预先计算好的值替换,而无
    //需再对 e 求值。
    def a = 2 + 1
    val start = 3
    //条件赋值
    val result = if(start > 2) true else false
    println(result)


    var (x,y) = (3,3)
    //序列生成器
    for(i <- 1 to 10){ // a to b  = a.to(b)
      println(i)
    }
    //until　不输出10
    for(i <- 1 until 10){ // a to b  = a.to(b)
      println(i)
    }
    for(i <- 1 to 10 if i%2 == 0){
      println(i)
    }



  }
}
