package com.infomagnetic.regularization.test

import breeze.linalg.{DenseVector, norm}
import com.infomagnetic.example.inverse1D.shaw.Shaw
import org.scalatest.FunSuite
import com.infomagnetic.regularization.CGLS

/**
 * Created by ypang on 9/17/15.
 */
class CGLSTEST extends FunSuite{
  test("CGLS") {
    val shaw = new Shaw(128, Vector(0, 0, 0))
    val tempa = shaw.a.copy
    val tempb = shaw.b.copy
    val x = CGLS.calculateX(shaw.a, shaw.b, 8)
    assert(norm(shaw.a * x - shaw.b) < 2e-4)
    //check Encapsulation
    assert(tempa == shaw.a)
    assert(tempb == shaw.b)
  }
}
