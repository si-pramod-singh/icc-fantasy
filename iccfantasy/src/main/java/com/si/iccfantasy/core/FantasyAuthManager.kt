package com.si.iccfantasy.core

import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

internal data class User(val guid: String, val name: String)

class FantasyAuthManager {

    companion object {

        private const val TAG = "FantasyAuthManager"

        private val _accessToken = MutableStateFlow<String?>(null)
        val accessToken: StateFlow<String?>
            get() = _accessToken

        private val _user = MutableStateFlow<User?>(null)
        internal val user: StateFlow<User?>
            get() = _user

        val isLoggedIn: Boolean
            get() = _accessToken.value != null && _user.value != null

        fun init() {
            CoroutineScope(Dispatchers.Main).launch {
                _accessToken.collect {
                    if (it != null) {
                        //api call
                        Log.i(TAG, "Performing game user authorization")
                        _user.value = User("", "")
                    }
                }
            }
        }

        fun setAccessToken(token: String) {
            _accessToken.value = token
        }

        fun logout() {
            _accessToken.value = null
            _user.value = null
        }

    }

}