package com.example.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.repository.SrsRating

@Composable
fun SrsRatingBar(
    onRatingSelected: (SrsRating) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Évaluez votre mémorisation (Spaced Repetition)",
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            SrsButton(
                title = "À revoir",
                subtitle = "< 1 jour",
                color = Color(0xFFEF4444),
                onClick = { onRatingSelected(SrsRating.AGAIN) },
                modifier = Modifier
                    .weight(1f)
                    .testTag("srs_again_btn")
            )

            SrsButton(
                title = "Difficile",
                subtitle = "1-2 jours",
                color = Color(0xFFF59E0B),
                onClick = { onRatingSelected(SrsRating.HARD) },
                modifier = Modifier
                    .weight(1f)
                    .testTag("srs_hard_btn")
            )

            SrsButton(
                title = "Bon",
                subtitle = "3-5 jours",
                color = Color(0xFF10B981),
                onClick = { onRatingSelected(SrsRating.GOOD) },
                modifier = Modifier
                    .weight(1f)
                    .testTag("srs_good_btn")
            )

            SrsButton(
                title = "Facile",
                subtitle = "7+ jours",
                color = Color(0xFF3B82F6),
                onClick = { onRatingSelected(SrsRating.EASY) },
                modifier = Modifier
                    .weight(1f)
                    .testTag("srs_easy_btn")
            )
        }
    }
}

@Composable
private fun SrsButton(
    title: String,
    subtitle: String,
    color: Color,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        modifier = modifier.height(52.dp),
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = color,
            contentColor = Color.White
        ),
        contentPadding = androidx.compose.foundation.layout.PaddingValues(4.dp)
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = title,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = subtitle,
                fontSize = 10.sp,
                fontWeight = FontWeight.Normal,
                color = Color.White.copy(alpha = 0.85f)
            )
        }
    }
}
