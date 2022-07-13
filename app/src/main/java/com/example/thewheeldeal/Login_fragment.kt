package com.example.thewheeldeal

import android.app.ProgressDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

 class Login_fragment : Fragment() {

    lateinit var auth: FirebaseAuth
    private lateinit var progressDialog: ProgressDialog


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        auth= FirebaseAuth.getInstance()
        view.findViewById<TextView>(R.id.signup).setOnClickListener{
            view.findNavController().navigate(R.id.action_login_to_sign_up)
        }

        view.findViewById<Button>(R.id.button4).setOnClickListener{
            LoginUser(view)
        }
    }

    private fun LoginUser(view: View) {
        val email=view.findViewById<EditText>(R.id.emailInput).text.toString()
        val password=view.findViewById<EditText>(R.id.editTextTextPassword).text.toString()

        if (email.isNotEmpty()&&password.isNotEmpty()) {
            CoroutineScope(Dispatchers.IO).launch{
                try {
                    auth.signInWithEmailAndPassword(email, password)
                    withContext(Dispatchers.Main){
                        checkLoggedInState(view)
                    }
                }catch (e:Exception){
                    withContext(Dispatchers.Main){
                        Toast.makeText(requireActivity(), "", Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }

    private fun checkLoggedInState(view: View) {
        val FirebaseUser=auth.currentUser
        if (FirebaseUser!=null){


            view.findNavController().navigate(R.id.action_login_to_landing)
        }else{
            Toast.makeText(requireActivity(), "Invalid Credentials", Toast.LENGTH_SHORT).show()
        }
    }
}





