package com.example.thewheeldeal

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView


class Categories_fragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_categories, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // getting the recyclerview by its id
        val recyclerview = view.findViewById<RecyclerView>(R.id.recyclerview)

        // this creates a vertical layout Manager
        recyclerview?.layoutManager = GridLayoutManager(requireContext(),2)


        // ArrayList of class ItemsViewModel
        val data = ArrayList<Drivers>()

        // This loop will create 20 Views containing
        // the image with the count of view
        for (i in 1..20) {
            data.add(Drivers(R.drawable.ic_baseline_person_24, "Driver " + i))
        }

        // This will pass the ArrayList to our Adapter
        val adapter = CustomAdapter2(data)

        // Setting the Adapter with the recyclerview
        recyclerview?.adapter = adapter

        view.findViewById<ImageView>(R.id.imageView27)?.setOnClickListener {
            view.findNavController()
                .navigate(R.id.action_categories_to_landing)
        }
        view.findViewById<ImageView>(R.id.imageView32)?.setOnClickListener{
            view.findNavController().navigate(R.id.action_categories_to_userProfileActivity)
        }
        view.findViewById<ImageView>(R.id.imageView18)?.setOnClickListener{
            view.findNavController().navigate(R.id.action_categories_to_car_Add)
        }



    }
}