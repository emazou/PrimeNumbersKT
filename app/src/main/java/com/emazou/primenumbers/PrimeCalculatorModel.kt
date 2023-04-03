package com.emazou.primenumbers

class PrimeCalculatorModel {
    fun isPrime(value: Int): Boolean{
        for (i in 2..(Math.sqrt(value.toDouble()).toInt())) {
            if(value % i == 0) {
                return false
            }
        }
        return true
    }
    fun giveMeXPrime(amount: Int): List<Int> {
        val listResult = mutableListOf<Int>()
        var counterPrime = 0
        var value = 2

        while (counterPrime < amount) {
            if(isPrime(value)) {
                counterPrime++
                listResult.add(value)
            }
            value++
        }
        return listResult
    }
}