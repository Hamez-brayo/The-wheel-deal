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
import com.google.firebase.database.*

class Landing_fragment : Fragment() {
    private lateinit var dbref:DatabaseReference
    private lateinit var CarRecyclerView: RecyclerView
    private lateinit var  carArrayList:ArrayList<Cars>




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_landing, container, false)

    }

    private fun getCarData() {

        dbref= FirebaseDatabase.getInstance().getReference("cars_upload")
        dbref.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {

                if (snapshot.exists()){
                    for (carSnapshot in snapshot.children){
                        val car=carSnapshot.getValue(Cars::class.java)
                        carArrayList.add(car!!)

                    }

                    CarRecyclerView.adapter =CarAdapter(requireContext(),carArrayList)
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }


        })


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        CarRecyclerView= view.findViewById(R.id.recyclerview)!!


        CarRecyclerView.layoutManager = GridLayoutManager(requireContext(),  2)

        CarRecyclerView.setHasFixedSize(true)

        carArrayList= arrayListOf<Cars>()
        getCarData()




        view.findViewById<ImageView>(R.id.imageView22).setOnClickListener {
            view.findNavController().navigate(R.id.action_landing_to_categories)
        }


        view.findViewById<ImageView>(R.id.imageView18).setOnClickListener{
            view.findNavController().navigate(R.id.action_landing_to_car_Add)

        }
        view.findViewById<ImageView>(R.id.imageView24).setOnClickListener{
            view.findNavController().navigate(R.id.action_landing_to_userProfileActivity)
        }







    }


}

