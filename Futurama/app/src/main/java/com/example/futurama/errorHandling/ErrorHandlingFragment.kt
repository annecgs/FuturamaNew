package com.example.futurama.errorHandling

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.futurama.R

class ErrorHandlingFragment : Fragment() {

    companion object {
        fun newInstance() = ErrorHandlingFragment()
    }

    private lateinit var viewModel: ErrorHandlingViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_error_handling, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ErrorHandlingViewModel::class.java)
        // TODO: Use the ViewModel
    }

}