package com.example.thewheeldeal

import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.format.DateFormat
import android.webkit.MimeTypeMap
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.thewheeldeal.databinding.ActivityCarAddBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.StorageTask
import java.text.SimpleDateFormat
import java.util.*

class Car_Add : AppCompatActivity() {

    lateinit var binding: ActivityCarAddBinding
    lateinit var ImageUri: Uri
    private lateinit var mStorageRef: StorageReference
    private lateinit var mDatabaseRef : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding =ActivityCarAddBinding.inflate(layoutInflater)
        setContentView(binding.root)


        mStorageRef = FirebaseStorage.getInstance().getReference("cars_uploads")
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("cars_uploads")



        binding.ivCarPhoto.setOnClickListener{

            selectImage()
        }
        binding.imageView62.setOnClickListener{




           saveCarData()
        }
    }

    private fun saveCarData() {

        val type= binding.etType.text.toString()
        val model = binding.etModel.text.toString()
        val numPlate= binding.etRegNo.text.toString()
        val pricing=binding.etPrice.text.toString()
        val descr=binding.etDescription.text.toString()
        val formatter = SimpleDateFormat("yyyy_MM_dd_HH_mm_ss", Locale.getDefault())
        val now = Date()

        val fileName=formatter.format(now)

        val car=Cars(type,model,numPlate,pricing,descr)
        val mDatabaseRef = FirebaseDatabase.getInstance().getReference("cars_uploads/$fileName")

        mDatabaseRef.push().child(numPlate).setValue(car).addOnSuccessListener{


            binding.etType.text.clear()
            binding.etModel.text.clear()
            binding.etRegNo.text.clear()
            binding.etPrice.text.clear()
            binding.etDescription.text.clear()
            uploadFile()


            Toast.makeText(this, "Car Listing Uploaded successfully", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener {

            Toast.makeText(this, "Error in Uploading The Car Listing", Toast.LENGTH_SHORT).show()
        }

    }


    private fun selectImage(){

        val intent = Intent()
        intent.type = "image/*"
        intent.action= Intent.ACTION_GET_CONTENT



        startActivityForResult(intent, 100)


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if ( requestCode==100&& resultCode== RESULT_OK && data != null && data.data != null){
            ImageUri= data.data!!
            binding.ivCarPhoto.setImageURI(ImageUri)


        }
    }
    private fun getFileExtension(uri: Uri): String? {
        val cR = contentResolver
        val mime = MimeTypeMap.getSingleton()
        return mime.getExtensionFromMimeType(cR.getType(uri))
    }



    private fun uploadFile() {

        val progressDialog= ProgressDialog(this)
        progressDialog.setMessage("Uploading Listing...")
        progressDialog.setCancelable(false)
        progressDialog.show()

        val formatter = SimpleDateFormat("yyyy_MM_dd_HH_mm_ss", Locale.getDefault())
        val now = Date()
        val fileName=formatter.format(now)








        val storageReference = FirebaseStorage.getInstance().getReference("cars_upload/$fileName")
        storageReference.putFile(ImageUri).
                addOnSuccessListener{
                    binding.ivCarPhoto.setImageURI(null)
                    Toast.makeText(this@Car_Add, "Successfully uploaded", Toast.LENGTH_SHORT).show()
                    if (progressDialog.isShowing)progressDialog.dismiss()

                }.addOnFailureListener{
                    if(progressDialog.isShowing)progressDialog.dismiss()
            Toast.makeText(this@Car_Add,"failed",Toast.LENGTH_SHORT).show()
        }

    }


}