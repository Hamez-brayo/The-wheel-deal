package com.example.thewheeldeal

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class CarAdapter( val context: Context, val carList:ArrayList<Cars>): RecyclerView.Adapter<CarAdapter.CarViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder {
        val itemView=LayoutInflater.from(parent.context).inflate(R.layout.landing_page_lists,parent ,false)
        return CarViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CarViewHolder, position: Int) {
        val currentCar=carList[position]

        holder.Type.text=currentCar.type
        Glide.with(context).load(currentCar.imageUrl).into(holder.carImage)
        holder.Model.text=currentCar.model
        holder.Price.text=currentCar.price

    }

    override fun getItemCount(): Int {

        return carList.size
    }

    class CarViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val Type : TextView=itemView.findViewById(R.id.Car_Type)
        var carImage:ImageView=itemView.findViewById(R.id.Car_Image)
        val Model: TextView=itemView.findViewById(R.id.car_Model)
        val Price: TextView=itemView.findViewById(R.id.Car_Price)

    }

}