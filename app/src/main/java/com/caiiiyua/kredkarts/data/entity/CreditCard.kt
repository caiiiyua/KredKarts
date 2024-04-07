package com.caiiiyua.kredkarts.data.entity

import com.caiiiyua.kredkarts.data.network.CreditCardDto

/**
 * Represents a credit card.
 */
data class CreditCard(
    val id: Int,
    val uid: String,
    val cardNumber: String,
    val expiryDate: String,
    val cardType: String
) {
    companion object {
        fun fromDto(dto: CreditCardDto): CreditCard {
            return CreditCard(
                id = dto.id,
                uid = dto.uid,
                cardNumber = dto.cardNumber,
                expiryDate = dto.expirationDate,
                cardType = dto.cardType
            )
        }
    }
}
