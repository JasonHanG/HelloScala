package scala.learning

/**
  * Created by jason on 16-9-8.
  */
class AbstractClass {

}

abstract class Human {
  def speak

  var name: String
}

class Asian extends Human {
  //覆盖abstract方法和字段可以不用override关键字
  def speak {
    println("NiHao")
  }

  var name: String = "name"
}


trait Logger {
  def log(msg: String)
}

trait ConsoleLogger extends Logger {
  def log(msg: String): Unit = {
    println("ConsoleLog:" + msg)
  }
}

trait PaperLogger extends ConsoleLogger {
  override def log(msg: String): Unit = {
    println("PaperLog:" + msg)
  }
}

trait Saver {
  def save
}

//第一个混入的特质也要用extents,其余的用with,多个特质从左到右构造
class MyRecorder extends ConsoleLogger with Saver{
  def record(info: String): Unit = {
    println("Info:" + info)
    log(info.length.toString)
  }

  override def save: Unit = {
    println("Saving...")
  }
}

object AbstractClass extends App {
  //  new Asian().speak

  //对象级别的特质混入,提供类特质的子特质实现
  val recorder = new MyRecorder() with PaperLogger
  recorder.record("Paper or Console ?")
}

//trait 可以对应java8中的接口:可以在接口中给默认的实现
