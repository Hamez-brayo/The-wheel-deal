package com.example.thewheeldeal

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class conv_fragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_conv_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // getting the recyclerview by its id
        val recyclerview = view.findViewById<RecyclerView>(R.id.ConvRV)

        // this creates a vertical layout Manager
        recyclerview?.layoutManager = LinearLayoutManager(requireContext())

        // ArrayList of class ItemsViewModel
        val data = ArrayList<Items>()

        // This loop will create 20 Views containing
        // the image with the count of view
//        for (i in 1..1) {
//            data.add(Items(R.drawable.rectangle_icons, "Message " + i))
//        }

        // This will pass the ArrayList to our Adapter
        val adapter = ConvAdapter(data)

        // Setting the Adapter with the recyclerview
        recyclerview?.adapter = adapter
    }
}