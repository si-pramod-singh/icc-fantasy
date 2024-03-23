package com.si.iccfantasy.core

import android.app.Application
import android.content.Context
import androidx.annotation.RestrictTo
import androidx.annotation.StringDef
import java.util.Locale


class Fantasy {

    @StringDef(ENV_INT, ENV_PRE, ENV_PROD)
    @Retention(AnnotationRetention.SOURCE)
    annotation class Environment

    companion object {

        const val ENV_INT = "int"
        const val ENV_PRE = "pre"
        const val ENV_PROD = "prod"

        /**
         * Game should not set this variable
         *
         * Host is expected to set this variable
         */
        var listener: FantasyListener? = null

        private lateinit var application: Application

        @RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
        fun getContext(): Application {
            return application
        }

        fun init(
            context: Context,
            @Environment environment: String,
            versionName: String,
            locale: String = Locale.getDefault().toLanguageTag(),
        ) {
            this.application = context.applicationContext as Application
            FantasyConstant.setup(environment, versionName, locale)
            FantasyAuthManager.init()
        }

        fun login() {
            listener?.openLoginPage()
        }

        fun register() {
            listener?.openRegistrationPage()
        }

        fun logout() {
            listener?.logout()
        }

        fun close() {
            listener?.closeGame()
        }

    }

}