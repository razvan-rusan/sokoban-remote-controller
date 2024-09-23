package com.example.sokobanremotecontroller

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.ktor.network.selector.SelectorManager
import io.ktor.network.sockets.aSocket
import io.ktor.network.sockets.openWriteChannel
import io.ktor.utils.io.writeStringUtf8
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch

class NetworkingViewModel : ViewModel() {
    private val messageChannel = Channel<String>()

    fun sendDataToTCPServer(message: String) {
        viewModelScope.launch {
            messageChannel.send(message)
        }
    }

    init {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val selectorManager = SelectorManager(Dispatchers.IO)
                val socket = aSocket(selectorManager).tcp().connect("192.168.0.31", 25565)
                val sendChannel = socket.openWriteChannel(autoFlush = true)

                for (message in messageChannel) {
                    sendChannel.writeStringUtf8("$message\n")
                }
            } catch (e: Exception) {
                Log.e("RAZVO", e.toString())
            }
        }
    }

}