package com.example.bicentral

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.bicentral.databinding.FragmentHomeBinding
import com.example.reto4.PicassoCircleTransformation
import com.squareup.picasso.Picasso

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.edTName.setText(activity?.intent?.getStringExtra("name"))
        binding.edTAddress.setText(activity?.intent?.getStringExtra("address"))

        //PicassoRoundedCornerTransformation(38.0f)

        val url = activity?.intent?.getStringExtra("imageurl")
        val imageView = binding.imgViewUser

        Picasso.get().load(url)
            .transform(PicassoCircleTransformation())
            .placeholder(R.drawable.ic_action_user)
            .into(imageView)

        return view
    }
}