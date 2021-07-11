/**
 * Note, sometimes using "s" instead "d" for Int can work
 */
String.format("%3s", 1)
1.formatted("%3s")

val whiteSpace = 3
var strFormatted = ""

// Left White Space
strFormatted = String.format("%" + whiteSpace + "s", "X")
assert(strFormatted.length == 3)
strFormatted

// Right White Space
strFormatted = String.format("%" + (whiteSpace * -1) + "s", "X")
assert(strFormatted.length == 3)
strFormatted

// Format vs Formatted (three white space)
String.format("%3s", "X")
String.format("%-3s", "X")
"X".formatted("%3s")
"X".formatted("%-3s")
// Int Type
String.format("%3d", 1)
String.format("%-3d", 1)
1.formatted("%3d")
1.formatted("%-3d")

// Fill with zero an String, Does not Work (java.util.FormatFlagsConversionMismatchException)
String.format("%03s", "X")
"X".formatted("%03s")

// Fill with zero Int Type
String.format("%03d", 1)
1.formatted("%03d")

