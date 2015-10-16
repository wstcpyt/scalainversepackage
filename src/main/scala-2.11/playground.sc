Vector.fill(100)(0).zipWithIndex.map{t => t._2}

var a = Vector(1, 2, 3)
a.updated(0, 2)
a = a :+ 4