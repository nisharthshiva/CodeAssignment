package com.demo.codeassignment

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.demo.codeassignment.base.BaseViewModel
import com.demo.codeassignment.common.Constants.NETWORK_PAGE_SIZE
import com.demo.codeassignment.data.PassengerItemDataSource
import com.demo.codeassignment.data.models.UserDetailsModel
import com.demo.codeassignment.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: Repository, @ApplicationContext private val mContext: Context
) : BaseViewModel() {

    val errorMessage = MutableLiveData<String>()

    fun getphotoList(): LiveData<PagingData<UserDetailsModel>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false,
                initialLoadSize = 20
            ),
            pagingSourceFactory = {
                PassengerItemDataSource(repository)
            }
            , initialKey = 1
        ).liveData.cachedIn(viewModelScope)
    }
}

