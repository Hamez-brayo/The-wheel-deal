package com.example.thewheeldeal

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.navigation.findNavController

class Item_details : Fragment() {




    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        view.findViewById<ImageView>(R.id.imageView28).setOnClickListener {
            view.findNavController().navigate(R.id.action_item_details_to_landing)
        }

    }

}