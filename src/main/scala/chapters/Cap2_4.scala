package chapters

object Cap2_4 extends App {

    /**
   * Procedure is a "Function" that return
   * no value (Unit). Only call a procedure for its
   * side effect.
   * Below I omit the "=" symbol.
   */
  def proc1(str: String) {
    println(str)
  }
  // Without parameter
  def proc2 {
    println("Str")
  }
  // Highly Concise
  def proc3 = println("Str")

  /**
   * Below a not concise syntax.
   * Explicit return type.
   */
  def proc4: Unit = {
    println("Str")
  }

  /**
   * A Lazy Values is initialized when
   * it is accessed for the first time.
   * If the program never accesses value words,
   * the file is never opened.
   */
  lazy val words =
    scala.io.Source.fromFile("/usr/share/dict/words").mkString
  /**
   * NOTE: Laziness is not cost-free.
   * Every time a lazy value is accessed, a
   * method is called that checks, in a threadsafe manner,
   * whether the value has already been initialized.
   */

  val words1 = scala.io.Source.fromFile("/usr/share/dict/words").mkString
  // Evaluated as soon as words is defined
  lazy val words2 = scala.io.Source.fromFile("/usr/share/dict/words").mkString
  // Evaluated the first time words is used
  def words3 = scala.io.Source.fromFile("/usr/share/dict/words").mkString
  // Evaluated every time words is used

  /**
   * Exception: same way as in Java but
   * has no checked exception.
   */

  // throw a exception
  def sqrtFunc(x: Int) =
    if (x >= 0) scala.math.sqrt(x)
    else throw new IllegalArgumentException("x should not be negative")

  /**
   * A throw expression has the special type Nothing.
   * If one branch has type Nothing,
   * the type of the if/else expression is the
   * type of the other branch (Double).
   */
  val d: Double = sqrtFunc(-1)

  val file = "/usr/share/dict/wordsX"
  val file2 = "/root/file"

  try {
    val s: String =
    scala.io.Source.fromFile(file).mkString
  } catch {
    case e:java.io.FileNotFoundException => println(e.getMessage)
  }

  def process(u: URL) {
	  val img = ImageIO.read(u)
			  JOptionPane.showMessageDialog(null, null, null, 0, new ImageIcon(img))
  }

  val messiGIF = "https://i.pinimg.com/originals/0c/44/0a/0c440a567f8b9a6d164c3bc5ec53a7eb.gif"
  val badURL = "hppts://google.com"

  try {
	  process(new URL(messiGIF))
  } catch {
    //use _ for the variable name if you don’t need it.
    case _: java.net.MalformedURLException => println("Bad URL")
    case ex: java.io.IOException => ex.printStackTrace()
  }

  try {
	  process(new URL(badURL))
  } catch {
    case _: java.net.MalformedURLException => println("Bad URL")
    case ex: java.io.IOException => ex.printStackTrace()
  } finally {
    println("Finnaly")
  }

  try {
    val x = 1/0
  } catch {
    case _: java.lang.ArithmeticException => println("catch")
  } finally {
    println("Do something")
  }
}
