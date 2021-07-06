package com.example.fragmentexample.fragments

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.fragmentexample.R
import com.example.fragmentexample.`interface`.IFragment
import com.example.fragmentexample.databinding.FragmentAddBinding

class FragmentAdd(val navigation: IFragment) : Fragment() {

    private var _binding: FragmentAddBinding? = null
    private val binding get() = _binding!!
    var model: String? = null
    private var imgUri: Uri? = null
    private val SELECT_IMAGE_CODE = 1


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) : View{

        val bundle = arguments
        bundle?.let {
            model = bundle.getString("EXTRA_model")
        }

        _binding = FragmentAddBinding.inflate(inflater, container, false)
        return binding.root

    }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)

            binding.addBtn.setOnClickListener {
                Intent().apply {
                    val firstFragment = FragmentList(navigation)
                    val bundle = Bundle()
                    putExtra("EXTRA_MODEL", binding.addModel.text.toString())
                    putExtra("EXTRA_SCREEN", binding.addBrand.text.toString())
                    putExtra("EXTRA_HARDWARE", binding.addYear.text.toString())
                    putExtra("EXTRA_URI", imgUri.toString())
                    firstFragment.arguments = bundle
                    val transaction = fragmentManager?.beginTransaction()
                    transaction?.let {
                        transaction.replace(R.id.nav_host_fragment, firstFragment)
                        transaction.commit()
                    }
                }
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




