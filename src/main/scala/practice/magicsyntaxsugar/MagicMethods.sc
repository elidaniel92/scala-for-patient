
object Test {
  private var array =  new Array[String](0)

  def apply(i: Int): String = {
    try {
      array(i)
    } catch {
      case _ => throw new RuntimeException("Index out of the bounds")
    }
  }

  /**
   * Note: obj(i) = x can be update and/or add (persist)
   * Check scala.collection.mutable.Map
   */
  def update(i: Int, s: String): Unit = {
    if(i < array.length) array(i) = s
    else this.array = array.concat(Array(s))
  }

  def applyOrElse = {
//    array.applyOrElse(i, (x: Int) => "Default")
    ???
  }

  /*def update(i: Int, s: String): Unit = {
    println(s"Test($i) = $s")
  }*/
}

// apply method
Test(0)

// update method
Test(0) = "A"
Test(123) = "B"

// update and apply method
Test(0) = Test(0) + "A"