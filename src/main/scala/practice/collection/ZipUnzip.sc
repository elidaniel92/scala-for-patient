// Zip and Unzip

val names = Array[String]("Daniel", "John", "Anne")
val scores = Array[Int](10, 20, 30)

val zipNamesAndScores = names.zip(scores)
val (names2, scores2) = zipNamesAndScores.unzip
