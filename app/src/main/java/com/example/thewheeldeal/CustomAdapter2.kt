package com.example.thewheeldeal
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView

class CustomAdapter2(private val mList: List<ItemsViewModel>):RecyclerView.Adapter<CustomAdapter2.ViewHolder>()  {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):ViewHolder{
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.landing_page_lists, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mList.size
    }
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageView15)
        val textView: TextView = itemView.findViewById(R.id.textView)

        init {
            val imageView: ImageView = itemView.findViewById(R.id.imageView15)
            val textView: TextView = itemView.findViewById(R.id.textView)

            itemView.setOnClickListener {

                itemView.findNavController().navigate(R.id.action_categories_to_item_details)
            }

        }


    }



    override fun onBindViewHolder(holder: CustomAdapter2.ViewHolder, position: Int) {

        val ItemsViewModel = mList[position]

        // sets the image to the imageview from our itemHolder class
        holder.imageView.setImageResource(ItemsViewModel.image)

        // sets the text to the textview from our itemHolder class
        holder.textView.text = ItemsViewModel.text
    }
}