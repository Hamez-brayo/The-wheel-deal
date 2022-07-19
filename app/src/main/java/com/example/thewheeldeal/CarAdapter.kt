package com.example.thewheeldeal

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.thewheeldeal.databinding.LandingPageListsBinding
import java.lang.reflect.Type

class CarAdapter(var c:Context,var carList:ArrayList<Cars>
):RecyclerView.Adapter<CarAdapter.CarViewHolder>() {
    inner class CarViewHolder(var v:LandingPageListsBinding): RecyclerView.ViewHolder(v.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder {
        val inflter = LayoutInflater.from(parent.context)
        val v = DataBindingUtil.inflate<LandingPageListsBinding>(
            inflter, R.layout.landing_page_lists,parent,
            false)
        return CarViewHolder(v)
    }

    override fun onBindViewHolder(holder: CarViewHolder, position: Int) {
        val newList = carList[position]
        holder.v.isCars = carList[position]
        holder.v.root.setOnClickListener {
            val type=newList.type
            val imgUrl = newList.imageUrl
            val model = newList.model
            val price = newList.price

            /**set Data*/
            val mIntent = Intent(c,Item_Details_Activity::class.java)
            mIntent.putExtra("type",type)
            mIntent.putExtra("image",imgUrl)
            mIntent.putExtra("model",model)
            mIntent.putExtra("price",price)


                    c.startActivity(mIntent)
        }    }

    override fun getItemCount(): Int {
        return  carList.size
    }

}