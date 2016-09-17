package scala.learning

/**
  * Created by jason on 16-9-8.
  */
class Worker {
  def apply(): Unit = {
    println("Work work")
  }
  def something: Unit = {
    println("Do something")
  }
}

//可以将object中的方法看做static方法
object WorkerMaker {
  def apply()= new Worker
}

object MainClass extends App {
  val instant = WorkerMaker()//(类名) 调用同名object上的apply方法
  instant.something
  instant()//(对象) 调用类上的apply方法
}