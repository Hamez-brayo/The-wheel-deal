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
import com.example.thewheeldeal.databinding.DriverBinding
import com.example.thewheeldeal.databinding.LandingPageListsBinding

class DriverAdapter(var c:Context,var DriverList:ArrayList<Drivers>
):RecyclerView.Adapter<DriverAdapter.DriverViewHolder>(){
    inner class DriverViewHolder(var v:DriverBinding):RecyclerView.ViewHolder(v.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DriverViewHolder {
        val inflter = LayoutInflater.from(parent.context)
        val v = DataBindingUtil.inflate<DriverBinding>(
            inflter, R.layout.driver,parent,
            false)
        return DriverViewHolder(v)
    }

    override fun onBindViewHolder(holder: DriverViewHolder, position: Int) {
        val newList = DriverList[position]
       // holder.v.isDrivers = DriverList[position]
        holder.v.root.setOnClickListener {
            val name=newList.DrName
            val imgUrl = newList.DrimageUrl
            val price = newList.DrCharge

            /**set Data*/
            val mIntent = Intent(c,Driver_details_activity::class.java)
            mIntent.putExtra("DrName",name)
            mIntent.putExtra("image",imgUrl)
            mIntent.putExtra("price",price)


            c.startActivity(mIntent)
        }    }

    override fun getItemCount(): Int {
        return DriverList.size
    }


}






