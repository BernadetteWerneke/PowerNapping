package com.example.powernapping.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.fragment.app.Fragment
import com.example.powernapping.R
import com.example.powernapping.databinding.FragmentTimerCheckBinding
import kotlinx.android.synthetic.main.fragment_timer_check.*


class TimerCheckFragment : Fragment() {

    private var _binding : FragmentTimerCheckBinding? = null
    private val binding get() = _binding!!

    var startPoint = 0
    var endPoint = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentTimerCheckBinding.inflate(inflater, container, false)
        val view = binding.root

        //set preview emoji
        binding.checkEmojiIv.setImageResource(R.drawable.sleeping_face)

        //handling seekBar
        binding.checkSeekBarDiscrete.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{

            //react to the value being set in seekBar
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                //shows progress in number
                check_showrating_text.text = progress.toString()

            when (progress){
                //emoji 1: sleeping face
                1 -> check_emoji_iv.setImageResource(R.drawable.sleeping_face)
                //emoji 2: ohhhh
                2 -> check_emoji_iv.setImageResource(R.drawable.face_with_peeking_eye)
                //emoji 3: angry face
                3 -> check_emoji_iv.setImageResource(R.drawable.angry_face)
                //emoji 4: woozy face
                4 -> check_emoji_iv.setImageResource(R.drawable.woozy_face)
                //emoji 5: frowning face
                5 -> check_emoji_iv.setImageResource(R.drawable.frowning_face)
                //emoji 6: do not know face
                6 -> check_emoji_iv.setImageResource(R.drawable.face_with_raised_eyebrow)
                //emoji 7: smiling face
                7 -> check_emoji_iv.setImageResource(R.drawable.slightly_smiling_face)
                //emoji 8: smiling a lot face
                8 -> check_emoji_iv.setImageResource(R.drawable.grinning_face_with_big_eyes)
                //emoji 9: star eyes
                9 -> check_emoji_iv.setImageResource(R.drawable.star_struck)
                //emoji 10: cool face
                10 -> check_emoji_iv.setImageResource(R.drawable.smiling_face_with_sunglasses)}
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                if (seekBar != null) {
                    startPoint = seekBar.progress
                    //emoji
                }
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                if (seekBar != null) {
                    endPoint = seekBar.progress
                    //emoji
                }
            }
        })

        return view
    }

}