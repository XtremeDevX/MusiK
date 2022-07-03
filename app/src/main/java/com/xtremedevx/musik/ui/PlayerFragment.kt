package com.xtremedevx.musik.ui


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.xtremedevx.musik.R
import com.xtremedevx.musik.databinding.FragmentPlayerBinding
import com.xtremedevx.musik.utils.FlashlightHelper
import com.xtremedevx.musik.viewmodel.PlayerViewModel


class PlayerFragment : Fragment(R.layout.fragment_player) {
    private var _binding: FragmentPlayerBinding? = null
    private val binding get() = _binding!!

    private val sharedVewModel : PlayerViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPlayerBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedVewModel.title.observe(viewLifecycleOwner) { title ->
            binding.tvSongTitle.text = title
        }
        sharedVewModel.isPlaying.observe(viewLifecycleOwner){isPlaying->
            binding.btnPlayPause.setImageResource(if(isPlaying)
            {
                FlashlightHelper.blink()
                R.drawable.ic_pause
            }
            else{
                FlashlightHelper.turnOff()
                R.drawable.ic_play
            })
        }


        binding.btnPlayPause.setOnClickListener {
                sharedVewModel.playPauseSong()
        }

    }


}