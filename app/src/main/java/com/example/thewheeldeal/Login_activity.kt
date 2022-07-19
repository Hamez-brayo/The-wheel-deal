package com.example.thewheeldeal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.example.thewheeldeal.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class Login_activity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    lateinit var auth:FirebaseAuth



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


        auth=FirebaseAuth.getInstance()
        login()
    }
    private fun login(){
        binding.button4.setOnClickListener{
            if (TextUtils.isEmpty(R.id.et_email.toString())){
                binding.etEmail.error = "Please enter email"
                return@setOnClickListener
            }else  if (TextUtils.isEmpty(R.id.et_password.toString())){
                binding.etPassword.error = "Please enter password"
                return@setOnClickListener
            }
            val Email=binding.etEmail.text.toString()
            val Pass=binding.etPassword.text.toString()
            auth.signInWithEmailAndPassword(Email,Pass).addOnCompleteListener {
                if (it.isSuccessful){

                    startActivity(Intent(this@Login_activity,Landing_activity::class.java))
                    finish()

                }else{
                    Toast.makeText(this@Login_activity,"Invalid Credentials", Toast.LENGTH_LONG).show()


                }
            }
        }
        binding.signup.setOnClickListener{
            startActivity(Intent(this@Login_activity,Register_activity::class.java))
        }
    }
}