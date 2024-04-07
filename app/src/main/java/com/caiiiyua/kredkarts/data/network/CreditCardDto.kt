package com.caiiiyua.kredkarts.data.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreditCardDto(
    val id: Int,
    val uid: String,
    @SerialName("credit_card_number")
    val cardNumber: String,
    @SerialName("credit_card_type")
    val cardType: String,
    @SerialName("credit_card_expiry_date")
    val expirationDate: String,
)
