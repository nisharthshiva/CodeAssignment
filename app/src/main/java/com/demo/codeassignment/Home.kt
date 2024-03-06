package com.demo.codeassignment

import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.demo.codeassignment.base.BaseActivity
import com.demo.codeassignment.databinding.ActivityHomeBinding
import com.demo.codeassignment.adapter.MainListAdapter
import com.demo.codeassignment.common.setVisibility
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class Home :BaseActivity<ActivityHomeBinding>(),View.OnClickListener {
    override fun getInjectedViewBinding() = ActivityHomeBinding.inflate(layoutInflater)
    private val homeViewModel: HomeViewModel by viewModels()
    private var adapter = MainListAdapter()

    override fun initUI() {

        setUpViewModelObserver()

        homeViewModel.getphotoList()
        binding.rvRecycler.layoutManager = LinearLayoutManager(this)
        binding.rvRecycler.adapter = adapter

        adapter.addLoadStateListener { loadState ->
            // show empty list
            if (loadState.refresh is LoadState.Loading || loadState.append is LoadState.Loading)
                binding.progressDialog.setVisibility(true)
            else {
                binding.progressDialog.setVisibility(false)
                // If we have an error, show a toast
                val errorState = when {
                    loadState.append is LoadState.Error -> loadState.append as LoadState.Error
                    loadState.prepend is LoadState.Error ->  loadState.prepend as LoadState.Error
                    loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
                    else -> null
                }
                errorState?.let {
                    Toast.makeText(this, it.error.toString(), Toast.LENGTH_LONG).show()
                }
            }
        }

        binding.icHeader.imgRefresh.setOnClickListener(this)

        binding.swipeRefresh.setOnRefreshListener {
            callapi()
            binding.swipeRefresh.isRefreshing = false
            adapter.notifyDataSetChanged()
        }

        adapter.mEventListener = object  : MainListAdapter.EventListnear{
            override fun onItemClicked(title: String, url: String) {
                showGenericErrorAlert(this@Home,title,url)
            }
        }
        callapi()

    }

    private fun callapi(){
        lifecycleScope.launch {
            homeViewModel.getphotoList().observe(this@Home) {
                it?.let {
                    adapter.submitData(lifecycle, it)
                }
            }
        }
    }


    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.img_refresh ->{
                callapi()
                binding.rvRecycler.scrollToPosition(0)
            }
        }
    }



}