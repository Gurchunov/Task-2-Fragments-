package com.example.fragmentexample.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fragmentexample.`interface`.IFragment
import com.example.fragmentexample.data.PhoneItem
import com.example.fragmentexample.databinding.ItemPhoneBinding

class PhoneListAdaptor(
    private var phoneList: List<PhoneItem>, private val navigation: IFragment)
    : RecyclerView.Adapter<PhoneListAdaptor.PhonesViewHolder>() {

    inner class PhonesViewHolder(private val itemPhoneBinding: ItemPhoneBinding)
        : RecyclerView.ViewHolder(itemPhoneBinding.root) {

        fun bind(phoneItem: PhoneItem) {
            itemPhoneBinding.itemBrand.text = phoneItem.brand
            itemPhoneBinding.itemModel.text = phoneItem.model
            itemPhoneBinding.itemYear.text = phoneItem.year
            itemPhoneBinding.itemImage.setImageDrawable(phoneItem.img)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhonesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemPhoneBinding = ItemPhoneBinding.inflate(layoutInflater, parent, false)
        return PhonesViewHolder(itemPhoneBinding)
    }

    override fun getItemCount(): Int {
        return phoneList.size
    }

    override fun onBindViewHolder(holder: PhonesViewHolder, position: Int) {
        holder.bind(phoneList[position])
        holder.itemView.setOnClickListener{
        navigation.updatePhone(position)
        }

    }
}