package scala.learning

/**
  * Created by jason on 16-9-8.
  */
class Match {

}


//属性默认,保证消息不变性
case class Book(name : String , author : String)


object Match extends App {
/*  val number = 3
  //无需显示break,首次匹配就返回
  val result = number match {
    case i if i == 1 => "one"
    case 2 => "two"
    case _ => "other number"
  }
  println(result)

  def matchClass(obj: Any) = obj match {
    case x: Int => "Int"
    case x: String => "String"
    case _ => "Unknown Type"
  }

  print(matchClass(1.0))*/


  //不用new关键字就能产生对象,使用apply方法实现
  val macTalk = Book("mac","jason")

  macTalk match {
    case Book(name,author) => println("This is Book")
    case _ => println("Something I don't known")
  }
}