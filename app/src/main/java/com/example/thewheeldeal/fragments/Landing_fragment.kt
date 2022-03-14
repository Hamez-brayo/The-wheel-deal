package com.example.thewheeldeal.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.thewheeldeal.models.CustomAdapter
import com.example.thewheeldeal.models.Items
import com.example.thewheeldeal.R

class Landing_fragment : Fragment() {
    // TODO: Rename and change types of parameters

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_landing, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // getting the recyclerview by its id
        val recyclerview = view.findViewById<RecyclerView>(R.id.recyclerview)

        // this creates a vertical layout Manager
        recyclerview?.layoutManager = GridLayoutManager(requireContext(),2)

        // ArrayList of class ItemsViewModel
        val data = ArrayList<Items>()

        // This loop will create 20 Views containing
        // the image with the count of view
        for (i in 1..20) {
            data.add(Items(R.drawable.range_rover, "Item " + i))
        }

        // This will pass the ArrayList to our Adapter
        val adapter = CustomAdapter(data)

        // Setting the Adapter with the recyclerview
        recyclerview?.adapter = adapter

        view.findViewById<ImageView>(R.id.imageView22).setOnClickListener {
            view.findNavController().navigate(R.id.action_landing_to_categories)

        }
        view.findViewById<ImageView>(R.id.imageView23).setOnClickListener {
            view.findNavController().navigate(R.id.action_landing_to_chat)

        }

    }



}
