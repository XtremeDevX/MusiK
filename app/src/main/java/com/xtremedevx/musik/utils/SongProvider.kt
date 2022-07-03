package com.xtremedevx.musik.utils

import android.content.Context
import android.database.Cursor
import android.provider.MediaStore
import android.util.Log
import androidx.lifecycle.LiveData
import com.xtremedevx.musik.model.Song

object SongProvider {
    private val songList =
        mutableListOf<Song>(
            Song("Song 1", 3, "UnKnown"),
            Song("Song 2", 3, "UnKnown"),
            Song("Song 3", 3, "UnKnown")
        )
    private val _allSongs = MutableListLiveData(songList)


    val allSongs: LiveData<List<Song>> = _allSongs

    private val projection = arrayOf(
        MediaStore.Audio.AudioColumns.TITLE,
        MediaStore.Audio.AudioColumns.DURATION,
        MediaStore.Audio.AudioColumns.DATA
    )


    fun getAllSongs(context: Context): LiveData<List<Song>> {
        Log.i("TEST", "ADDED Inside Get All Song")

        val cursor = getCursor(context)
        val songs = ArrayList<Song>()
        if (cursor != null && cursor.moveToFirst()) {
            do {
                val song = getSongFromCursor(cursor)
                if (song.duration >= 30000) {
                    Log.i("TEST", "ADDED SONG")
                    songs.add(song)
                    _allSongs.add(getSongFromCursor(cursor))
                }
            } while (cursor.moveToNext())
        }


        return allSongs
    }

    val allDeviceSongs = mutableListOf<Song>(
        Song("Song 1", 3, "UnKnown"),
        Song("Song 2", 3, "UnKnown"),
        Song("Song 3", 3, "UnKnown")
    )

    fun getAllSongList(context: Context): List<Song> {
        Log.i("TEST", "ADDED Inside Get All Song")

        val cursor = getCursor(context)
        if (cursor != null && cursor.moveToFirst()) {
            do {
                val song = getSongFromCursor(cursor)
                if (song.duration >= 30000) {
                    Log.i("TEST", "ADDED SONG")
                    allDeviceSongs.add(song)
                    _allSongs.add(getSongFromCursor(cursor))
                }
            } while (cursor.moveToNext())
        }


        return allDeviceSongs
    }


    private fun getSongFromCursor(cursor: Cursor): Song {
        return Song(cursor.getString(0), cursor.getInt(1), cursor.getString(2))
    }

    private fun getCursor(context: Context): Cursor? {
        return try {
            context.contentResolver.query(
                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                projection, null, null, null
            )
        } catch (e: SecurityException) {
            null
        }
    }
}