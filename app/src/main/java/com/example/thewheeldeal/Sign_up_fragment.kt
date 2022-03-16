package com.example.thewheeldeal


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.rilixtech.widget.countrycodepicker.CountryCodePicker
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class sign_up_fragment : Fragment() {

    lateinit var auth:FirebaseAuth
    override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if(currentUser != null){

        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        auth= FirebaseAuth.getInstance()
        view.findViewById<Button>(R.id.button5).setOnClickListener{
           RegisterUser(view, auth)

        }
//        view.findViewById<Button>(R.id.button5).setOnClickListener {
//            view.findNavController().navigate(R.id.action_sign_up_to_landing)
//        }
        view.findViewById<TextView>(R.id.Login).setOnClickListener{
            view.findNavController().navigate(R.id.action_sign_up_to_login)
        }
        }

    }

    private fun RegisterUser(view: View, auth: FirebaseAuth) {
        val Fname= view.findViewById<EditText>(R.id.FirstName).text.toString()
        val Lname= view.findViewById<EditText>(R.id.LastName).text.toString()
        val IdNo= view.findViewById<EditText>(R.id.IdNum).text.toString()
        val Email= view.findViewById<EditText>(R.id.email).text.toString()
        val countrypick= view.findViewById<CountryCodePicker>(R.id.ccp).toString()
        val Contact= view.findViewById<EditText>(R.id.Cont).text.toString()
        val password= view.findViewById<EditText>(R.id.Password).text.toString()
        val ConfirmPass= view.findViewById<EditText>(R.id.PassCon).text.toString()
        if (Fname.isNotEmpty()&&Lname.isNotEmpty()&&IdNo.isNotEmpty()&&Email.isNotEmpty()&&countrypick.isNotEmpty()&&Contact.isNotEmpty()&&password.isNotEmpty()&&ConfirmPass.isNotEmpty())
        {
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    auth.createUserWithEmailAndPassword(Email,password)
                    withContext(Dispatchers.Main){
                        checkLoggedInState(view,auth)
                    }
                }catch (e:Exception){
                    withContext(Dispatchers.Main){
                        //Toast.makeText(requireActivity(), "", Toast.LENGTH_LONG).show()
                    }
                }
            }
        }



    }

 fun checkLoggedInState(view: View, auth: FirebaseAuth) {
   if (auth.currentUser==null){

   }else{
       view.findNavController().navigate(R.id.action_sign_up_to_landing)
   }
}



