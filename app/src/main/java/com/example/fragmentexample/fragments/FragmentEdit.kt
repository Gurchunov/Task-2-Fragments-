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
import com.example.fragmentexample.data.PhoneItem
import com.example.fragmentexample.databinding.FragmentEditBinding
import com.example.fragmentexample.helpers.UriToDrawable
import com.example.fragmentexample.viewmodel.PhoneListViewModel


class FragmentEdit(private val navigation: IFragment) : Fragment(R.layout.fragment_edit) {
    private var _binding: FragmentEditBinding? = null
    private val binding get() = _binding!!

    private var imgUri: Uri? = null
    private val selImgCode = 1


    private val phoneListViewModel: PhoneListViewModel by activityViewModels()
    private var setItemId: Int = 0
    private var selectedItem: PhoneItem = PhoneItem(null, null, null, null)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEditBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setItemId = phoneListViewModel.selectedIndex.value!!
        selectedItem = phoneListViewModel.getPhonesPosition(setItemId)

        with(binding) {
            editBrand.setText(phoneListViewModel.getPhonesPosition(setItemId).brand)
            editModel.setText(phoneListViewModel.getPhonesPosition(setItemId).model)
            editYear.setText(phoneListViewModel.getPhonesPosition(setItemId).year)
            editImage.setImageDrawable(phoneListViewModel.getPhonesPosition(setItemId).img)

            binding.btnSave.setOnClickListener {
                selectedItem.brand = editBrand.text.toString()
                selectedItem.model = editModel.text.toString()
                selectedItem.year = editYear.text.toString()

//                phoneListViewModel.updatePhonePosition(se)
                navigation.listPhones()
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