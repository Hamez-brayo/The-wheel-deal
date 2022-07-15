package com.example.thewheeldeal

import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.webkit.MimeTypeMap
import android.widget.TextView
import android.widget.Toast
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
    private var mStorageRef: StorageReference? = null
    private var mDatabaseRef: DatabaseReference? = null
    private var mUploadTask: StorageTask<*>? = null
    private val PICK_IMAGE_REQUEST = 1

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

            uploadFile()
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

//    private fun uploadImage(){
//
//        val progressDialog=ProgressDialog(this)
//        progressDialog.setMessage("Uploading Listing...")
//        progressDialog.setCancelable(false)
//        progressDialog.show()
//
//        val formatter = SimpleDateFormat("yyyy_MM_dd_HH_mm_ss", Locale.getDefault())
//        val now =Date()
//        val fileName=formatter.format(now)
//
//
//
//        val storageReference = FirebaseStorage.getInstance().getReference("images/$fileName")
//        storageReference.putFile(ImageUri).
//                addOnSuccessListener{
//                    binding.ivCarPhoto.setImageURI(null)
//                    Toast.makeText(this@Car_Add, "Successfully uploaded", Toast.LENGTH_SHORT).show()
//                    if (progressDialog.isShowing)progressDialog.dismiss()
//
//                }.addOnFailureListener{
//                    if(progressDialog.isShowing)progressDialog.dismiss()
//            Toast.makeText(this@Car_Add,"failed",Toast.LENGTH_SHORT).show()
//
//        }
//
//
//
//    }

    private fun uploadFile() {

        val progressDialog= ProgressDialog(this)
        progressDialog.setMessage("Uploading Listing...")
        progressDialog.setCancelable(false)
        progressDialog.show()

        val formatter = SimpleDateFormat("yyyy_MM_dd_HH_mm_ss", Locale.getDefault())
        val now = Date()
        val fileName=formatter.format(now)
        val upload = Items(
            text =findViewById<TextView>(R.id.et_type).text.toString().trim { it <= ' ' },
            model =findViewById<TextView>(R.id.et_model).text.toString().trim { it <= ' ' },
            plateNum =findViewById<TextView>(R.id.et_RegNo).text.toString().trim { it <= ' ' },
            price =findViewById<TextView>(R.id.et_price).text.toString().trim { it <= ' ' },
            description =  findViewById<TextView>(R.id.et_description).text.toString().trim { it <= ' ' }

        )
        val uploadId = mDatabaseRef!!.push().key
        mDatabaseRef!!.child((uploadId)!!).setValue(upload)
        openImagesActivity()


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
//        if (ImageUri != null) {
//            val fileReference = mStorageRef!!.child(
//                System.currentTimeMillis()
//                    .toString() + "." + getFileExtension(ImageUri!!)
//            )
//
//            mUploadTask = fileReference.putFile(ImageUri!!)
//                .addOnSuccessListener { taskSnapshot ->
//                    val handler = Handler()
//                    handler.postDelayed({
//
//                    }, 500)
//                    Toast.makeText(
//                        this@Car_Add,
//                        "Car data Upload successful",
//                        Toast.LENGTH_LONG
//                    )
//                        .show()
//
//                }
//                .addOnFailureListener { e ->
//                    Toast.makeText(this@Car_Add, e.message, Toast.LENGTH_SHORT).show()
//                    Log.e("data","${e.message}")
//                }
//                .addOnProgressListener { taskSnapshot ->
//                    val progress =
//                        (100.0 * taskSnapshot.bytesTransferred / taskSnapshot.totalByteCount)
//                }
//        } else {
//            Toast.makeText(this, "You haven't Selected Any file selected", Toast.LENGTH_SHORT)
//                .show()
//        }
    }
    private fun  openImagesActivity() {
        startActivity(Intent(this@Car_Add, MainActivity::class.java))
    }

}