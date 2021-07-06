package com.example.fragmentexample

import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.Fragment
import com.example.fragmentexample.`interface`.IFragment
import com.example.fragmentexample.data.CarItem
import com.example.fragmentexample.databinding.ActivityMainBinding
import com.example.fragmentexample.fragments.FragmentAdd
import com.example.fragmentexample.fragments.FragmentEdit
import com.example.fragmentexample.fragments.FragmentList
import com.example.fragmentexample.helpers.InitHelper.initDevicesList
import com.example.fragmentexample.viewmodel.CarViewModel
import java.io.FileNotFoundException

class MainActivity : AppCompatActivity(), IFragment {
    private val ADD_CAR_CODE = 1

    private lateinit var binding: ActivityMainBinding

    private lateinit var fFragmentList: FragmentList
    private lateinit var fFragmentEdit: FragmentEdit
    private lateinit var fFragmentAdd: FragmentAdd

    private var carsList = mutableListOf<CarItem>()
    private val phonesListViewModel: CarViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {

        binding = ActivityMainBinding.inflate(layoutInflater)

        fFragmentList = FragmentList(this)
        fFragmentEdit = FragmentEdit(this)
        fFragmentAdd = FragmentAdd(this)

        carsList = initDevicesList(this)


        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        phonesListViewModel.loadPhones(initDevicesList(this))

        binding.bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.mList -> setCurrentFragment(fFragmentList)
                R.id.mEdit -> setCurrentFragment(fFragmentEdit)
                R.id.mAdd -> setCurrentFragment(fFragmentAdd)
                R.id.mTrack -> Toast.makeText(
                    this,
                    "Track",
                    Toast.LENGTH_LONG
                ).show()
            }
            true
        }
        setCurrentFragment(fFragmentList)
    }

    private fun setCurrentFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragmentList, fragment)
            commit()
        }
    }

    override fun creteCar() {
        binding.bottomNavigationView.selectedItemId = R.id.mAdd
//        setCurrentFragment(fFragmentAdd)
    }

    override fun updateCar(index: Int) {
        phonesListViewModel.selectItem(index)
        binding.bottomNavigationView.selectedItemId = R.id.mEdit
        setCurrentFragment(fFragmentEdit)
    }

    override fun listCar() {
        binding.bottomNavigationView.selectedItemId = R.id.mEdit
        setCurrentFragment(fFragmentList)
    }

    override fun onCarCreated(car: CarItem?) {
        TODO("Not yet implemented")
    }

//    private fun uriToDrawable(imageUri: String): Drawable {
//        var image = AppCompatResources.getDrawable(this, R.drawable.phone)!!
//        try {
//            val inputStream = contentResolver.openInputStream(Uri.parse(imageUri))
//            image = Drawable.createFromStream(inputStream, imageUri)
//        } catch (e: FileNotFoundException) {
//            Log.e("MainActivity", "Unable to parse image from URI: $imageUri")
//        }
//        return image
//    }

//    override fun onClick(view: View) {
//        val action =
//            FragmentListDirections
//                .actionFragmentListToFragmentAdd2()
//        view.findNavController().navigate(action)
//    }


}