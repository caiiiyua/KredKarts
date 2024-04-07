package com.caiiiyua.kredkarts.home.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.caiiiyua.kredkarts.data.entity.CreditCard
import com.caiiiyua.kredkarts.ui.PreviewData
import com.caiiiyua.kredkarts.ui.theme.KredKartsTheme

@Composable
fun CreditCardItem(
    creditCard: CreditCard,
    onCardClicked: (CreditCard) -> Unit = {},
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp, 4.dp)
            .clip(CardDefaults.shape)
            .clickable { onCardClicked(creditCard) }
            .clip(CardDefaults.shape),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
        ) {
            Text(
                style = MaterialTheme.typography.titleLarge,
                text = creditCard.cardNumber
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    style = MaterialTheme.typography.labelMedium,
                    text = creditCard.cardType
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    style = MaterialTheme.typography.labelSmall,
                    text = creditCard.expiryDate
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CreditCardItemPreview() {
    val creditCard = PreviewData.provideCreditCard()
    KredKartsTheme {
        CreditCardItem(creditCard)
    }
}