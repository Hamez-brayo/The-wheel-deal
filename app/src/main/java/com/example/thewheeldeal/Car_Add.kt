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
    private var mStorageRef: StorageReference? = null
    private var mDatabaseRef: DatabaseReference? = null
    private var mUploadTask: StorageTask<*>? = null
    private val PICK_IMAGE_REQUEST = 1
//    private lateinit var _type:EditText
//    private lateinit var _model:EditText
//    private lateinit var _regNo:EditText
//    private lateinit var _price:EditText
//    private lateinit var _description:EditText


//    var _model=binding.etModel.text.toString()
//    var _type=binding.etType.text.toString()
//    var _regNo=binding.etRegNo.text.toString()
//    var _price= binding.etPrice.text.toString()
//    var _description=binding.etDescription.text.toString()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


         //   _type = findViewById(R.id.et_type)
//            _model= findViewById(R.id.et_model)
//            _regNo=findViewById(R.id.et_RegNo)
//            _price=findViewById(R.id.et_price)
//            _description=findViewById(R.id.et_description)

        binding =ActivityCarAddBinding.inflate(layoutInflater)
        setContentView(binding.root)


        mStorageRef = FirebaseStorage.getInstance().getReference("cars_uploads")
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("cars_uploads")



        binding.ivCarPhoto.setOnClickListener{

            //selectImage()
        }
        binding.imageView62.setOnClickListener{
            val type= binding.etType.text.toString()
            val model = binding.etModel.text.toString()
            val numPlate= binding.etRegNo.text.toString()
            val pricing=binding.etPrice.text.toString()
            val descr=binding.etDescription.text.toString()
            mDatabaseRef = FirebaseDatabase.getInstance().getReference("cars_uploads")
            val car=Cars(type,model,numPlate,pricing,descr)
            mDatabaseRef?.child(numPlate)?.setValue(car)?.addOnSuccessListener {
                binding.etType.text.clear()
                binding.etModel.text.clear()
                binding.etRegNo.text.clear()
                binding.etPrice.text.clear()
                binding.etDescription.text.clear()

                Toast.makeText(this, "Car Listing Uploaded successfully", Toast.LENGTH_SHORT).show()
            }?.addOnFailureListener {

                Toast.makeText(this, "Error in Uploading The Car Listing", Toast.LENGTH_SHORT).show()


            }


            //uploadFile()
           //saveCarData()
        }
    }

    private fun saveCarData() {
//        val model= _model.text.toString()
//        val type=_type.text.toString()
//        val plateNum=_regNo.text.toString()
//        val price=_price.text.toString()
//        val description=_description.text.toString()
//
//        if (model.isEmpty()){
//            _model; " Please enter the model of the Car"
//
//        }
//        if (type.isEmpty()){
//            _type;" Please enter the type of the Car"
//
//        }
//        if (plateNum.isEmpty()){
//            _regNo;" Please enter the registration number of the Car"
//
//        }
//        if (price.isEmpty()){
//            _price;" Please enter the pricing of the Car per day"
//
//        }
//        if (description.isEmpty()){
//            _description;" Please enter the description of the Car"
//
//        }
//
//        val carId= mDatabaseRef?.push()?.key!!
//
//            val Cars = Cars(carId, model,type,plateNum,price,description)
//        mDatabaseRef = FirebaseDatabase.getInstance().getReference("cars_uploads")
//
//        mDatabaseRef?.child(carId)?.setValue(Cars)
//            ?.addOnCompleteListener{
//                Toast.makeText(this, " Car Uploaded successfully", Toast.LENGTH_LONG).show()
//
//            }?.addOnFailureListener{err->
//                Toast.makeText(this, "Error in uploading the listing ${err.message}", Toast.LENGTH_LONG).show()
//
//            }



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



    private fun uploadFile() {

        val progressDialog= ProgressDialog(this)
        progressDialog.setMessage("Uploading Listing...")
        progressDialog.setCancelable(false)
        progressDialog.show()

        val formatter = SimpleDateFormat("yyyy_MM_dd_HH_mm_ss", Locale.getDefault())
        val now = Date()
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