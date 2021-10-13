package com.example.quizapp

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.quizapp.Utils.EXTRA_NAME
import com.example.quizapp.Utils.EXTRA_URI
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private lateinit var btnGetStarted: Button
    private lateinit var etYourName: EditText
    private lateinit var btnContact: Button
    private lateinit var ivImagePicker: ImageView
    private var selectedUri: Uri? = null

    companion object {
        const val TAG = "MAIN ACTIVITY"
        val CONTACT_PERMISSION_CODE = 440
        val EXTERNAL_PERMISSION_CODE = 442
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initializeView()
        registerListeners()
    }

    @SuppressLint("Range")
    val getContact = registerForActivityResult(PickContact()) {
        Log.i(TAG, "getContact result ${it.toString()}")
        val cursor: Cursor

        cursor = contentResolver.query(it!!, null, null, null, null)!!
        if (cursor.moveToFirst()) {
            var contactName =
                cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME))
            etYourName.setText(contactName)
        }
    }

    val getPhoto = registerForActivityResult(PickPhoto()) { selectedUri ->
        if (selectedUri != null) {
            ivImagePicker.setImageURI(selectedUri)
        }

    }

    private fun registerListeners() {
        btnGetStarted.setOnClickListener {
            val text = etYourName.text.toString()
            Log.i(TAG, "Button clicked, edit text's content: $text")
//            Toast.makeText(this,"Button clicked, edit text's content: ${etYourName.text.toString()}",Toast.LENGTH_SHORT).show()
            if (text.length > 0) {
                val snack = Snackbar.make(
                    it,
                    "Button clicked, edit text's content: ${etYourName.text.toString()}",
                    Snackbar.LENGTH_SHORT
                )
                snack.show()
                val intent = Intent(this, DisplayActivity::class.java)
                intent.putExtra(EXTRA_NAME, text)
                intent.putExtra(EXTRA_URI, selectedUri.toString())
                startActivity(intent)

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

    private fun initializeView() {
        btnGetStarted = findViewById(R.id.btnGetStarted)
        etYourName = findViewById(R.id.etYourName)
        btnContact = findViewById(R.id.btnContact)
        ivImagePicker = findViewById(R.id.ivImagePicker)
    }

    private fun isReadExternalPermissionGranted(): Boolean {
        return ContextCompat.checkSelfPermission(
            this,
            android.Manifest.permission.READ_EXTERNAL_STORAGE
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun requestReadExternalPermission() {
        val permission = arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE)
        ActivityCompat.requestPermissions(this, permission, EXTERNAL_PERMISSION_CODE)
    }

    private fun checkContactPermission(): Boolean {
        return ContextCompat.checkSelfPermission(
            this,
            android.Manifest.permission.READ_CONTACTS
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun requestContactPermission() {
        val permission = arrayOf(android.Manifest.permission.READ_CONTACTS)
        ActivityCompat.requestPermissions(this, permission, CONTACT_PERMISSION_CODE)

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == CONTACT_PERMISSION_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getContact.launch(0)
            } else {
                Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show()
            }
        } else if (requestCode == EXTERNAL_PERMISSION_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getPhoto.launch(0)
            } else {
                Toast.makeText(
                    this,
                    "In order to post an advertisement, you need to provide access to your photos",
                    Toast.LENGTH_LONG
                ).show()
            }

            super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        }
    }
}





