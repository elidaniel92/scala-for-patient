package chapters

object Cap2_2 extends App {
	var count = 1
			while(count<=10) {
				print(count + " ")
				count+=1
			}
	println

	/**
	 * The construct: for (i <- expression)
	 * 1.to(10) returns a range of numbers.
	 * Note: There is no var before the
	 * variable (i). The type of the variable
	 * is the element type of the collection.
	 */
	for(i <- 1 to 10) {
		println(i)
	}

	/**
	 * To traverse an array use until.
	 * 0 until 10 equal 0 to 9
	 * until = x to (y-1)
	 */
	if((0 until 10) == (0 to 9)) println("equal")
	val str = "ABCDEFGH"
	// 0, 1, 2 ... 9
	for(i <- 0 until str.length()) {
		print(str(i) + "" + str(i).toLower + "\n")
	}
	println
	// get characters from a String
	for(character <- str) {
	  print(character + "" + character.toLower + "\n")
	}

	/**
	 * Note: "var variable..." is not "for(variable..."
	 */
	var variable = "100"
	for(variable <- Array("1","2","3")) {
	  print(variable + " ")
	}
	println(variable)

	/**
	 * You can have multiple "variable <-expression".
	 * Loop ixj (repeat 9 times).
	 * Two generator.
	 * Note that there is no semicolon in the last generator.
	 */
	for(i <- 1 to 3; j <- 1 to 3) {
	  println(i + "" + j)
	}

	/**
	 * Each generator can have a guard,
	 * a Boolean condition preceded by if.
	 * Note that there is  no semicolon before if.
	 */
	println
	for(i <- 65 to 67;
	    j <- 65 to 67 if i != j;
	    k <- 65 to 67 if k != i && k != j) {
	  println(i.toChar + "" + j.toChar + "" + k.toChar)
	}
	// Without semicolons
	for{i <- 65 to 67
	    j <- 65 to 67 if i != j
	    k <- 65 to 67 if k != i && k != j} {
	  println(i.toChar + "" + j.toChar + "" + k.toChar)
	}

	/**
	 * Note the variable hundred ca be
	 * used to generate a range.
	 */
	for(i <- 1 to 3;
      hundred = i * 100;
      j <- (hundred + 1) to (hundred + 3)) {
	  println(j)
	}

	/**
	 * Below ASC and DESC multiplication table.
	 * Note the variable declaration x that can
	 * be used inside the loop.
	 */
	val fromN = 1 // exclude 1 "fromN=2"
	val toN = 10
	for (i <- fromN to toN; j <- fromN to i; x=i*j) {
	  println(i + "X" + j + "=" + x)
	}
	for(i <- fromN to toN; j <- i to toN; x=i*j) {
	  println(i + "X" + j + "=" + x)
	}

	/**
	 * A loop can return a collection.
	 * It is called a for comprehension.
	 * The type of the collection is
	 * the type of the first generator
	 * Generator = i <- 1 to 10
	 */
	for(i <- 1 to 10) yield i/2
	for(character <- "Hello") yield character.toInt
	for(character <- "Hello") yield (character + 1).toChar
}
