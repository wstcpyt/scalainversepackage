package com.infomagnetic.example.inverse1D.shaw

import breeze.linalg.{norm, DenseVector}
import com.infomagnetic.CEA.Individual
import com.infomagnetic.regularization.CGLS

class ShawFitness(individual: Individual) {
  def calculateCGLS(discretizationSize: Int, cglsIndex: Int) ={
    var berror: Vector[Double] = individual.individualProperites
    //add a bias to berror
    berror = berror.map(error => error - 0.05)
    val shaw = new Shaw(discretizationSize, berror)
    val x: DenseVector[Double] = CGLS.calculateX(shaw.a, shaw.b, cglsIndex)
    norm(shaw.a * x - shaw.b)
  }
}
