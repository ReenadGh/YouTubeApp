package com.example.kotlinyoutube

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import kotlinx.android.synthetic.main.video_item.view.*

class VideoAdapter(
    private val videoList: Array<Videos>,
    private val player: YouTubePlayer): RecyclerView.Adapter<VideoAdapter.VideoViewHolder>(){
    class VideoViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val button: Button = itemView.btVideo
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
                R.layout.video_item,
                parent,
                false
        )
        return VideoViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        val name = videoList[position].name
        val id = videoList[position].id
        holder.button.text = name
        holder.button.setOnClickListener {
            player.loadVideo(id, 0f)
        }
    }

    override fun getItemCount() = videoList.size
}