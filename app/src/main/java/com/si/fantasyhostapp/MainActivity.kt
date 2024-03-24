@file:OptIn(ExperimentalMaterial3Api::class)

package com.si.fantasyhostapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.si.fantasyhostapp.ui.theme.FantasyHostAppTheme
import com.si.iccfantasy.FantasyGame
import com.si.iccfantasy.core.Fantasy
import com.si.iccfantasy.core.FantasyAuthManager
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.Locale
import java.util.UUID

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Fantasy.init(
            context = this,
            environment = Fantasy.ENV_PRE,
            versionName = packageManager.getPackageInfo(packageName, 0).versionName,
            locale = Locale.getDefault().toLanguageTag()
        )
        setContent {

            val coroutineScope = rememberCoroutineScope()

            val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

            FantasyHostAppTheme {
                ModalNavigationDrawer(
                    scrimColor = Color.Gray,
                    drawerState = drawerState,
                    drawerContent = {
                        ModalDrawerSheet {
                            Column {
                                Spacer(modifier = Modifier.weight(1f))
                                TextButton(onClick = {
                                    //host app login operation
                                    FantasyAuthManager.setAccessToken(UUID.randomUUID().toString())
                                }) {
                                    Text(text = "Click to Login")
                                }
                            }
                        }
                    }
                ) {
                    Scaffold(topBar = {
                        CenterAlignedTopAppBar(
                            title = { Text("ICC World Cup") },
                            navigationIcon = {
                                IconButton(onClick = {
                                    coroutineScope.launch {
                                        drawerState.open()
                                    }
                                }) {
                                    Icon(
                                        imageVector = Icons.Default.Menu,
                                        contentDescription = "Menu"
                                    )
                                }
                            })
                    }) {
                        // A surface container using the 'background' color from the theme
                        Surface(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(it),
                            color = MaterialTheme.colorScheme.background
                        ) {
                            FantasyGame()
                        }
                    }
                }
            }
        }
    }
}


@Preview
@Composable
fun HostHomePage() {

    val coroutineScope = rememberCoroutineScope()

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

    var loader by remember { mutableStateOf(false) }

    ModalNavigationDrawer(
        scrimColor = Color.Gray,
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                modifier = Modifier,
                drawerContainerColor = Color(android.graphics.Color.parseColor("#000066"))
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Spacer(modifier = Modifier.weight(1f))
                    Button(
                        modifier = Modifier.align(Alignment.CenterHorizontally),
                        colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                        onClick = {
                            coroutineScope.launch {
                                drawerState.close()
                                loader = true
                                //host app login operation
                                delay(2000L)
                                loader = false
                                FantasyAuthManager.setAccessToken(UUID.randomUUID().toString())
                            }
                        }) {
                        Text(text = "Click to Login", color = Color.Black)
                    }
                }
            }
        }
    ) {
        Scaffold(topBar = {
            CenterAlignedTopAppBar(
                title = { Text("ICC World Cup") },
                navigationIcon = {
                    IconButton(onClick = {
                        coroutineScope.launch {
                            drawerState.open()
                        }
                    }) {
                        Icon(
                            imageVector = Icons.Default.Menu,
                            contentDescription = "Menu"
                        )
                    }
                })
        }) {
            // A surface container using the 'background' color from the theme
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it),
                color = MaterialTheme.colorScheme.background
            ) {
                FantasyGame()
                if (loader) Box(
                    modifier = Modifier
                        .background(Color.Black.copy(0.1f))
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FantasyHostAppTheme {
        Greeting("Android")
    }
}