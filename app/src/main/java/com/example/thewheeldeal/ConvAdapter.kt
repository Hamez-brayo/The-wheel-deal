package com.example.thewheeldeal

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView

class ConvAdapter(private val mList: List<Items>): RecyclerView.Adapter<ConvAdapter.ViewHolder>()  {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConvAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.landing_page_lists, parent, false)

        return ConvAdapter.ViewHolder(view)
    }
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageView15)
        val textView: TextView = itemView.findViewById(R.id.textView)
        init {
            val imageView: ImageView = itemView.findViewById(R.id.imageView15)
            val textView: TextView = itemView.findViewById(R.id.textView)

        }
    }
    override fun onBindViewHolder(holder: ConvAdapter.ViewHolder, position: Int) {
        val ItemsViewModel = mList[position]

        // sets the image to the imageview from our itemHolder class
        holder.imageView.setImageResource(ItemsViewModel.image)

        // sets the text to the textview from our itemHolder class
        holder.textView.text = ItemsViewModel.text
    }

    override fun getItemCount(): Int {
        return mList.size
    }
}