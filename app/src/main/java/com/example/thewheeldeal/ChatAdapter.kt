package com.example.thewheeldeal


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView


class ChatAdapter(private val mList: List<ItemsViewModel>):RecyclerView.Adapter<ChatAdapter.ViewHolder>()  {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.chat_page_list, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mList.size
    }
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
//        val imageViews:ImageView=itemView.fin

        val imageView: ImageView = itemView.findViewById(R.id.imageView44)
//        val imageView2: ImageView = itemView.findViewById(R.id.imageView49)
//        val imageView3: ImageView = itemView.findViewById(R.id.imageView48)
        val textView: TextView = itemView.findViewById(R.id.textView17)
//        val textView1: TextView = itemView.findViewById(R.id.textView20)
//        val textView2: TextView = itemView.findViewById(R.id.textView21)
        init {
//            val imageView: ImageView = itemView.findViewById(R.id.imageView15)
//            val textView: TextView = itemView.findViewById(R.id.textView)
            itemView.setOnClickListener{
                itemView.findNavController().navigate(R.id.action_landing_to_item_details)
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val ItemsViewModel = mList[position]

        // sets the image to the imageview from our itemHolder class
        holder.imageView.setImageResource(ItemsViewModel.image)

        // sets the text to the textview from our itemHolder class
        holder.textView.text = ItemsViewModel.text
    }

}