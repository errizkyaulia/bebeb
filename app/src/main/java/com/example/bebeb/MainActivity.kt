package com.example.bebeb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.widget.Button
import android.widget.VideoView
import android.net.Uri

class MainActivity : AppCompatActivity() {
    private lateinit var videoView: VideoView
    private var currentPosition: Int = 0
    private lateinit var nextLooks: Button
    private var isPaused: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        videoView = findViewById(R.id.videoView)

        val videoPath = "android.resource://" + packageName + "/" + R.raw.ayank
        videoView.setVideoURI(Uri.parse(videoPath))

        val nextScroll = findViewById<Button>(R.id.masuk)

        nextScroll.setOnClickListener {
            val intent = Intent(this, ScrollingActivity::class.java)
            startActivity(intent)
        }

        // Check if there's a saved position
        if (savedInstanceState != null) {
            currentPosition = savedInstanceState.getInt("currentPosition", 0)
            isPaused = savedInstanceState.getBoolean("isPaused", false)
        }

        if (isPaused) {
            // If the video was paused, set the position and pause it
            videoView.seekTo(currentPosition)
            videoView.pause()
        } else {
            // If not paused, start the video
            videoView.start()
        }

        // Set a loop for the video
        videoView.setOnPreparedListener { mp ->
            mp.isLooping = true
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        // Save the current position and pause state
        outState.putInt("currentPosition", currentPosition)
        outState.putBoolean("isPaused", isPaused)
        super.onSaveInstanceState(outState)
    }
    override fun onPause() {
        super.onPause()
        // Pause the video when the activity is paused
        if (videoView.isPlaying) {
            videoView.pause()
            isPaused = true
        }
    }
    override fun onResume() {
        super.onResume()
        // Resume the video when the activity is resumed
        if (isPaused) {
            videoView.seekTo(currentPosition)
            videoView.start()
            isPaused = false
        }
    }
}