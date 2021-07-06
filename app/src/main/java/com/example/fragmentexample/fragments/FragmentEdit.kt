package com.example.fragmentexample.fragments


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import com.example.fragmentexample.R
import com.example.fragmentexample.`interface`.IFragment
import com.example.fragmentexample.data.CarItem
import com.example.fragmentexample.databinding.FragmentEditBinding
import com.example.fragmentexample.helpers.UriToDrawable
import com.example.fragmentexample.viewmodel.CarViewModel


class FragmentEdit(private val navigation: IFragment) : Fragment(R.layout.fragment_edit) {
    private var _binding: FragmentEditBinding? = null
    private val binding get() = _binding!!

    private var imgUri: Uri? = null
    private val selImgCode = 1


    private val carViewModel: CarViewModel by activityViewModels()
    private var setItemId: Int = 0
    private var selectedItem: CarItem = CarItem(null, null, null, null)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEditBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setItemId = carViewModel.selectedIndex.value!!
        selectedItem = carViewModel.getPhonesPosition(setItemId)

        with(binding) {
            editBrand.setText(carViewModel.getPhonesPosition(setItemId).brand)
            editModel.setText(carViewModel.getPhonesPosition(setItemId).model)
            editYear.setText(carViewModel.getPhonesPosition(setItemId).year)
            editImage.setImageDrawable(carViewModel.getPhonesPosition(setItemId).img)

            btnSave.setOnClickListener {
                selectedItem.brand = editBrand.text.toString()
                selectedItem.model = editModel.text.toString()
                selectedItem.year = editYear.text.toString()

                navigation.listCar()
            }
            editImage.setOnClickListener {
                val intent = Intent(Intent.ACTION_PICK)
                intent.type = "image/*"
                startActivityForResult(intent, setItemId)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == AppCompatActivity.RESULT_OK && requestCode == selImgCode) {
            imgUri = data?.data
            binding.editImage.setImageURI(imgUri)
            selectedItem.img = UriToDrawable.uriToDrawable(imgUri.toString(), requireContext())
        }
    }
}