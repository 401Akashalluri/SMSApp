package com.example.smsapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("SMSApp", "onCreate: Activity started")

        val phoneNumberEditText = findViewById<EditText>(R.id.phoneNumberEditText)
        val messageEditText = findViewById<EditText>(R.id.messageEditText)
        val sendButton = findViewById<Button>(R.id.sendButton)

        sendButton.setOnClickListener {
            val phoneNumber = phoneNumberEditText.text.toString()
            val message = messageEditText.text.toString()
            sendSMS(phoneNumber, message)
        }
    }

    private fun sendSMS(phoneNumber: String, message: String) {
        if (phoneNumber.isEmpty() || message.isEmpty()) {
            Toast.makeText(this, "Please enter phone number and message", Toast.LENGTH_SHORT).show()
            Toast.makeText(this, "Trying to open SMS app for $phoneNumber", Toast.LENGTH_SHORT).show()
            return
        }

        val intent = Intent(Intent.ACTION_VIEW).apply {
            data = Uri.parse("sms:$phoneNumber")
            putExtra("sms_body", message)
        }

        try {
            startActivity(intent)
            Toast.makeText(this, "Opening SMS app…", Toast.LENGTH_SHORT).show()
            Log.d("SMSApp", "Intent launched to send SMS to $phoneNumber")
        } catch (e: Exception) {
            Toast.makeText(this, "Failed to open SMS app: ${e.message}", Toast.LENGTH_LONG).show()
            Log.e("SMSApp", "SMS Intent failed", e)
        }
    }
}
