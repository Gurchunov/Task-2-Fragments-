package com.example.fragmentexample.fragments

import android.content.ContentResolver
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fragmentexample.adapter.CarAdaptor
import com.example.fragmentexample.R
import com.example.fragmentexample.`interface`.IFragment
import com.example.fragmentexample.data.CarItem
import com.example.fragmentexample.databinding.FragmentListBinding
import com.example.fragmentexample.helpers.UriToDrawable
import com.example.fragmentexample.viewmodel.CarViewModel


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
        val model = arguments?.getString("model")
        val brand = arguments?.getString("brand")
        val year = arguments?.getString("year")
        val img = arguments?.getString("drawable")
        val drawable = img?.let {uri ->
            UriToDrawable.uriToDrawable(uri,requireContext())
        }
        val car = CarItem(brand, model, year, drawable)
itemsList.add(car)
        adapter.notifyItemInserted(itemsList.size-1)


    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}