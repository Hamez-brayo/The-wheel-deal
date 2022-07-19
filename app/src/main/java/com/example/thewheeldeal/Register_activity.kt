package com.example.thewheeldeal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.example.thewheeldeal.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class Register_activity : AppCompatActivity() {
    lateinit var binding: ActivityRegisterBinding
    lateinit var auth:FirebaseAuth
    var databaseReference: DatabaseReference?=null
    var database:FirebaseDatabase?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth=FirebaseAuth.getInstance()
        database=FirebaseDatabase.getInstance()
        databaseReference=database?.reference!!.child("profile")

        register()




    }
    private fun register()
    {
        binding.button5.setOnClickListener {
            if (TextUtils.isEmpty(R.id.FirstName.toString())) {
                binding.FirstName.error = "Please Enter First Name"
                return@setOnClickListener

            } else if (TextUtils.isEmpty(R.id.LastName.toString())) {
                binding.LastName.error = "Please Enter Last Name"
                return@setOnClickListener

            } else if (TextUtils.isEmpty(R.id.et_IDnum.toString())) {
                binding.etIDnum.error = "Please Enter ID number"
                return@setOnClickListener

            } else if (TextUtils.isEmpty(R.id.et_email.toString())) {
                binding.etEmail.error = "Please Enter Email"
                return@setOnClickListener

            } else if (TextUtils.isEmpty(R.id.et_contact.toString())) {
                binding.etContact.error = "Please Enter Contact"
                return@setOnClickListener

            } else if (TextUtils.isEmpty(R.id.et_password.toString())) {
                binding.etPassword.error = "Please Enter Password"
                return@setOnClickListener

            } else if (TextUtils.isEmpty(R.id.et_passwordConfirm.toString())) {
                binding.etPasswordConfirm.error = "Please Confirm Password"
                return@setOnClickListener

            }
            val FirstName=binding.FirstName.text.toString()
            val LastName=binding.LastName.text.toString()
            val ID=binding.etIDnum.text.toString()
            val Email= binding.etEmail.text.toString()
            val Num=binding.etContact.text.toString()
            val Password= binding.etPassword.text.toString()

            val Users=User(FirstName,LastName,ID, Email,Num)
            databaseReference?.push()?.setValue(Users)
            auth.createUserWithEmailAndPassword(Email, Password).addOnCompleteListener {
                if (it.isSuccessful) {
                    val currentUser = auth.currentUser
                    val currentUserDB=databaseReference?.child((currentUser?.uid!!))
                    currentUserDB?.child("firstname")?.setValue(R.id.FirstName.toString())
                    currentUserDB?.child("lastname")?.setValue(R.id.LastName.toString())
                    currentUserDB?.child("id_number")?.setValue(R.id.et_IDnum.toString())

                    currentUserDB?.child("contact")?.setValue(R.id.et_contact.toString())

                    Toast.makeText(this@Register_activity,"Welcome to the wheel deal", Toast.LENGTH_LONG).show()
                    startActivity(Intent(this@Register_activity,Landing_activity::class.java))


                }else{
                    Toast.makeText(this@Register_activity,"Registration failed!! Please try again!", Toast.LENGTH_LONG).show()

                }
            }
        }
        binding.Login.setOnClickListener{
            startActivity(Intent(this@Register_activity,Login_activity::class.java))
        }


    }
}