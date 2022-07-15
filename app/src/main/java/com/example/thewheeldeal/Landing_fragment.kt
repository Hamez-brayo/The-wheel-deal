package com.example.thewheeldeal

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage

class Landing_fragment : Fragment() {

    private var mStorage: FirebaseStorage?=null
    private var mDatabaseRef: DatabaseReference?=null
    private var mDBListener: ValueEventListener?=null
    private lateinit var mCars:MutableList<Items>
    private lateinit var customAdapter:CustomAdapter


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
        recyclerview?.setHasFixedSize(true)

        // this creates a vertical layout Manager
        recyclerview?.layoutManager = GridLayoutManager(requireContext(),2)

        // ArrayList of class ItemsViewModel
       val data = ArrayList<Items>()

        // This loop will create 20 Views containing
        // the image with the count of view
//        for (i in 1..20) {
//            data.add(Items(R.drawable.range_rover, "Item " + i))
//        }
        mCars=ArrayList()
        customAdapter= CustomAdapter(requireActivity() ,mCars)
        mStorage=FirebaseStorage.getInstance()
        mDatabaseRef=FirebaseDatabase.getInstance().getReference("cars_uploads")
        mDBListener= mDatabaseRef!!.addValueEventListener(object:ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                mCars.clear()
                for (carSnapshot in snapshot.children){
                    val upload = carSnapshot.getValue(Items::class.java)
                    upload!!.key = carSnapshot.key
                    mCars.add(upload)

                }
              //  CustomAdapter.notifyDataSetChanged()

            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(requireActivity(),error.message, Toast.LENGTH_SHORT).show()

            }

        })

        // This will pass the ArrayList to our Adapter
        val adapter = CustomAdapter(requireActivity(), data)

        // Setting the Adapter with the recyclerview
        recyclerview?.adapter = adapter

        view.findViewById<ImageView>(R.id.imageView22).setOnClickListener {
            view.findNavController().navigate(R.id.action_landing_to_categories)
        }

        view.findViewById<ImageView>(R.id.imageView23).setOnClickListener {
            view.findNavController().navigate(R.id.action_landing_to_chat)
        }

        view.findViewById<ImageView>(R.id.imageView18).setOnClickListener{
            view.findNavController().navigate(R.id.action_landing_to_car_Add)

        }
        view.findViewById<ImageView>(R.id.imageView24).setOnClickListener{
            view.findNavController().navigate(R.id.action_landing_to_userProfileActivity)
        }




//        view.findViewById<ImageView>(R.id.imageView18).setOnClickListener {
//            Override fun onClick (view: View){
//
//            }


//        }
//        view.findViewById<ImageView>(R.id.imageView18).setOnClickListener{
//           AddCarDialogue(
//               onSubmitClickListener =  { quantity->
//                   Toast.makeText(requireContext(), " ", Toast.LENGTH_SHORT).show()
//               }
//           ).show(parentFragmentManager, "dialog")
//       }


    }
    override fun onDestroy() {
        super.onDestroy()
        mDatabaseRef!!.removeEventListener(mDBListener!!)
    }

}

