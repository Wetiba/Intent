package com.example.mysaturdayintentsapp

import android.Manifest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.content.Intent

import android.net.Uri
import android.provider.MediaStore
import androidx.core.app.ActivityCompat

import android.content.pm.PackageManager

import androidx.core.content.ContextCompat










class MainActivity : AppCompatActivity() {
    var buttonSms:Button ?= null
    var buttonEmail:Button ?= null
    var buttonShare:Button ?= null
    var buttonCamera:Button ?= null
    var buttonStk:Button ?= null
    var buttonCall:Button ?= null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        buttonSms = findViewById(R.id.mBtnSms)
        buttonEmail = findViewById(R.id.mBtnEmail)
        buttonShare = findViewById(R.id.mBtnShare)
        buttonCamera = findViewById(R.id.mBtnCamera)
        buttonStk = findViewById(R.id.mBtnStk)
        buttonCall = findViewById(R.id.mBtnCall)

        buttonSms!!.setOnClickListener {
            val uri = Uri.parse("smsto:0714000000")
            val intent = Intent(Intent.ACTION_SENDTO, uri)
            intent.putExtra("sms_body", "Hello sir,..")
            startActivity(intent)
        }
        buttonEmail!!.setOnClickListener {
            val emailIntent =
                Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", "glory@gmail.com", null))
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "JOB APPLICATION")
            emailIntent.putExtra(Intent.EXTRA_TEXT, "Dear Madam,...")
            startActivity(Intent.createChooser(emailIntent, "Send email..."))
        }
        buttonShare!!.setOnClickListener {
            val shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            shareIntent.type = "text/plain"
            shareIntent.putExtra(Intent.EXTRA_TEXT, "Hey, download this app!")
            startActivity(shareIntent)
        }
        buttonCamera!!.setOnClickListener {
            val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(takePictureIntent, 1)

        }
        buttonStk!!.setOnClickListener {
            val simToolKitLaunchIntent =
                applicationContext.packageManager.getLaunchIntentForPackage("com.android.stk")
            simToolKitLaunchIntent?.let { startActivity(it) }

        }
        buttonCall!!.setOnClickListener {
            val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "0714000000"))
            if (ContextCompat.checkSelfPermission(
                    this@MainActivity,
                    Manifest.permission.CALL_PHONE
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    this@MainActivity,
                    arrayOf(Manifest.permission.CALL_PHONE),
                    1
                )
            } else {
                startActivity(intent)
            }

        }
    }
}








