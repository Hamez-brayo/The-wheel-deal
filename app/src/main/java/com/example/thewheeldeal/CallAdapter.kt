package com.example.thewheeldeal

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CallAdapter(private val mList: List<ItemsViewModel>): RecyclerView.Adapter<ChatAdapter.ViewHolder>()  {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.call_page_list, parent, false)

        return ChatAdapter.ViewHolder(view)
    }
    override fun getItemCount(): Int {
        return mList.size
    }
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {


        val imageView: ImageView = itemView.findViewById(R.id.imageView52)

        val textView: TextView = itemView.findViewById(R.id.textView17)
//        init {
//         itemView.setOnClickListener{
//                itemView.findNavController().navigate(R.id.action_landing_to_item_details)
//            }
//        }
    }
    override fun onBindViewHolder(holder: ChatAdapter.ViewHolder, position: Int) {
        val ItemsViewModel = mList[position]

        // sets the image to the imageview from our itemHolder class
        holder.imageView.setImageResource(ItemsViewModel.image)

        // sets the text to the textview from our itemHolder class
        holder.textView.text = ItemsViewModel.text
    }


}