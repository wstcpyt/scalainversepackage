package com.infomagnetic.linalg

import breeze.linalg.DenseMatrix
import org.scalatest.FunSuite

/**
 * Created by yutongpang on 9/19/15.
 */
class LinalgTest extends FunSuite{
  test ("toeplitz for Create Toeplitz matrix") {
    val tpmatrix = Linalg.toeplitz(Vector(1.0,2.0,3.0))
    val tpresult = DenseMatrix((1.0, 2.0, 3.0), (2.0, 1.0, 2.0), (3.0, 2.0, 1.0))
    assert(tpmatrix == tpresult)
  }
  test ("toeplitz for Create un-sym Toeplitz matrix"){
    val tpmatrix = Linalg.toeplitz(Vector(1.0, 2.0, 3.0, 4.0), Vector(4.0, 5.0, 6.0))
    val tpresult = DenseMatrix((1, 5, 6), (2, 1, 5), (3, 2, 1), (4, 3, 2))
    assert(tpmatrix == tpresult)
  }
}
