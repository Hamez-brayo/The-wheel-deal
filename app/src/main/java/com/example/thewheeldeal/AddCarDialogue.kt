package com.example.thewheeldeal

import android.app.AlertDialog
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ExpandableListView
import android.widget.ImageView
import androidx.fragment.app.DialogFragment
import com.example.thewheeldeal.databinding.AddCarDialogueInputBinding

class AddCarDialogue(
    private val onSubmitClickListener: ( Float) -> Unit
): DialogFragment (){

    private lateinit var binding :AddCarDialogueInputBinding
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding= AddCarDialogueInputBinding.inflate(LayoutInflater.from(context))

        val builder = AlertDialog.Builder(requireActivity())
        builder.setView(binding.root)

        view?.findViewById<ImageView>(R.id.imageView18)?.setOnClickListener {
            onSubmitClickListener.invoke(binding.textInputLayout.toString().toFloat())

        }
        val dialog=builder.create()
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return dialog

    }

}