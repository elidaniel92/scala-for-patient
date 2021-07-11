/**
 * For me package is like a last name.
 * It can be defined with directories or code.
 */

// java.lang, scala, and Predef are always imported

/**
 * java.lang, scala, and Predef are always imported
 * Unlike all other imports, scala is allowed
 * to override the preceding import. For example, scala.StringBuilder overrides
 * java.lang.StringBuilder instead of conflicting with it.
 */

/**
 * Note: Predef is an object.
 * print is a Predef's method, it is a alias for Console.println(x)
 * Map is a constant that is a alias for immutable.Map
 */

// Define a Package
package com {
  package horstmann {
    package impatient {
      class Employee
    }
  }
}

// Unlike an object or a class, a package can be defined in multiple files
// Add class in Package above
// It could be in another file
package com {
  package horstmann {
    package impatient {
      class Manager
    }
  }
}

// Note: You can access names from the enclosing scope.
package com {
  package horstmann {

    object Utils {
      def percentOf(value: Double, rate: Double) = value * rate / 100
    }

    package impatient {
      object Employee {
        def apply(): Employee = new Employee()
      }
      class Employee {
        var salary = 0
        def giveRaise(rate: scala.Double) {
          salary += Utils.percentOf(salary, rate) // Accessing Utils without imports
        }
      }
    }
  }
}

// Other example
package x {
  package y {
    object ObjA {
      import x.ObjB
      def apply(n: Int) = n % 2 == 0
      def method = new ObjB(1) // Here it is necessary import
    }
  }
}
package x { package y { package x {
  class ObjB(number: Int) {
    def method = ObjA(number) // Accessing ObjA without import
  }
} } }

// In scala, package names are relative, just like inner class names.
// Bellow a solution for possible ambiguity.
package foo { object A { def method = "foo.A" } }
package com {
  package foo { object A { def method = "com.foo" } }
  package bar { object B {
    def method = foo.A.method // Ambiguous situation, relative path, "com.foo" is called.
    def method2 = _root_.foo.A.method // Absolute path
  } }
}

/**
 * NOTE: Most programmers use complete paths for package names, without
 * the _root_ prefix. This is safe as long as everyone avoids names scala, java,
 * com, net, and so on, for nested packages.
 */

package com { package foo { package baz { package qux {
  object C { def method = A.method }
}}}}
// A package clause can contain a “chain,” or path segment, for example:
package com.foo.baz {
  // Members of com and com.foo are not visible here
  package qux { object C { def method = "AAA" } }
}

// Package Objects

// The package and object has the same name
package object people {
  val defaultName = "John Q. Public"
}

// The class Person belongs the package people therefore
// all members of package object people is visible.
package people {
  class Person {
    var name = defaultName // A constant from the package
  }
}

// Package Visibility
package com.br.people {
  class Person(name: String) {
    // Qualifiers, the method description is only visible in people package
    private[people] def description = s"A person with name $name"
  }
}
// You can extend the visibility to an enclosing package
// private[br] def description = s"A person with name $name"

// Imports

package object colors {
  import java.awt.Color
  // The import let use a short name instead of complete/long name
  val red = Color.red
}

/**
 * In Scala, an import statement can be anywhere, not just at the top of a file. The
 * scope of the import statement extends until the end of the enclosing block.
 */

object ImportScope {
  val X = {
    import foo.A
    A
  }
  val Y = {
    import com.foo.A
    A
  }
}

/**
 * The wildcard "_" allow import all members of a package or class or object
 * Note: "all members" includes subpackage (import collection.mutable).
 * import x.y.z._
 */

package pkg {
  object I {
    val variable = "V"
    val function = "F"
    def method = "M"
  }
  object J { def method = "M" }
  object K { def method = "M" }
}

// Import more than one member in one import statement

// import two objects (I, J)
package object selector1 {
  import pkg.{I, J}
  def m1 = I.method
  def m2 = J.method
}
// import two members of object I (variable, function)
package object selector2 {
  import pkg.I.{variable, function}
  def m1 = variable
  def m2 = function
//  def m3 = method
}
// import all object except K
package object selector3 {
  import pkg.{K => _, _}
  def m1 = I.method
  def m2 = J.method
//  def m3 = K.method
}
// import all (variable, function) except method
package object selector4 {
  import pkg.I.{method => _, _}
  def m1 = variable
  def m2 = function
  //  def m3 = method
}
/**
 * To avoid ambiguous situations you can rename
 * import java.util.{HashMap => JavaHashMap}
 * import scala.collection.mutable._
 */
// Rename a variable
package object selector5 {
  import pkg.I.{variable => A}
  def m1 = A
}

