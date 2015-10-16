package com.infomagnetic.CEA

import scala.util.Random

object CEAlgorithm {
  type Population = Vector[Vector[Individual]]
  private val random = new Random()

  def initPopulation(populationLength: Int, individualPropertiesSize: Int, randMin: Double, randMax: Double): Population = {
    Vector.fill(populationLength, populationLength)(0).map { row =>
      row.map { _ =>
        val newProperties = Vector.fill[Double](individualPropertiesSize)(0.0).map { _ =>
          val randomTemp = random.nextDouble()
          val randomValue = randMin + (randMax - randMin) * randomTemp
          randomValue
        }
        new Individual(newProperties, 0.0)
      }
    }
  }

  def produceOffSpring(x: Int, y: Int, population: Population, randMin: Double, randMax: Double): Vector[Individual] = {
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
        val randomTemp = random.nextDouble()
        val randomValue = randMin + (randMax - randMin) * randomTemp
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
    else if (y + 1 < population.head.length) {
      val parentB = population(x)(y + 1)
      offSpring = offSpring :+ mate(parentA, parentB)
      offSpring
    }
    //left offspring
    else if (x - 1 >= 0) {
      val parentB = population(x - 1)(y)
      offSpring = offSpring :+ mate(parentA, parentB)
      offSpring
    }
    //right offspring
    else if (x + 1 < population.length) {
      val parentB = population(x + 1)(y)
      offSpring = offSpring :+ mate(parentA, parentB)
      offSpring
    }
    else {
      offSpring
    }
  }
}