package scala.learning

/**
  * Created by jason on 16-9-12.
  */
class Trait {

}
/*
在比较对象时，一下六种关系通常使用率最高：小于、小于等于、等于、不等于、大于等于、大于。但是把他们都定义一次无疑是很没用而且繁琐的。
尤其是 六种关系中的四种其实是可以通过其他两种关系导出的。
例如给定等于和小于的定义后就可以推导出其他的定义。于是在Scala中，这些推导可以通过下面这个 trait实现：
*/

trait Ord {
  def < (that: Any): Boolean
  def <=(that: Any): Boolean =  (this < that) || (this == that)
  def > (that: Any): Boolean = !(this <= that)
  def >=(that: Any): Boolean = !(this < that)
}

class Date(y: Int, m: Int, d: Int) extends Ord {
  def year = y
  def month = m
  def day = d
  override def toString( ): String = year + "-" + month + "-" + day


/*
  这个函数使用了预定义函数 isInstanceOf 和asInstanceOf
  第一个isInstanceOf 类似Java中的instanceOf ：当且仅当对象是给定类型的实例时才返回true。
  第二个 asInstanceOf 对应Java中的类型转换操作：当对象是给定类型的子类时转换，否则抛出ClassCastException。
*/
  override def equals(that: Any): Boolean =
  that.isInstanceOf[Date] && {
    val o = that.asInstanceOf[Date]
    o.day == day && o.month == month && o.year == year
  }

  //最后我们还需要定义测试小于关系的函数，如下面所示。这个函数使用了预定义的函数error ，它可以使用给定字符串抛出一个异常。
  def <(that: Any): Boolean = {
    if (!that.isInstanceOf[Date])
    sys.error("cannot compare " + that + " and a Date")
    val o = that.asInstanceOf[Date]
    (year < o.year) ||
    (year == o.year && (month < o.month ||
      (month == o.month && day < o.day)))
  }
}