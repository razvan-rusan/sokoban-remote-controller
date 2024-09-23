

package com.example.sokobanremotecontroller

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.sokobanremotecontroller.ui.theme.SokobanRemoteControllerTheme

class MainActivity : ComponentActivity() {
    val networkingViewModel: NetworkingViewModel by viewModels<NetworkingViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContent {
            SokobanRemoteControllerTheme {
                Column(verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth()) {
                    ControlsGrid(modifier = Modifier
                        .padding(40.dp)
                        .size(250.dp),
                        networkingViewModel = networkingViewModel
                    )
                }

            }
        }
    }
}

//@Preview
@Composable
fun ControlsGrid(modifier: Modifier = Modifier, networkingViewModel: NetworkingViewModel) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Spacer(Modifier.weight(1f))
            Surface(onClick = {
                Log.v("RAZVO", "up")
                networkingViewModel.sendDataToTCPServer("w")
            }) {
                Icon(
                    imageVector = Icons.Filled.KeyboardArrowUp,
                    contentDescription = null
                )
            }
            Spacer(Modifier.weight(1f))
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Surface(onClick = {
                Log.v("RAZVO", "left")
                networkingViewModel.sendDataToTCPServer("a")
            }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                    contentDescription = null
                )
            }
            Spacer(Modifier.weight(1f))
            Surface(onClick = {
                Log.v("RAZVO", "right")
                networkingViewModel.sendDataToTCPServer("d")
            }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                    contentDescription = null
                )
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Spacer(Modifier.weight(1f))
            Surface(onClick = {
                Log.v("RAZVO", "down")
                networkingViewModel.sendDataToTCPServer("s")
            }) {
                Icon(
                    imageVector = Icons.Filled.KeyboardArrowDown,
                    contentDescription = null
                )
            }
            Spacer(Modifier.weight(1f))
        }
    }
}