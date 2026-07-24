package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.Repeat
import androidx.compose.material.icons.filled.Shuffle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.FlashcardEntity
import com.example.data.viewmodel.MainViewModel
import com.example.ui.components.FlashcardItem
import com.example.ui.components.SrsRatingBar

@Composable
fun DeckScreen(
    viewModel: MainViewModel,
    onNavigateToSpeakingLabWithCard: (FlashcardEntity) -> Unit,
    modifier: Modifier = Modifier
) {
    val cards by viewModel.filteredCards.collectAsState()
    val currentIndex by viewModel.currentDeckIndex.collectAsState()
    val isFlipped by viewModel.isCardFlipped.collectAsState()
    val selectedNiveau by viewModel.selectedNiveau.collectAsState()
    val selectedTheme by viewModel.selectedTheme.collectAsState()

    val niveaux = listOf("Tous", "A2", "B1", "B2")
    val themes = listOf(
        "Tous",
        "Vie Quotidienne",
        "Opinions & Émotions",
        "Voyages & Transports",
        "Travail & Affaires",
        "Social & Conversations",
        "Urgences & Problèmes",
        "Récits & Expériences",
        "Projets & Hypothèses"
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = 16.dp)
            .verticalScroll(rememberScrollState())
            .padding(bottom = 80.dp)
    ) {
        Spacer(modifier = Modifier.height(12.dp))

        // Level Filter Chips
        Text(
            text = "Niveau :",
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground
        )

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(vertical = 4.dp)
        ) {
            items(niveaux) { niveau ->
                FilterChip(
                    selected = selectedNiveau == niveau,
                    onClick = { viewModel.setNiveauFilter(niveau) },
                    label = { Text(text = niveau, fontSize = 12.sp) }
                )
            }
        }

        // Theme Filter Chips
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(bottom = 12.dp)
        ) {
            items(themes) { theme ->
                FilterChip(
                    selected = selectedTheme == theme,
                    onClick = { viewModel.setThemeFilter(theme) },
                    label = { Text(text = theme, fontSize = 11.sp) }
                )
            }
        }

        if (cards.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "Aucune carte ne correspond aux filtres 🔍",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Button(onClick = {
                        viewModel.setNiveauFilter("Tous")
                        viewModel.setThemeFilter("Tous")
                    }) {
                        Text("Réinitialiser les filtres")
                    }
                }
            }
        } else {
            val safeIndex = currentIndex.coerceIn(0, cards.size - 1)
            val currentCard = cards[safeIndex]

            // Counter Header
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Carte ${safeIndex + 1} sur ${cards.size}",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )

                IconButton(onClick = { viewModel.previousCard(cards.size) }) {
                    Icon(
                        imageVector = Icons.Default.Shuffle,
                        contentDescription = "Mélanger",
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Animated Flashcard
            FlashcardItem(
                card = currentCard,
                isFlipped = isFlipped,
                onFlip = { viewModel.flipCard() },
                onFavoriteToggle = { viewModel.toggleFavorite(currentCard) },
                onPlayAudio = { text -> viewModel.speakText(text) },
                onPracticeSpeaking = { card -> onNavigateToSpeakingLabWithCard(card) }
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Navigation Controls
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = { viewModel.previousCard(cards.size) },
                    modifier = Modifier.size(48.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.ChevronLeft,
                        contentDescription = "Précédent",
                        modifier = Modifier.size(32.dp)
                    )
                }

                Button(
                    onClick = { viewModel.flipCard() },
                    shape = RoundedCornerShape(20.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        contentColor = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                ) {
                    Icon(
                        imageVector = Icons.Default.Repeat,
                        contentDescription = null,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(text = if (isFlipped) "Voir Malagasy" else "Voir Verso")
                }

                IconButton(
                    onClick = { viewModel.nextCard(cards.size) },
                    modifier = Modifier.size(48.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.ChevronRight,
                        contentDescription = "Suivant",
                        modifier = Modifier.size(32.dp)
                    )
                }
            }

            // Spaced Repetition SRS Rating Bar (Visible when card is flipped)
            AnimatedVisibility(visible = isFlipped) {
                Column(modifier = Modifier.padding(top = 16.dp)) {
                    SrsRatingBar(
                        onRatingSelected = { rating ->
                            viewModel.submitSrsRating(currentCard, rating, cards.size)
                        }
                    )
                }
            }
        }
    }
}
