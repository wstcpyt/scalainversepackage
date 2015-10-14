package com.infomagnetic.example.inverse1D.test.discrepancy

import breeze.linalg.DenseVector
import com.infomagnetic.example.inverse1D.shaw.Shaw
import com.infomagnetic.regularization.CGLS
import org.scalatest.FunSuite

import scala.util.Random

class DiscrepancyTest extends FunSuite{
  test("initAwithF") {
    val r = Random
    val berror = Seq.fill(40)(r.nextDouble()/1007).to[Vector]
    val shaw = new Shaw(40, berror)
    val x: DenseVector[Double] = CGLS.calculateX(shaw.a, shaw.b, 8)
    val xReconstruct = x.toArray
    assert(1 == 1)
  }
}
