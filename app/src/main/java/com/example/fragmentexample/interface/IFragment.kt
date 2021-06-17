package com.example.fragmentexample.`interface`

import com.example.fragmentexample.data.CarItem

interface IFragment {
    fun cretePhone()
    fun updatePhone(index: Int)
    fun listPhones()
    fun onPhoneCreated(car: CarItem?)

}