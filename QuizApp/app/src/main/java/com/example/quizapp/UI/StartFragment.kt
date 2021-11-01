package com.example.quizapp.UI

import android.annotation.SuppressLint



import android.content.pm.PackageManager
import android.database.Cursor
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.quizapp.MainActivity
import com.example.quizapp.ActivityResult.PickContact
import com.example.quizapp.ActivityResult.PickPhoto
import com.example.quizapp.R
import com.example.quizapp.Utils.SharedViewModel
import com.google.android.material.snackbar.Snackbar


class StartFragment : Fragment() {
    private lateinit var btnGetStarted: Button
    private lateinit var etYourName: EditText
    private lateinit var btnContact: Button
    private lateinit var ivImagePicker: ImageView
    private val model: SharedViewModel by activityViewModels()


    companion object {
        const val TAG = "START FRAGMENT"
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_start, container, false)
        initializeView(view)
        registerListeners()
        return view
    }
    @SuppressLint("Range")
    val getContact = registerForActivityResult(PickContact()) {
        Log.i(MainActivity.TAG, "getContact result ${it.toString()}")

        val cursor: Cursor =
            requireActivity().contentResolver.query(it!!, null, null, null, null)!!
        if (cursor.moveToFirst()) {
            val contactName =
                cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME))
            etYourName.setText(contactName)
        }
    }

    private val getPhoto = registerForActivityResult(PickPhoto()) { selectedUri ->
        if (selectedUri != null) {
            ivImagePicker.setImageURI(selectedUri)
        }

    }
    private fun initializeView(view:View) {
        btnGetStarted = view.findViewById(R.id.btnGetStarted) as Button
        etYourName = view.findViewById(R.id.etYourName) as EditText
        btnContact = view.findViewById(R.id.btnContact) as Button
        ivImagePicker = view.findViewById(R.id.ivImagePicker) as ImageView
    }

    private fun registerListeners() {
        btnGetStarted.setOnClickListener {
            val text = etYourName.text.toString()
            Log.i(TAG, "Button clicked, edit text's content: $text")
//            Toast.makeText(this,"Button clicked, edit text's content: ${etYourName.text.toString()}",Toast.LENGTH_SHORT).show()
            if (text.isNotEmpty()) {
//                val snack = Snackbar.make(
//                    it,
//                    "Button clicked, edit text's content: ${etYourName.text}",
//                    Snackbar.LENGTH_SHORT
//                )
//                snack.show()
                model.setPlayerName(text)
                findNavController().navigate(R.id.action_startFragment_to_quizFragment)


            } else {
                val snack = Snackbar.make(it, "Type in your name!", Snackbar.LENGTH_SHORT)
                snack.show()
            }

        }
        btnContact.setOnClickListener {
            if (checkContactPermission()) {
                getContact.launch(0)
            } else {
                requestContactPermission()
            }
        }

        ivImagePicker.setOnClickListener {
            if (isReadExternalPermissionGranted()) {
                getPhoto.launch(0)
            } else {
                requestReadExternalPermission()
            }
        }
    }



    private fun isReadExternalPermissionGranted(): Boolean {
        return ContextCompat.checkSelfPermission(
            requireContext(),android.Manifest.permission.READ_EXTERNAL_STORAGE
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun requestReadExternalPermission() {
        val permission = arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE)
        ActivityCompat.requestPermissions(requireActivity(), permission,
            MainActivity.EXTERNAL_PERMISSION_CODE
        )
    }

    private fun checkContactPermission(): Boolean {
        return ContextCompat.checkSelfPermission(
            requireContext(),
            android.Manifest.permission.READ_CONTACTS
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun requestContactPermission() {
        val permission = arrayOf(android.Manifest.permission.READ_CONTACTS)
        ActivityCompat.requestPermissions(requireActivity(), permission,
            MainActivity.CONTACT_PERMISSION_CODE
        )

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == MainActivity.CONTACT_PERMISSION_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getContact.launch(0)
            } else {
                Toast.makeText(requireContext(), "Permission denied", Toast.LENGTH_SHORT).show()
            }
        } else if (requestCode == MainActivity.EXTERNAL_PERMISSION_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getPhoto.launch(0)
            } else {
                Toast.makeText(
                    requireContext(),
                    "In order to post an advertisement, you need to provide access to your photos",
                    Toast.LENGTH_LONG
                ).show()
            }

            super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        }
    }
}