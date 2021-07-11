trait Foo { }
class Bar { }
abstract class FooBar { }

{
  class Impl1 extends Foo { def a = "A" }
  class Impl2 extends Foo { def b = "B" }
  val x1: Foo = new Impl1
  val x2: Foo = new Impl2
  def test[A](x1: A, x2: A) = {
    assert(x1.isInstanceOf[Impl1] && !x1.isInstanceOf[Impl2] && x1.isInstanceOf[A])
    def handler(x: A) = {
      if(x.isInstanceOf[Impl1]) x.asInstanceOf[Impl1].a
      else  x.asInstanceOf[Impl2].b
    }
    assert(handler(x1) == "A" && handler(x2) == "B")
    println("Test: " + x1.getClass)
  }
  test[Foo](x1,x2)
}

{
  class Impl1 extends Bar { def a = "A" }
  class Impl2 extends Bar { def b = "B" }
  val x1: Bar = new Impl1
  val x2: Bar = new Impl2
  def test[A](x1: A, x2: A) = {
    assert(x1.isInstanceOf[Impl1] && !x1.isInstanceOf[Impl2] && x1.isInstanceOf[A])
    def handler(x: A) = {
      if(x.isInstanceOf[Impl1]) x.asInstanceOf[Impl1].a
      else  x.asInstanceOf[Impl2].b
    }
    assert(handler(x1) == "A" && handler(x2) == "B")
    println("Test: " + x1.getClass)
  }
  test[Bar](x1,x2)
}

{
  class Impl1 extends FooBar { def a = "A" }
  class Impl2 extends FooBar { def b = "B" }
  val x1: FooBar = new Impl1
  val x2: FooBar = new Impl2
  def test[A](x1: A, x2: A) = {
    assert(x1.isInstanceOf[Impl1] && !x1.isInstanceOf[Impl2] && x1.isInstanceOf[A])
    def handler(x: A) = {
      if(x.isInstanceOf[Impl1]) x.asInstanceOf[Impl1].a
      else  x.asInstanceOf[Impl2].b
    }
    assert(handler(x1) == "A" && handler(x2) == "B")
    println("Test: " + x1.getClass)
  }
  test[FooBar](x1,x2)
}

class Outer(val id: Int) {
  class Inner { def id = Outer.this.id }
}
val outer1 = new Outer(1)
val outer2 = new Outer(2)
val inner1: Outer#Inner = new outer1.Inner
val inner2: Outer#Inner = new outer2.Inner

// Does wor
assert(!inner1.isInstanceOf[outer2.Inner] && !inner2.isInstanceOf[outer1.Inner])


class Outer2(val id: Int) {
  class Inner2 {
    def id = Outer2.this.id
    def x:Outer2.this.Inner2= ???
  }
}