package com.example.quizapp

import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.quizapp.Utils.EXTRA_NAME
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private lateinit var btnGetStarted: Button
    private lateinit var etYourName: EditText
    private lateinit var btnContact: Button
    companion object {
        const val TAG= "MAIN ACTIVITY"
        val CONTACT_PERMISSION_CODE = 440
        val CONTACT_PICK_CODE = 441
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnGetStarted = findViewById(R.id.btnGetStarted)
        etYourName = findViewById(R.id.etYourName)
        btnContact = findViewById(R.id.btnContact)
        btnGetStarted.setOnClickListener {
            val text = etYourName.text.toString()
            Log.i(TAG,"Button clicked, edit text's content: $text")
//            Toast.makeText(this,"Button clicked, edit text's content: ${etYourName.text.toString()}",Toast.LENGTH_SHORT).show()
            if(text.length>0) {
                val snack = Snackbar.make(
                    it,
                    "Button clicked, edit text's content: ${etYourName.text.toString()}",
                    Snackbar.LENGTH_SHORT
                )
                snack.show()
                val intent = Intent(this,DisplayActivity::class.java)
                intent.putExtra(EXTRA_NAME,text)
                startActivity(intent)

            }
            else{
                val snack = Snackbar.make(it,"Type in your name!",Snackbar.LENGTH_SHORT)
                snack.show()
            }

        }
        btnContact.setOnClickListener {

        }
    }
    private fun checkContactPermission():Boolean{
        return ContextCompat.checkSelfPermission(
            this,
            android.Manifest.permission.READ_CONTACTS
        ) == PackageManager.PERMISSION_GRANTED
    }
    private fun requestContactPermission(){
        val permission = arrayOf(android.Manifest.permission.READ_CONTACTS)
        ActivityCompat.requestPermissions(this,permission, CONTACT_PERMISSION_CODE)

    }
    private fun pickContact(){
        val intent = Intent(Intent.ACTION_PICK,ContactsContract.Contacts.CONTENT_URI)
        startActivityForResult(intent, CONTACT_PICK_CODE)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if(requestCode == CONTACT_PERMISSION_CODE){
            if(grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
               pickContact()
            }
            else{
                Toast.makeText(this,"Permission denied",Toast.LENGTH_SHORT).show()
            }
        }
    }

}