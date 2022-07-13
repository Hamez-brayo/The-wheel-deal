package com.example.thewheeldeal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import com.example.thewheeldeal.databinding.ActivityUserProfileBinding
import com.google.firebase.auth.FirebaseAuth

class UserProfileActivity : AppCompatActivity() {

    private lateinit var binding:ActivityUserProfileBinding

    private lateinit var actionBar: ActionBar

    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityUserProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
//
//        actionBar=supportActionBar!!
//        actionBar.title= "Profile"

        firebaseAuth=FirebaseAuth.getInstance()
        checkUser()

        binding.logoutBtn.setOnClickListener{
            firebaseAuth.signOut()
            checkUser()
        }

    }

    private fun checkUser() {
        val firebaseUser =firebaseAuth.currentUser
        if (firebaseUser!= null){
            val emailU= firebaseUser.email
           // binding.etEmail.text = emailU
        }else{
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

}