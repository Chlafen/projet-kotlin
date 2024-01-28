package com.example.nyt

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.nyt.ui.theme.NYTTheme
import kotlinx.coroutines.ExperimentalCoroutinesApi
import java.util.Locale

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class,
        ExperimentalCoroutinesApi::class
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val connection by connectivityState()
            NYTTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Scaffold (
                       bottomBar = {
                           if(connection === ConnectionState.Available)
                               BottomNavigationBar(navController = navController)
                       },
                        topBar = {
                            var title by remember {
                                mutableStateOf(navController.currentBackStackEntry?.destination?.route.toString())
                            }
                            navController.addOnDestinationChangedListener { controller, destination, arguments ->
                                title = destination.route.toString().split("?").first().replaceFirstChar {
                                    if (it.isLowerCase()) it.titlecase(
                                        Locale.getDefault()
                                    ) else it.toString()
                                }
                            }
                            TopAppBar(
                                title = {
                                    Text(if(title=="null") "NYT" else title)
                                }
                            )
                        },

                    ){
                        innerPadding ->
                        Column (
                            verticalArrangement =  Arrangement.Top,
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(innerPadding)
                        ){
                            ConnectivityStatus()
                            when(connection === ConnectionState.Available){
                                true -> AppNavHost(navController = navController)
                                false -> Text(text = "")
                            }
                        }
                    }

                }
            }
        }
    }
}

