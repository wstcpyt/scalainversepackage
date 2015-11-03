package com.infomagnetic.CEA

import com.infomagnetic.common.CommonTypedef._

import scala.util.Random

object  CEAlgorithm {
  private val random = new Random()
  def initPopulation(populationLength: Int, individualPropertiesSize: Int, randMin: Double, randMax: Double, randBase:Int = 100):Population  = {
    Vector.fill(populationLength, populationLength)(0).map { row =>
      row.map { _ =>
        val newProperties = Vector.fill[Double](individualPropertiesSize)(0.0).map { _ =>
          val randomValue = generateRandomNumber(randMin, randMax, randBase)
          randomValue
        }
        new Individual(newProperties, 0.0)
      }
    }
  }

  def produceOffSpring(x: Int, y: Int, population: Population, randMin: Double, randMax: Double, randBase:Int = 100): Vector[Individual] = {
    def mate(parentA: Individual, parentB: Individual): Individual = {
      val mutationRate = 0.1
      val propertiesSize = parentA.individualProperites.length
      //crossOver
      val crossposition = random.nextInt(propertiesSize)
      val randBeta = random.nextDouble()
      var properties = Vector.fill(propertiesSize)(0).zipWithIndex.map { t =>
        val i = t._2
        if (i <= crossposition) {
          parentB.individualProperites(i) + randBeta * (parentA.individualProperites(i) - parentB.individualProperites(i))
        }
        else {
          parentB.individualProperites(i)
        }
      }
      //mutate
      if (random.nextDouble() < mutationRate) {
        val mutatePosition = random.nextInt(propertiesSize)
        val randomValue = generateRandomNumber(randMin, randMax, randBase)
        properties = properties.updated(mutatePosition, randomValue)
      }
      new Individual(properties, parentB.fitness)
    }

    val populationLength = population.length
    var offSpring = Vector[Individual]()
    val parentA = population(x)(y)
    //up offspring
    if (y - 1 >= 0) {
      val parentB = population(x)(y - 1)
      offSpring = offSpring :+ mate(parentA, parentB)
      offSpring
    }
    //down offspring
    if (y + 1 < population.head.length) {
      val parentB = population(x)(y + 1)
      offSpring = offSpring :+ mate(parentA, parentB)
      offSpring
    }
    //left offspring
    if (x - 1 >= 0) {
      val parentB = population(x - 1)(y)
      offSpring = offSpring :+ mate(parentA, parentB)
      offSpring
    }
    //right offspring
    if (x + 1 < population.length) {
      val parentB = population(x + 1)(y)
      offSpring = offSpring :+ mate(parentA, parentB)
      offSpring
    }
    offSpring
  }
  def generateRandomNumber(randMin: Double, randMax: Double, randBase: Int) = {
    val randomTemp = random.nextDouble()
    randMin + (randMax - randMin) * randomTemp
  }
}