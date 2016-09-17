package scala.example

/**
  * Created by jason on 16-9-14.
  */

/*
    Call-by-value 的优点是:可以免去对实参重复求值;Call-by-name 的优点是:可以避免对函
    数中压根没有用到的实参求值。Call-by-value 的效率通常优于 call-by-name,但某些通过
    call-by-name 能求出值的表达式,使用 call-by-value 方式会导致死循环
    传名调用 (call-by-name),传值调用(call-by-value)
    Scala 默认使用 call-by-value 方式,但如果函数的参数类型前有“ => ”前缀,则自动切换到call-by-name 方式。
*/

class CallByNameOrValue {


}

//在外层 block 中定义的名字,内层 block 也是可见的,除非在内层做了重新的定义。
object CallByNameOrValue extends App {
/*  def loop: Int = loop

  def constOne(x: Int, y: => Int) = 1
  //使用 call-by-name 方式对 constOne(1, loop) 求值得 1 ,而用 call-by-value 则推导出来的结果与原表达式相同,从而产生死循环:
  constOne(1, loop)
  constOne(loop,1)*/
}