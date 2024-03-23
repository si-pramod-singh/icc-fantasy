package com.si.iccfantasy

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.si.iccfantasy.core.FantasyAuthManager
import com.si.iccfantasy.core.FantasyConstant

@Composable
fun FantasyGame(bundle: Map<String, String>? = null) {

    val token by FantasyAuthManager.accessToken.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Column {
            Row {
                Text(text = "Environment:")
                Text(
                    text = FantasyConstant.constant[FantasyConstant.ENVIRONMENT].orEmpty()
                )
            }
            Row {
                Text(text = "Language:")
                Text(
                    text = FantasyConstant.constant[FantasyConstant.LANGUAGE].orEmpty()
                )
            }
            Row {
                Text(text = "Host version:")
                Text(
                    text = FantasyConstant.constant[FantasyConstant.APP_VERSION].orEmpty()
                )
            }
        }

        token?.let { Text("Token:" + it) } ?: Text("Not logged in!")

    }
}