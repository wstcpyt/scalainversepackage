package com.infomagnetic.CEA

import com.infomagnetic.common.CommonTypedef._

import scala.collection.immutable.IndexedSeq

object SubPopulationList {
  def generateList(population: Population, threadNumber: Int, populationLength: Int, populationLengthPerThread: Int): List[SubPopulation] = {
    val subPopulationList = (0 until threadNumber).toList map{thread =>
      if (thread == 0) {
        val subPopulation = Vector.fill(populationLength, populationLengthPerThread + 1)(0).zipWithIndex.map { row =>
          row._1.zipWithIndex.map { col => population(row._2)(col._2)}}
        new SubPopulation(subPopulation, thread)
      }
      else if(thread == threadNumber - 1){
        val subPopulation = Vector.fill(populationLength, populationLengthPerThread + 1)(0).zipWithIndex.map { row =>
          row._1.zipWithIndex.map { col => population(row._2)(col._2 + thread * populationLengthPerThread - 1)}}
        new SubPopulation(subPopulation, thread)
      }
      else {
        val subPopulation = Vector.fill(populationLength, populationLengthPerThread + 2)(0).zipWithIndex.map { row =>
          row._1.zipWithIndex.map { col => population(row._2)(col._2 + thread * populationLengthPerThread - 1)}}
        new SubPopulation(subPopulation, thread)
      }
    }
    subPopulationList
  }
}
