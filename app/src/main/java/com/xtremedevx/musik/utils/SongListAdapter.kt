package com.xtremedevx.musik.utils

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.xtremedevx.musik.databinding.SongItemBinding
import com.xtremedevx.musik.model.Song

class SongListAdapter(private val listener: OnItemClickListener) :
    ListAdapter<Song, SongListAdapter.SongHolder>(DiffCallback()) {
    inner class SongHolder(private val binding: SongItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val song = getItem(position)
                    listener.onItemClick(song)
                }
            }
        }

        fun bind(song: Song) {
            binding.apply {
                tvSongTitle.text = song.title
                tvSongDuration.text = song.duration.toString()
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongHolder {
        val binding = SongItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SongHolder(binding)
    }

    override fun onBindViewHolder(holder: SongHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }


    interface OnItemClickListener {
        fun onItemClick(song: Song)
    }

    class DiffCallback : DiffUtil.ItemCallback<Song>() {
        override fun areItemsTheSame(oldItem: Song, newItem: Song) =
            oldItem.path == newItem.path

        override fun areContentsTheSame(oldItem: Song, newItem: Song) =
            oldItem == newItem
    }
}


