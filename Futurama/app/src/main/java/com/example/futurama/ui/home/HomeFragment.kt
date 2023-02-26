package com.example.futurama.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.futurama.R
import com.example.futurama.data.dto.FuturamaApiResult
import com.example.futurama.data.dto.FuturamaCharactersItem
import com.example.futurama.databinding.FragmentHomeBinding
import com.example.futurama.errorHandling.ErrorHandlingFragment
import com.example.futurama.errorHandling.ErrorHandlingViewModel
import com.example.futurama.ui.adapter.AdapterFuturama
import com.example.futurama.utils.ConfigViewModel

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private lateinit var adapterHome: AdapterFuturama

    private val viewModel: HomeViewModel by activityViewModels() { ConfigViewModel.getFuturamaViewModelFactory()}

    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        setupUi()

        return root
    }

    private fun setupUi() {
        configAdapter()
    }

    private fun configAdapter() {
        adapterHome = AdapterFuturama()
        binding.listCharacters.adapter = adapterHome
        getData()
        goToFirstItemInRecyclerView()
    }

    private fun setListAdapter(list: List<FuturamaCharactersItem>) {
        adapterHome.submitList(list)
        adapterHome.notifyDataSetChanged()
        Log.i("TAM LIST",list.size.toString())
    }


    private fun goToFirstItemInRecyclerView() {
        val linearLayoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.listCharacters.layoutManager = linearLayoutManager
        binding.listCharacters.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (linearLayoutManager.findFirstVisibleItemPosition() == 0) {
                    binding.buttomArrow.visibility = View.GONE
                } else binding.buttomArrow.visibility = View.VISIBLE
            }
        })
        binding.buttomArrow.setOnClickListener {
            binding.listCharacters.scrollToPosition(0)
        }
    }


    private fun getData(){
        viewModel.getCharactersFromRetrofit()
        viewModel.characterItem.observe(viewLifecycleOwner) { listCharacters ->
            when (listCharacters) {
                is FuturamaApiResult.Loading<*> -> {
                    Log.d("INFO", "Loading")
                    binding.loadingCharacters.root.visibility = View.VISIBLE
                }
                is FuturamaApiResult.Success<*> -> {
                    Log.d("INFO", "Success: ${listCharacters.data}")
                    setListAdapter(listCharacters.data as List<FuturamaCharactersItem>)
                    binding.loadingCharacters.root.visibility = View.GONE

                }
                is FuturamaApiResult.Error<*> -> {
                    var mensagem: ErrorHandlingViewModel = ErrorHandlingViewModel()
                    mensagem.mensagemError = listCharacters.throwable.message.toString()
                    replaceFragment(ErrorHandlingFragment())
                    //Log.d("INFO", "Error.cause: ${listCharacters.throwable.cause}")
                    //Log.d("INFO", "Error: $listCharacters")
                    Log.d("INFO", "Error.message: ${listCharacters.throwable.message}")
                }
            }
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = activity?.supportFragmentManager
        val fragmentTransaction = fragmentManager?.beginTransaction()
        fragmentTransaction?.replace(R.id.nav_host_fragment_activity_bottom_navigation, fragment)
        fragmentTransaction?.addToBackStack(null)
        fragmentTransaction?.commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}