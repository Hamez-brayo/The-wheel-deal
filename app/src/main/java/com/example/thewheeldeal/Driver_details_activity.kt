package com.example.thewheeldeal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_item_details.*

class Driver_details_activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_driver_details)

        val DriverIntent = intent
        val name = DriverIntent.getStringExtra("DrName")
        val image= DriverIntent.getStringExtra("image")
        val price=DriverIntent.getStringExtra("price")

        DrName.text = name
        DrContact.text = price
        imageView35.loadImage(image, getProgessDrawable(this))
    }
}