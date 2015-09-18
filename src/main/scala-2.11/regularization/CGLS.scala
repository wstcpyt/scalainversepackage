package regularization

import breeze.linalg.{norm, DenseMatrix, DenseVector}
import math._

/**
 * Created by ypang on 9/17/15.
 */
object CGLS {
  def calculateX(a:DenseMatrix[Double], b:DenseVector[Double], k:Int):DenseVector[Double] = {
    val matrixLength = b.size
    var x = DenseVector.zeros[Double](matrixLength)
    var r = b - a * x
    var d = a.t * r
    (0 until k).foreach{
      _ => {
        val alpha = pow(norm(a.t * r) / norm(a * d), 2.0)
        x = x + d :*= alpha
        val tempr = r
        r = r - (a * d) :*= alpha
        val beta = pow(norm(a.t * r) / norm(a.t * tempr) , 2.0)
        d = a.t * r + d :*= beta
      }
    }
    x
  }
}
