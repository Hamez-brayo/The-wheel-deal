package com.example.thewheeldeal

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class DriverAdapter(val context: Context, val DriverList:ArrayList<Drivers>): RecyclerView.Adapter<DriverAdapter.DriverViewHolder>(){

    class DriverViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val DrName : TextView =itemView.findViewById(R.id.DriverName)
        var DriverImage: ImageView =itemView.findViewById(R.id.DriverImage)
        val charges: TextView =itemView.findViewById(R.id.DriverCharges)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DriverViewHolder {
        val itemView= LayoutInflater.from(parent.context).inflate(R.layout.driver,parent ,false)
        return DriverAdapter.DriverViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: DriverViewHolder, position: Int) {
        val currentDriver=DriverList[position]

        holder.DrName.text=currentDriver.DrName
        Glide.with(context).load(currentDriver.DrimageUrl).into(holder.DriverImage)
        holder.charges.text=currentDriver.DrCharge

    }

    override fun getItemCount(): Int {
        return DriverList.size

    }
}

