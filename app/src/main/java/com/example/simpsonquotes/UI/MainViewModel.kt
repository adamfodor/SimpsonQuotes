package com.example.simpsonquotes.UI

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.simpsonquotes.data.RetrofitHelper
import com.example.simpsonquotes.data.SimpsonApi
import com.example.simpsonquotes.data.SimpsonsResponse
import com.example.simpsonquotes.network.ConnectivityObserver
import com.example.simpsonquotes.network.NetworkConnectivityObserver
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import retrofit2.Response

class MainViewModel(context: Context): ViewModel() {

    private lateinit var connectivityObserver: ConnectivityObserver
    private var connected : ConnectivityObserver.Status = ConnectivityObserver.Status.Unavailable

    private val api = RetrofitHelper.getInstance().create(SimpsonApi::class.java)
    var quote = MutableLiveData<String>()
    var character = MutableLiveData<String>()
    var imgUrl = MutableLiveData<String>()


    init {
        startmonitoring(context)

    }



    @OptIn(ExperimentalCoroutinesApi::class)
    fun nextQuote(){

        if (connected == ConnectivityObserver.Status.Available) {


            val current = viewModelScope.async { api.getQuote() }
            current.invokeOnCompletion {
                quote.value = current.getCompleted().body()!![0].quote
                character.value = current.getCompleted().body()!![0].character
                imgUrl.value = current.getCompleted().body()!![0].image

            }

        }else{
            quote.value = "No network connection"
            character.value = ""
            imgUrl.value ="asdasd"

        }
    }



    private fun startmonitoring(context:Context){
        connectivityObserver = NetworkConnectivityObserver(context)
        connectivityObserver.observ().onEach { connected = it }.launchIn(viewModelScope)
    }
}