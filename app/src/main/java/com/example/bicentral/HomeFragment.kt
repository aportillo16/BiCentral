package com.example.bicentral

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.bicentral.databinding.FragmentHomeBinding
import okio.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL


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
        //binding.imgViewUser.load(activity?.intent?.getStringExtra("imageurl"))

        /*val url = URL(activity?.intent?.getStringExtra("imageurl"))
        val image = BitmapFactory.decodeStream(url.openConnection().getInputStream())

        binding.imgViewUser.setImageBitmap(image)*/

        val thread = Thread {
            try {
                binding.imgViewUser.setImageBitmap(getBitmapFromURL(activity?.intent?.getStringExtra("imageurl")))
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        thread.start()
        return view
    }

    /*fun convertBitmapToByteArrayUncompressed(bitmap: Bitmap): ByteArray? {
        val byteBuffer: ByteBuffer = ByteBuffer.allocate(bitmap.byteCount)
        bitmap.copyPixelsToBuffer(byteBuffer)
        byteBuffer.rewind()
        return byteBuffer.array()
    }*/

    private fun getBitmapFromURL(src: String?): Bitmap? {
        return try {
            val url = URL(src)
            val connection: HttpURLConnection = url
                .openConnection() as HttpURLConnection
            connection.doInput = true
            connection.connect()
            val input: InputStream = connection.inputStream
            BitmapFactory.decodeStream(input)
        } catch (e: Exception) {
            Log.d("vk21", e.toString())
            null
        }
    }

    /*
    val url = URL(activity?.intent?.getStringExtra("imageurl"))
                val image = BitmapFactory.decodeStream(url.openConnection().getInputStream())

                binding.imgViewUser.setImageBitmap(image)*/
}