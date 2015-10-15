package com.infomagnetic.example.inverse1D.test.discrepancy

import breeze.linalg.DenseVector
import com.infomagnetic.common.WriteToFile
import com.infomagnetic.example.inverse1D.shaw.Shaw
import com.infomagnetic.regularization.CGLS
import org.scalatest.FunSuite

import scala.util.Random

class DiscrepancyTest extends FunSuite{
  test("initAwithF") {
    val r = Random
    val berror = Seq.fill(40)(r.nextDouble()/10000).to[Vector]
    val shaw = new Shaw(40, berror)
    val xExact = shaw.x
    val xExactFile = "src/main/python/discranpancyprinciple/xExact.txt"
    WriteToFile.arrayToTxT(xExactFile, xExact.toArray)
    val xReconstruct: DenseVector[Double] = CGLS.calculateX(shaw.a, shaw.b, 8)
    val xReconstruct1: Array[Double] = xReconstruct.toArray
    val xReconstruct1File = "src/main/python/discranpancyprinciple/simulation1/simulation1.txt"
    WriteToFile.arrayToTxT(xReconstruct1File, xReconstruct1)
  }
}
