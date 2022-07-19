package com.example.thewheeldeal

import android.app.ProgressDialog
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.thewheeldeal.databinding.ActivityUserProfileBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.io.File
import java.text.SimpleDateFormat
import java.util.*


class UserProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUserProfileBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var user: User
    private lateinit var uid: String
    private lateinit var databaseReference: DatabaseReference
    private lateinit var storageReference: StorageReference
    private lateinit var database: FirebaseDatabase
    private lateinit var imageUri: Uri

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityUserProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = FirebaseAuth.getInstance()
        database=FirebaseDatabase.getInstance()
        databaseReference=database.reference.child("profile")

        val uid = auth.currentUser?.uid.toString()
        databaseReference=FirebaseDatabase.getInstance().getReference("profile")

        binding.driverProfileButton.setOnClickListener{
            val intent=Intent(this@UserProfileActivity, Driver_Add:: class.java)
            startActivity(intent)
        }

        binding.btnSubmit.setOnClickListener{
            val firstname =binding.etFirstName.text.toString()
            val lastname = binding.etLastName.text.toString()
            val email= binding.etEmail.text.toString()
            val mobilenum= binding.etMobileNumber.text.toString()

            val user= User( firstname,lastname, email, mobilenum)

            if(uid.isNotEmpty()) {


            }else if (uid!= null){
                databaseReference.child(uid).setValue(user).addOnCompleteListener {
                    if (it.isSuccessful){

                        uploadProfilePic()


                    }else{
                        Toast.makeText(this@UserProfileActivity,"Failed to update profile", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        binding.ivUserPhoto.setOnClickListener{
            selectImage()
        }

        loadProfile()



        }
    private fun loadProfile(){
        val user=auth.currentUser
        val userreference=databaseReference.child(user?.uid!!)

        binding.etEmail.hint="Email -->"+user.email
        userreference.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {

                binding.etFirstName.hint="Firstname -->"+snapshot.child("firstname").value.toString()
                binding.etLastName.hint="Lastname -->"+snapshot.child("lastname").value.toString()
                binding.etMobileNumber.hint="Contact-->"+snapshot.child("contact").value.toString()
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
        binding.logoutBtn.setOnClickListener{
            auth.signOut()
            startActivity(Intent(this@UserProfileActivity,Login_activity::class.java))
            finish()
        }



    }




    private fun selectImage() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action= Intent.ACTION_GET_CONTENT

        startActivityForResult(intent, 100)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode==100&& resultCode== RESULT_OK){
            imageUri=data?.data!!
            binding.ivUserPhoto.setImageURI(imageUri)
        }
    }

    private fun uploadProfilePic() {

        val progressDialog= ProgressDialog(this)
        progressDialog.setMessage("Uploading Listing...")
        progressDialog.setCancelable(false)
        progressDialog.show()

        val formatter = SimpleDateFormat("yyyy_MM_dd_HH_mm_ss", Locale.getDefault())
        val now = Date()
        val fileName=formatter.format(now)

        val storageReference = FirebaseStorage.getInstance().getReference("images/$fileName")
        storageReference.putFile(imageUri).
        addOnSuccessListener{
            binding.ivUserPhoto.setImageURI(null)
            Toast.makeText(this@UserProfileActivity, "Successfully uploaded", Toast.LENGTH_SHORT).show()
            if (progressDialog.isShowing)progressDialog.dismiss()

        }.addOnFailureListener{
            if(progressDialog.isShowing)progressDialog.dismiss()
            Toast.makeText(this@UserProfileActivity,"failed",Toast.LENGTH_SHORT).show()

        }


        imageUri=Uri.parse("android.resource://$packageName/${R.drawable.ic_user_placeholder}")
        this.storageReference =FirebaseStorage.getInstance().getReference("Users/"+ auth.currentUser?.uid+"jpg")
       this.storageReference.putFile(imageUri).addOnSuccessListener{
            Toast.makeText(this@UserProfileActivity,"Profile Successfully Updated",Toast.LENGTH_SHORT).show()
        }.addOnFailureListener{

            Toast.makeText(this@UserProfileActivity,"Failed to Upload The Image",Toast.LENGTH_SHORT).show()
        }


    }

}



