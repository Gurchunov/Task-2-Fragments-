package com.example.fragmentexample.`interface`

import com.example.fragmentexample.data.CarItem

interface IFragment {
    fun creteCar()
    fun updateCar(index: Int)
    fun listCar()
    fun onCarCreated(car: CarItem?)

}