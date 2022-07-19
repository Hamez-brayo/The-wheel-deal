package com.example.thewheeldeal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.thewheeldeal.databinding.ActivityLandingBinding
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_landing.*

class Landing_activity : AppCompatActivity() {
    private lateinit var binding: ActivityLandingBinding
    private lateinit var mDatabase: DatabaseReference
    private lateinit var  carList:ArrayList<Cars>

    private lateinit var mAdapter: CarAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityLandingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        carList= ArrayList()
        mAdapter = CarAdapter(this, carList )

       // CarRecyclerView= findViewById(R.id.recyclerview)!!




        recyclerview.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)

        recyclerview.setHasFixedSize(true)




        getCarData()


        binding.imageView24.setOnClickListener{
            startActivity(Intent(this@Landing_activity,UserProfileActivity::class.java))
        }
        binding.imageView18.setOnClickListener{
            startActivity(Intent(this@Landing_activity,Car_Add::class.java))
        }
        binding.imageView22.setOnClickListener{
            startActivity(Intent(this@Landing_activity, Categories_activity::class.java))
        }

    }
    private fun getCarData() {

        mDatabase= FirebaseDatabase.getInstance().getReference("cars_upload")
        mDatabase.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                if (snapshot.exists()){
                    for (carSnapshot in snapshot.children){
                        val car=carSnapshot.getValue(Cars::class.java)
                        carList.add(car!!)

                    }
                    recyclerview.adapter=mAdapter

                }
            }

            override fun onCancelled(error: DatabaseError) {

            }


        })


    }
}