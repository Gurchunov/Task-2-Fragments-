package com.example.fragmentexample.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fragmentexample.adapter.PhoneListAdaptor
import com.example.fragmentexample.R
import com.example.fragmentexample.`interface`.IFragment
import com.example.fragmentexample.data.PhoneItem
import com.example.fragmentexample.databinding.FragmentListBinding
import com.example.fragmentexample.helpers.InitHelper
import com.example.fragmentexample.viewmodel.PhoneListViewModel


class FragmentList(val navigation: IFragment) : Fragment(R.layout.fragment_list) {
    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    private lateinit var phoneListAdaptor: PhoneListAdaptor
    private lateinit var fcontext: Context

    private val phoneListViewModel: PhoneListViewModel by activityViewModels()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.fcontext = context

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
        val phoneList = mutableListOf<PhoneItem>()

        phoneListAdaptor = PhoneListAdaptor(phoneListViewModel.phoneListLiveData.value!!, navigation)

        binding.rvPhoneList.adapter = phoneListAdaptor
        binding.rvPhoneList.layoutManager = LinearLayoutManager(fcontext)

        phoneListViewModel.phoneListLiveData.observe(
            viewLifecycleOwner, Observer<MutableList<PhoneItem>>{
                phoneListAdaptor.notifyDataSetChanged()
            }
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}