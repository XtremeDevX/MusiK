package com.xtremedevx.musik.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.xtremedevx.musik.databinding.FragmentAllSongBinding
import com.xtremedevx.musik.model.Song
import com.xtremedevx.musik.utils.FlashlightHelper
import com.xtremedevx.musik.utils.SongListAdapter
import com.xtremedevx.musik.utils.SongProvider
import com.xtremedevx.musik.viewmodel.PlayerViewModel


class AllSongFragment : Fragment(), SongListAdapter.OnItemClickListener {
    private var _binding: FragmentAllSongBinding? = null
    private val binding get() = _binding!!

    private val sharedVewModel: PlayerViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentAllSongBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        sharedVewModel.addAllDeviceSong(requireContext())

        val songList = SongProvider.getAllSongList(requireActivity())
        val songAdapter = SongListAdapter(this)
        FlashlightHelper.initialize(requireContext())
        songAdapter.submitList(songList)

        binding.apply {
            recyclerview.apply {
                adapter = songAdapter
                layoutManager = LinearLayoutManager(requireContext())
                setHasFixedSize(true)
            }
        }

    }


    override fun onItemClick(song: Song) {
        sharedVewModel.playSong(song,requireActivity())
        val action = AllSongFragmentDirections.actionAllSongFragmentToPlayerFragment()
        findNavController().navigate(action)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}