package com.example.fragmentexample.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fragmentexample.data.PhoneItem

class PhoneListViewModel:ViewModel() {
    val phoneListLiveData = MutableLiveData<MutableList<PhoneItem>>(mutableListOf())

    var selectedIndex = MutableLiveData<Int>(0)
    fun selectItem(index: Int){
        selectedIndex.value = index
    }
    fun loadPhones(phones: MutableList<PhoneItem>){
        phoneListLiveData.value = phones
    }
    fun addPhones(phones: PhoneItem){
        phoneListLiveData.value!!.add(phones)
    }
    fun getPhonesPosition(position: Int):PhoneItem{
        return  phoneListLiveData.value!![position]
    }
    fun updatePhonePosition(updateItem: PhoneItem, position: Int){
        phoneListLiveData.value!!.set(position, updateItem)
    }
}