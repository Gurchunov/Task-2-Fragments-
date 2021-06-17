package com.example.fragmentexample.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fragmentexample.data.CarItem

class CarViewModel:ViewModel() {
    val phoneListLiveData = MutableLiveData<MutableList<CarItem>>(mutableListOf())

    var selectedIndex = MutableLiveData<Int>(0)
    fun selectItem(index: Int){
        selectedIndex.value = index
    }
    fun loadPhones(cars: MutableList<CarItem>){
        phoneListLiveData.value = cars
    }
    fun addPhones(phones: CarItem){
        phoneListLiveData.value!!.add(phones)
    }
    fun getPhonesPosition(position: Int):CarItem{
        return  phoneListLiveData.value!![position]
    }
    fun updatePhonePosition(updateItem: CarItem, position: Int){
        phoneListLiveData.value!!.set(position, updateItem)
    }
}