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
        val CONTACT_PICK_CODE = 441
        val EXTERNAL_PERMISSION_CODE = 442
        val EXTERNAL_PICK_CODE = 443
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        btnGetStarted = findViewById(R.id.btnGetStarted)
        etYourName = findViewById(R.id.etYourName)
        btnContact = findViewById(R.id.btnContact)
        ivImagePicker = findViewById(R.id.ivImagePicker)
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
                intent.putExtra(EXTRA_URI,selectedUri.toString())
                startActivity(intent)

            } else {
                val snack = Snackbar.make(it, "Type in your name!", Snackbar.LENGTH_SHORT)
                snack.show()
            }

        }
        btnContact.setOnClickListener {
            if (checkContactPermission()) {
                pickContact()
            } else {
                requestContactPermission()
            }
        }

        ivImagePicker.setOnClickListener {
            if (isReadExternalPermissionGranted()) {
                launchIntentForPhoto()
            } else {
                requestReadExternalPermission()
            }
        }
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

    private fun pickContact() {
        val intent = Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI)
        startActivityForResult(intent, CONTACT_PICK_CODE)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == CONTACT_PERMISSION_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                pickContact()
            } else {
                Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show()
            }
        } else if (requestCode == EXTERNAL_PERMISSION_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                launchIntentForPhoto()
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

    private fun launchIntentForPhoto() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, false)
        startActivityForResult(Intent.createChooser(intent, "Choose pics"), EXTERNAL_PICK_CODE)
    }


    @SuppressLint("Range")
        override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
            super.onActivityResult(requestCode, resultCode, data)
            if (requestCode == CONTACT_PICK_CODE) {
                if (resultCode == RESULT_OK) {
                    val cursor: Cursor

                    val uri = data!!.data
                    cursor = contentResolver.query(uri!!, null, null, null, null)!!
                    if (cursor.moveToFirst()) {
                        var contactName =
                            cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME))
                        etYourName.setText(contactName)
                    }
                    cursor.close()
                } else {
                    Toast.makeText(this, "Cancelled", Toast.LENGTH_SHORT).show()
                }
            }
        else if(requestCode == EXTERNAL_PICK_CODE){
            if(resultCode == RESULT_OK && data != null){
                selectedUri = data.data
                if(selectedUri!= null) {
                    ivImagePicker.setImageURI(selectedUri)
                }

            }
                else{
                Toast.makeText(this, "Cancelled", Toast.LENGTH_SHORT).show()
            }
            }
        }
}