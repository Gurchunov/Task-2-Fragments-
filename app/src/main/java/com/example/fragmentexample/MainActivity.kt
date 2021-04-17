package com.example.fragmentexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.ListFragment
import com.example.fragmentexample.`interface`.IFragment
import com.example.fragmentexample.data.PhoneItem
import com.example.fragmentexample.databinding.ActivityMainBinding
import com.example.fragmentexample.fragments.FragmentEdit
import com.example.fragmentexample.fragments.FragmentList
import com.example.fragmentexample.helpers.InitHelper
import com.example.fragmentexample.viewmodel.PhoneListViewModel

class MainActivity : AppCompatActivity(), IFragment {

    private lateinit var binding: ActivityMainBinding
    private lateinit var fFragmentList: FragmentList
    private lateinit var fFragmentEdit: FragmentEdit

    private  val phonesListViewModel: PhoneListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {

        binding = ActivityMainBinding.inflate(layoutInflater)
        fFragmentList = FragmentList(this)
        fFragmentEdit = FragmentEdit(this)



        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        phonesListViewModel.loadPhones(InitHelper.initDevicesList(this))

        binding.bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.mList -> setCurrentFragment(fFragmentList)
                R.id.mEdit -> setCurrentFragment(fFragmentEdit)
                R.id.mAdd -> Toast.makeText(
                    this,
                    "Hello",
                    Toast.LENGTH_LONG
                ).show()
            }
            true
        }
        setCurrentFragment(fFragmentList)
    }

    private fun setCurrentFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fFragment, fragment)
            commit()
        }
    }

    override fun cretePhone() {
        binding.bottomNavigationView.selectedItemId = R.id.mAdd
//        setCurrentFragment()
    }

    override fun updatePhone(index: Int) {
        phonesListViewModel.selectItem(index)
        binding.bottomNavigationView.selectedItemId = R.id.mEdit
        setCurrentFragment(fFragmentEdit)
    }

    override fun listPhones() {
        binding.bottomNavigationView.selectedItemId = R.id.mEdit
        setCurrentFragment(fFragmentList)
    }

    override fun onPhoneCreated(phone: PhoneItem?) {
        TODO("Not yet implemented")
    }

}