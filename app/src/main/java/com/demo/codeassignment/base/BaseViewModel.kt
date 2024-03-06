package com.demo.codeassignment.base

import androidx.lifecycle.ViewModel
import com.demo.codeassignment.common.NetInfo

import javax.inject.Inject

abstract class BaseViewModel : ViewModel() {
    @Inject
    protected lateinit var netInfo: NetInfo
}