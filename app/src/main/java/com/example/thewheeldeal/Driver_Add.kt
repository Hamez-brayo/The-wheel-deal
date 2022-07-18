package com.example.thewheeldeal

import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.thewheeldeal.databinding.ActivityDriverAddBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.text.SimpleDateFormat
import java.util.*

class Driver_Add : AppCompatActivity() {

    lateinit var binding: ActivityDriverAddBinding
    lateinit var ImageUri: Uri
    private lateinit var mStorageRef: StorageReference
    private lateinit var mDatabaseRef : DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding =ActivityDriverAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mStorageRef = FirebaseStorage.getInstance().getReference("Driver_uploads")
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("Driver_uploads")

        binding.ivIDPhoto.setOnClickListener{
            selectImage()


        }
        binding.uploadBtn.setOnClickListener{

            uploadFile()
        }

    }

    private fun uploadFile() {
        val progressDialog = ProgressDialog(this)
        progressDialog.setMessage("Uploading Listing...")

        progressDialog.setCancelable(false)
        progressDialog.show()

        val formatter = SimpleDateFormat("yyyy_MM_dd_HH_mm_ss", Locale.getDefault())
        val now = Date()
        val fileName = formatter.format(now)


        val storageReference = FirebaseStorage.getInstance().getReference("Driver_upload/$fileName")
        storageReference.putFile(ImageUri).addOnSuccessListener {
            binding.ivIDPhoto.setImageURI(null)
            Toast.makeText(this@Driver_Add, "Successfully uploaded", Toast.LENGTH_SHORT).show()
            if (progressDialog.isShowing) progressDialog.dismiss()

        }.addOnFailureListener {
            if (progressDialog.isShowing) progressDialog.dismiss()
            Toast.makeText(this@Driver_Add, "failed", Toast.LENGTH_SHORT).show()

        }
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    it.result!!.uploadSessionUri.let { uri ->
                        Log.i("FirebaseURL", "uploadFile: $uri")
                        val ImageUrl= uri.toString()
                        saveDriverData(ImageUrl)
                    }
                    it.addOnSuccessListener {
                        Log.i("FirebaseUrl2", "uploadFile: $it")
                    }

                }



            }

    }

    private fun saveDriverData(Url: String) {
        val DrName= binding.etUserName.text.toString()
        val DrContact = binding.etContact.text.toString()
        val DrDlNum= binding.etDlNum.text.toString()
        val DrIdNum=binding.etIDnum.text.toString()
        val DrEmail=binding.etEmailAdd.text.toString()
        val DrimageUrl= Url


        val Driver=Drivers(DrName,DrContact,DrDlNum,DrIdNum,DrEmail,DrimageUrl )

        mDatabaseRef.push().child(DrName).setValue(Driver).addOnSuccessListener{


            binding.etUserName.text.clear()
            binding.etContact.text.clear()
            binding.etDlNum.text.clear()
            binding.etIDnum.text.clear()
            binding.etEmailAdd.text.clear()


            Toast.makeText(this, "Driver Profile Updated successfully", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener {

            Toast.makeText(this, "Error in Updating Driver Profile", Toast.LENGTH_SHORT).show()
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

        if ( requestCode==100&& resultCode== RESULT_OK && data != null && data.data != null){
            ImageUri= data.data!!
            binding.ivIDPhoto.setImageURI(ImageUri)



        }



    }



}