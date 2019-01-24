package acker.brian.af_stats.ui.home

import acker.brian.af_stats.R
import acker.brian.af_stats.databinding.HomeFragmentBinding
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class HomeFragment : Fragment() {

    companion object {
        const val TAG = "HomeFragmentTag"
        fun newInstance() = HomeFragment()
    }

    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?): View {
        val homeFragmentBinding = DataBindingUtil.inflate<HomeFragmentBinding>(inflater, R.layout.home_fragment, container, false)
        viewModel = HomeViewModel()
        homeFragmentBinding.viewModel = viewModel

        return homeFragmentBinding.root
    }
}
