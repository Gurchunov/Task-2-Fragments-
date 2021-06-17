package com.example.fragmentexample.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fragmentexample.`interface`.IFragment
import com.example.fragmentexample.data.CarItem
import com.example.fragmentexample.databinding.ItemPhoneBinding

class CarAdaptor(
    private var carList: List<CarItem>, private val navigation: IFragment)
    : RecyclerView.Adapter<CarAdaptor.PhonesViewHolder>() {

    inner class PhonesViewHolder(private val itemPhoneBinding: ItemPhoneBinding)
        : RecyclerView.ViewHolder(itemPhoneBinding.root) {

        fun bind(carItem: CarItem) {
            itemPhoneBinding.itemBrand.text = carItem.brand
            itemPhoneBinding.itemModel.text = carItem.model
            itemPhoneBinding.itemYear.text = carItem.year
            itemPhoneBinding.itemImage.setImageDrawable(carItem.img)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhonesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemPhoneBinding = ItemPhoneBinding.inflate(layoutInflater, parent, false)
        return PhonesViewHolder(itemPhoneBinding)
    }

    override fun getItemCount(): Int {
        return carList.size
    }

    override fun onBindViewHolder(holder: PhonesViewHolder, position: Int) {
        holder.bind(carList[position])
        holder.itemView.setOnClickListener{
        navigation.updatePhone(position)
        }

    }
}