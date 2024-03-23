package com.si.iccfantasy.core

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class FantasyConsentManager {

    companion object {

        private val _consents = MutableStateFlow<Map<String, String>>(emptyMap())
        val consents: StateFlow<Map<String, String>>
            get() = _consents

        fun setConsents(consents: Map<String, String>) {
            _consents.value = consents
        }

    }

}