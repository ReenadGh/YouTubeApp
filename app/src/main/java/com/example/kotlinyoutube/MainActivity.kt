package com.example.kotlinyoutube

import android.content.Context
import android.content.res.Configuration
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

class MainActivity : AppCompatActivity() {
    private val videos: Array<Videos> = arrayOf(
       Videos("Numbers Game", "CiFyTc1SwPw"),
        Videos("Calculator", "ZbZFMDk69IA"),
        Videos("Guess the Phrase", "DU1qMhyKv8g"),
        Videos("Username and Password", "G_XYXuC8b9M"),
        Videos("GUI Username and Password", "sqJWyPhZkDw"),
    )
    private lateinit var youTubePlayerView: YouTubePlayerView
    private lateinit var player: YouTubePlayer
    private var currentVideo = 0
    private var timeStamp = 0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        checkConnection()

        youTubePlayerView = findViewById(R.id.ytPlayer)
        youTubePlayerView.addYouTubePlayerListener(object: AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                super.onReady(youTubePlayer)
                player = youTubePlayer
                player.loadVideo(videos[currentVideo].name, timeStamp)
                initializeRV()
            }
        })

    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        if (newConfig.orientation === Configuration.ORIENTATION_LANDSCAPE) {
            youTubePlayerView.enterFullScreen()
        } else if (newConfig.orientation === Configuration.ORIENTATION_PORTRAIT) {
            youTubePlayerView.exitFullScreen()
        }
    }


    private fun initializeRV(){
        val recyclerView: RecyclerView = findViewById(R.id.rvVideos)
        recyclerView.adapter = VideoAdapter(videos, player)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
    }


    fun checkConnection() {
        val cm = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo

        if (activeNetwork?.isConnectedOrConnecting == true) {
            Log.d("Internt", " connected ")

        } else {
            Log.d("Internt", "no connection ")
            Toast.makeText(this, "Failed Connection !", Toast.LENGTH_SHORT).show()


        }

    }


}