package com.example.androidminiprojet

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toFile

class PickImageForProfileActivity : AppCompatActivity() {


    val REQUEST_CODE = 200
    private var imageUri: Uri? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pick_image_for_profile)

        val IMAGE_REQUEST_CODE = 1000
        val imageProfile : ImageView = findViewById(R.id.idImageProfile)
        val btnUploadImageProfile : Button = findViewById(R.id.btnUploadImageProfile)
        val ignoreUploadImageProfile : TextView = findViewById(R.id.idIgnoreUplooadImageProfile)
        val nextUploadImageProfile : TextView = findViewById(R.id.idNextUplooadImageProfile)
        nextUploadImageProfile.visibility = View.INVISIBLE

        btnUploadImageProfile.setOnClickListener({

            openGalleryForImages()
        })

        ignoreUploadImageProfile.setOnClickListener({
            val intentToMAin = Intent (this@PickImageForProfileActivity, MainActivity::class.java )
            startActivity(intentToMAin)
        })

        nextUploadImageProfile.setOnClickListener({
            val intentToMAin = Intent (this@PickImageForProfileActivity, MainActivity::class.java )
            startActivity(intentToMAin)
        })


    }

    private fun openGalleryForImages() {
        var intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
        intent.addCategory(Intent.CATEGORY_OPENABLE)
        intent.type = "image/*"
        startActivityForResult(intent, REQUEST_CODE);
    }


    private fun imageChooser() {
        val  i : Intent = Intent()
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(i, "Select Picture"), 200);
    }

    override  fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val imageProfile : ImageView = findViewById(R.id.idImageProfile)

        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE){

            // if multiple images are selected
            if (data?.getClipData() != null) {
//                var count = data.clipData?.itemCount
//
//                for (i in 0..count!! - 1) {
//                    var imageUri: Uri = data.clipData?.getItemAt(i)!!.uri
//                    //     iv_image.setImageURI(imageUri) Here you can assign your Image URI to the ImageViews
//                }

            } else if (data?.getData() != null) {
                // if single image is selected

                var imageUri: Uri = data.data!!
                imageProfile.setImageURI(imageUri)



//                Log.i("image",imageUri.toString())
//                Log.i("image",imageUri.toFile().path)
//                Log.i("image",imageUri.toFile().name)
//                Log.i("image",imageUri.toFile().toString())

                val nextUploadImageProfile : TextView = findViewById(R.id.idNextUplooadImageProfile)
                nextUploadImageProfile.visibility = View.VISIBLE
            }
        }
    }
}