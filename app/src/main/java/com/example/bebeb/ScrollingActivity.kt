package com.example.bebeb

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import com.example.bebeb.databinding.ActivityScrollingBinding
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout

class ScrollingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityScrollingBinding
    private val PICK_IMAGE_REQUEST = 1
    private lateinit var selectedImageUri: Uri // Store the selected image URI

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScrollingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(findViewById(R.id.toolbar))
        binding.toolbarLayout.title = title

        val AddPicture: Button = findViewById(R.id.AddPicture)
        val imageContainer: LinearLayout = findViewById(R.id.imageContainer)

        AddPicture.setOnClickListener {
            // Start intent to pick an image from the media storage
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, PICK_IMAGE_REQUEST)
        }

        binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "Ada Pesan Untuk Ku Ayank??", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null) {
            // Get the selected image URI
            selectedImageUri = data.data!!

            // Create a new ImageView
            val newImageView = ImageView(this)

            // Set the image resource for the new ImageView
            newImageView.setImageURI(selectedImageUri)

            // Set layout parameters for the new ImageView (adjust as needed)
            val layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )

            // Add the new ImageView to the image container
            val imageContainer: LinearLayout = findViewById(R.id.imageContainer)
            imageContainer.addView(newImageView, layoutParams)
        }
    }
}