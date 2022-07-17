package com.example.thewheeldeal
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView


class CustomAdapter2(private val mList: List<Drivers>):RecyclerView.Adapter<CustomAdapter2.ViewHolder>()  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.driver, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val DrImage: ImageView = itemView.findViewById(R.id.DriverImage)
        val DrName: TextView = itemView.findViewById(R.id.DriverName)
        val DrPrice:TextView=itemView.findViewById(R.id.DriverCharges)
        init {
            val DrimageView: ImageView = itemView.findViewById(R.id.DriverImage)
            val DrName: TextView = itemView.findViewById(R.id.DriverName)
            val DrChrge:TextView=itemView.findViewById(R.id.DriverCharges)

            itemView.setOnClickListener {

                itemView.findNavController().navigate(R.id.action_categories_to_driver_Details)
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val DriversViewModel = mList[position]

        // sets the image to the imageview from our itemHolder class
     //   holder.imageView.setImageResource(ItemsViewModel.image)

        // sets the text to the textview from our itemHolder class


       holder.DrImage.setImageResource(DriversViewModel.DrImage)
        holder.DrName.text=DriversViewModel.DrName


        holder.DrPrice.text=DriversViewModel.DrPrice
    }
}