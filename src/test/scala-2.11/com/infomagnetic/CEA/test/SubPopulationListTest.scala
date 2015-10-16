package com.infomagnetic.CEA.test

import com.infomagnetic.CEA.{SubPopulationList, CEAlgorithm}
import org.scalatest.FunSuite

class SubPopulationListTest extends FunSuite{
  test("generateList"){
    val threadNumber = 5
    val populationLengthPerThread = 5
    val population = CEAlgorithm.initPopulation(threadNumber * populationLengthPerThread, 3, 0, 0.2)
    val subPopulationList = SubPopulationList.generateList(population, threadNumber, threadNumber * populationLengthPerThread,populationLengthPerThread)
    assert(subPopulationList.length == 5)
    assert(subPopulationList.head.subPopulation.length == threadNumber * populationLengthPerThread)
    assert(subPopulationList.head.subPopulation.head.length == 6)
    assert(subPopulationList(1).subPopulation.head.length == 7)
    assert(subPopulationList(4).subPopulation.head.length == 6)
  }
}
