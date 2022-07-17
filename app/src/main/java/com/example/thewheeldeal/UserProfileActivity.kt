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
    private lateinit var imageUri: Uri

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityUserProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = FirebaseAuth.getInstance()
        val uid = auth.currentUser?.uid.toString()
        databaseReference=FirebaseDatabase.getInstance().getReference("Users")

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
                getUserData()


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



        }

    private fun getUserData() {
        databaseReference.child(uid).addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                user = snapshot.getValue(User::class.java)!!
                binding.etFirstName.setText(user.firstname)
                binding.etLastName.setText(user.lastname)
                binding.etEmail.setText(user.email)
                binding.etMobileNumber.setText(user.mobilenum)

                getUserProfile()
            }

            override fun onCancelled(error: DatabaseError) {

                Toast.makeText(this@UserProfileActivity,"Failed To Get User Profile Data", Toast.LENGTH_SHORT).show()

            }

        })
    }

    private fun getUserProfile() {

        storageReference=FirebaseStorage.getInstance().reference.child("Users/$uid.jpg")
        val localFile = File.createTempFile("tempImage","jpg")
        storageReference.getFile(localFile).addOnSuccessListener {
            val bitmap=BitmapFactory.decodeFile(localFile.absolutePath)
            binding.ivUserPhoto.setImageBitmap(bitmap)


        }.addOnFailureListener{
            Toast.makeText(this@UserProfileActivity,"Failed To Retrieve Image", Toast.LENGTH_SHORT).show()

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



