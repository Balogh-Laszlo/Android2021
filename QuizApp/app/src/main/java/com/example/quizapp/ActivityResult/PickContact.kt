package com.example.quizapp.ActivityResult

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.ContactsContract
import android.util.Log
import androidx.activity.result.contract.ActivityResultContract
import androidx.appcompat.app.AppCompatActivity

class PickContact : ActivityResultContract<Int, Uri?>() {
    companion object{
        const val TAG = "PICK CONTACT"
    }
    override fun createIntent(context: Context, ringtoneType: Int) =
        Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI)

    override fun parseResult(resultCode: Int, result: Intent?) : Uri? {
        if (resultCode != AppCompatActivity.RESULT_OK) {
            Log.i(TAG,"NULL")
            return null
        }
        return result?.data
    }
}