package com.example.thewheeldeal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.thewheeldeal.databinding.ActivityCategoriesBinding
import com.google.firebase.database.*

class Categories_activity : AppCompatActivity() {
    private lateinit var binding: ActivityCategoriesBinding
    private lateinit var dbref: DatabaseReference
    private lateinit var DriverRecyclerView: RecyclerView
    private lateinit var  DrverArrayList:ArrayList<Drivers>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityCategoriesBinding.inflate(layoutInflater)
        setContentView(binding.root)


        DriverRecyclerView=binding.Drrecyclerview


        DriverRecyclerView.layoutManager = GridLayoutManager( this, 2)

        DriverRecyclerView.setHasFixedSize(true)

        DrverArrayList= arrayListOf<Drivers>()
        getDriverData()



        binding.imageView32.setOnClickListener{
            startActivity(Intent(this@Categories_activity, UserProfileActivity::class.java))

        }
        binding.imageView18.setOnClickListener{
            startActivity(Intent(this@Categories_activity,Car_Add::class.java))
        }
        binding.imageView27.setOnClickListener{
            startActivity(Intent(this@Categories_activity,Landing_activity::class.java))
        }


    }

    private fun getDriverData() {
        dbref= FirebaseDatabase.getInstance().getReference("drivers_upload")
        dbref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                if (snapshot.exists()){
                    for (carSnapshot in snapshot.children){
                        val driver=carSnapshot.getValue(Drivers::class.java)
                        DrverArrayList.add(driver!!)

                    }

                    // DriverRecyclerView.adapter =DriverAdapter(DrverArrayList!!)
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }


        })
    }
}