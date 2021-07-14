package com.example.fragmentexample.fragments

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.fragmentexample.R
import com.example.fragmentexample.`interface`.IFragment
import com.example.fragmentexample.databinding.FragmentAddBinding

class FragmentAdd(val navigation: IFragment) : Fragment() {

    private var _binding: FragmentAddBinding? = null
    private val binding get() = _binding!!
    var model: String? = null
    private var imgUri: Uri? = null
    private val SELECT_IMAGE_CODE = 1
    val bundle = Bundle()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

//        val bundle = arguments
//        bundle?.let {
//            model = bundle.getString("EXTRA_model")
//        }

        _binding = FragmentAddBinding.inflate(inflater, container, false)
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.addBtn.setOnClickListener {


            val brand = binding.addBrand.text.toString()
            val model = binding.addModel.text.toString()
            val year = binding.addYear.text.toString()
            val imgUri = binding.addImage.toString()


            bundle.putString("brand", brand)
            bundle.putString("model", model)
            bundle.putString("year", year)
            bundle.putString("drawable", imgUri)

            findNavController().navigate(R.id.fragmentList, bundle)

        }
        binding.addImage.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, SELECT_IMAGE_CODE)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == SELECT_IMAGE_CODE) {
            imgUri = data?.data
            binding.addImage.setImageURI(imgUri)
        } else {
            binding.addImage.setImageURI(Uri.parse("@drawable/icon.jpg"))
        }


    }
}
