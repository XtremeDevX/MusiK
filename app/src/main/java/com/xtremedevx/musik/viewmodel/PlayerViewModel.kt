package com.xtremedevx.musik.viewmodel

import android.content.Context
import androidx.core.net.toUri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.xtremedevx.musik.model.Song
import com.xtremedevx.musik.utils.SongPlayer


class PlayerViewModel : ViewModel() {

    private val _title = MutableLiveData("Unknown")
    val title: LiveData<String> = _title
    private val _duration = MutableLiveData(0.0)
    private val _path = MutableLiveData("Unknown")
    val path: LiveData<String> = MutableLiveData("Unknown")

    private val _isPlaying = MutableLiveData(true)
    val isPlaying :LiveData<Boolean> = _isPlaying


    fun playSong(song: Song,context: Context) {
        _title.value = song.title
        _duration.value = song.duration.toDouble()
        _path.value = song.path
        SongPlayer.playSongUri(song.path.toUri(),context)
    }


    fun playPauseSong(){
        if(isPlaying.value == true) {
            SongPlayer.pauseSound()
            _isPlaying.value = false
        }else{
            SongPlayer.resumeSound()
            _isPlaying.value = true
        }
    }



}