package com.example.thewheeldeal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import kotlinx.android.synthetic.main.activity_item_details.*

class Item_Details_Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_details)
        val CarIntent = intent
        val type = CarIntent.getStringExtra("type")
        val model = CarIntent.getStringExtra("model")
        val image= CarIntent.getStringExtra("image")
        val price=CarIntent.getStringExtra("price")

        DrName.text = type
        DrContact.text = model
        imageView35.loadImage(image, getProgessDrawable(this))
        textView16.text = price

    }
}