package com.made.gdocs.platform

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.made.gdocs.R
import com.made.gdocs.databinding.FragmentPlatformBinding

class PlatformFragment : Fragment(), View.OnClickListener {

    private var _binding : FragmentPlatformBinding? = null
    private val binding get() = _binding!!

    private var keyword = "keyword"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPlatformBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            pcBtn.setOnClickListener(this@PlatformFragment)
            psBtn.setOnClickListener(this@PlatformFragment)
            xboxBtn.setOnClickListener(this@PlatformFragment)
            androidBtn.setOnClickListener(this@PlatformFragment)
            iosBtn.setOnClickListener(this@PlatformFragment)
        }

    }

    override fun onClick(v: View?) {
        keyword = when(v?.id) {
            R.id.pc_btn -> "PC"
            R.id.ps_btn -> "PlayStation"
            R.id.xbox_btn -> "Xbox"
            R.id.android_btn -> "Android"
            else -> "IOS"
        }

        if (v!!.isPressed) {
            val i = Intent(context, DetailPlatformActivity::class.java)
            i.putExtra(DetailPlatformActivity.PLATFORM_NAME, keyword)
            startActivity(i)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}