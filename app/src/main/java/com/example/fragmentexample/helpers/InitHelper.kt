package com.example.fragmentexample.helpers

import android.content.Context
import androidx.core.content.ContextCompat
import com.example.fragmentexample.R
import com.example.fragmentexample.data.PhoneItem

object InitHelper {
    fun initDevicesList(context: Context): MutableList<PhoneItem> {
        return mutableListOf(
            PhoneItem(
                "Samsung",
                "A50",
                "Android",
                ContextCompat.getDrawable(context, R.drawable.samsung_a50)
            )
        )
    }
}