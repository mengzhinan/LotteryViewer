package com.lotteryviewer.home.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class WebPageViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is webPage Fragment"
    }
    val text: LiveData<String> = _text
}