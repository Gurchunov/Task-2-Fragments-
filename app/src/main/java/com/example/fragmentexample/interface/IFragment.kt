package com.example.fragmentexample.`interface`

import com.example.fragmentexample.data.PhoneItem

interface IFragment {
    fun cretePhone()
    fun updatePhone(index: Int)
    fun listPhones()
    fun onPhoneCreated(phone: PhoneItem?)

}