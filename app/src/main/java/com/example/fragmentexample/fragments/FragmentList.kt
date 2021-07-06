package com.example.fragmentexample.fragments

import android.content.ContentResolver
import android.content.Context
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fragmentexample.adapter.CarAdaptor
import com.example.fragmentexample.R
import com.example.fragmentexample.`interface`.IFragment
import com.example.fragmentexample.data.CarItem
import com.example.fragmentexample.databinding.FragmentListBinding
import com.example.fragmentexample.viewmodel.CarViewModel
import java.io.FileNotFoundException


class FragmentList(val navigation: IFragment) : Fragment(R.layout.fragment_list) {
    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    private lateinit var carAdaptor: CarAdaptor
    private lateinit var fcontext: Context

    private val carViewModel: CarViewModel by activityViewModels()

    var itemsList = mutableListOf<CarItem>()
    lateinit var adapter: CarAdaptor
    private lateinit var fContext: Context
    private lateinit var fContentResolver: ContentResolver

    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.fContext = context
        this.fContentResolver = fContext.contentResolver

    }
    private fun uriToDrawable(imageUri: String): Drawable {
        var image = AppCompatResources.getDrawable(fcontext, R.drawable.phone)!!
        try {
            val inputStream = fContentResolver.openInputStream(Uri.parse(imageUri))
            image = Drawable.createFromStream(inputStream, imageUri)
        } catch (e: FileNotFoundException) {
            Log.e("MainActivity", "Unable to parse image from URI: $imageUri")
        }
        return image
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root


    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val phoneList = mutableListOf<CarItem>()

        carAdaptor = CarAdaptor(carViewModel.phoneListLiveData.value!!, navigation)

        binding.rvPhoneList.adapter = carAdaptor
        binding.rvPhoneList.layoutManager = LinearLayoutManager(fcontext)

        carViewModel.phoneListLiveData.observe(
            viewLifecycleOwner, Observer<MutableList<CarItem>>{
                carAdaptor.notifyDataSetChanged()
            }
        )


    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}