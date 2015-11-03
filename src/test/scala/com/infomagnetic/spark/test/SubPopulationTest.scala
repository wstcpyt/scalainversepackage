package com.infomagnetic.spark.test

import com.infomagnetic.CEA.{SubPopulation, CEAlgorithm}
import org.scalatest.FunSuite

class SubPopulationTest extends FunSuite{
  test("SubPopulation"){
    val population = CEAlgorithm.initPopulation(10, 40, 0, 0.2)
    val subPopulation = new SubPopulation(population, 1)
    assert(subPopulation.thread == 1)
    assert(subPopulation.subPopulation.length == 10)
  }

}
