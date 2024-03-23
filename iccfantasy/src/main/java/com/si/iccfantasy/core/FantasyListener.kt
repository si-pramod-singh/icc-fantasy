package com.si.iccfantasy.core

/**
 * Interface for communication between Game and the host app
 * Implement it ad pass it to the [Fantasy.listener]
 *
 * Note:
 * Methods marked as Required *should* be fully implemented.
 * Methods marked as Optional may be not implemented by the host (no-op)
 */
interface FantasyListener {

    /**
     * Required.
     * It is used by the games to start login flow.
     */
    fun openLoginPage()

    /**
     * Required.
     * It is used by the games to start register flow.
     */
    fun openRegistrationPage()

    /**
     * Optional.
     * It is used by the games to logout.
     */
    fun logout()

    /**
     * Optional.
     * It is used to ask to close game.
     */
    fun closeGame()

}