var str = "X"

val strCopy = str

str = "Y"

print(strCopy) // Print X

var strObj = new String("X")

val strObjCopy = strObj

strObj = "Y"

print(strObjCopy) // Print X

var value = Array('A', 'B', 'C')

// Return hash code of the object
value.toString

// Convert Array of Char to String. All code bellow is equivalent
String.valueOf(value)
String.copyValueOf(value)
new String(value)