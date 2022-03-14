package com.example.thewheeldeal.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.thewheeldeal.models.CallAdapter
import com.example.thewheeldeal.models.Items
import com.example.thewheeldeal.R


class Calls_fragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_calls, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // getting the recyclerview by its id
        val recyclerview = view.findViewById<RecyclerView>(R.id.RVc)

        // this creates a vertical layout Manager
        recyclerview?.layoutManager = LinearLayoutManager(requireActivity())
//        recyclerview.setLayoutManager( LinearLayoutManager(getContext()));

        // ArrayList of class ItemsViewModel
        val data = ArrayList<Items>()

        // This loop will create 20 Views containing
        // the image with the count of view
        for (i in 1..20) {
            data.add(Items(R.drawable.rectangle_icons, "Item " + i))
        }

        // This will pass the ArrayList to our Adapter
        val adapter = CallAdapter(data)

        // Setting the Adapter with the recyclerview
        recyclerview?.adapter = adapter

        view.findViewById<TextView>(R.id.textView18)?.setOnClickListener {
            view.findNavController().navigate(R.id.action_calls_to_chat)
        }
        view.findViewById<ImageView>(R.id.imageView39)?.setOnClickListener {
            view.findNavController().navigate(R.id.action_calls_to_chat)
        }
        view.findViewById<ImageView>(R.id.imageView47)?.setOnClickListener {
            view.findNavController().navigate(R.id.action_calls_to_categories)
        }
        view.findViewById<ImageView>(R.id.imageView45)?.setOnClickListener {
            view.findNavController().navigate(R.id.action_calls_to_landing)
        }
    }



}