package com.example.thewheeldeal

import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import com.example.thewheeldeal.databinding.ActivityCarAddBinding
import com.google.firebase.storage.FirebaseStorage
import com.rilixtech.widget.countrycodepicker.CountryCodePicker
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.*

class Car_Add : AppCompatActivity() {

    lateinit var binding: ActivityCarAddBinding
    lateinit var ImageUri: Uri
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =ActivityCarAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ivCarPhoto.setOnClickListener{

            selectImage()
        }
        binding.imageView62.setOnClickListener{

            uploadImage()
        }
    }
    private fun selectImage(){

        val intent = Intent()
        intent.type = "image/*"
        intent.action= Intent.ACTION_GET_CONTENT
      //  intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE)



        startActivityForResult(intent, 100)


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode==100&& resultCode== RESULT_OK){
            ImageUri=data?.data!!
            binding.ivCarPhoto.setImageURI(ImageUri)

//
//            binding.imageView65.isVisible(false)
        }
    }
    private fun uploadImage(){

        val progressDialog=ProgressDialog(this)
        progressDialog.setMessage("Uploading Listing...")
        progressDialog.setCancelable(false)
        progressDialog.show()

        val formatter = SimpleDateFormat("yyyy_MM_dd_HH_mm_ss", Locale.getDefault())
        val now =Date()
        val fileName=formatter.format(now)



        val storageReference = FirebaseStorage.getInstance().getReference("images/$fileName")
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