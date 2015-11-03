package com.infomagnetic.common
import java.io._

object WriteToFile {
  def arrayToTxT[T](fileName: String, array: Array[T]) ={
    val writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName)))
    array.foreach(x => writer.write(x.toString + " "))
    writer.close()
  }
}
