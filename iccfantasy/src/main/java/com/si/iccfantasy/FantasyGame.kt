package com.si.iccfantasy

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.si.iccfantasy.core.Fantasy
import com.si.iccfantasy.core.FantasyAuthManager
import com.si.iccfantasy.core.FantasyConstant

@Composable
fun FantasyGame(bundle: Map<String, String>? = null) {

    val token by FantasyAuthManager.accessToken.collectAsState()

    Column(
        modifier = Modifier
            .background(Color.Black.copy(0.05f))
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Spacer(modifier = Modifier.weight(1f))

        Column(modifier = Modifier) {

            Text(text = "ICC Fantasy Game", fontSize = 20.sp)

            Spacer(modifier = Modifier.height(40.dp))

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

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = {
                Fantasy.login()
            }) {
            Text(text = "Login to play game")
        }
    }
}