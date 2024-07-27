package ir.amirhosein.rockpaperscissorsgame

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import ir.amirhosein.rockpaperscissorsgame.ui.theme.RockPaperScissorsGameTheme
import ir.amirhosein.rockpaperscissorsgame.viewModel.GameViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RockPaperScissorsGameTheme {

                RockPaperScissorsGame()

            }
        }
    }
}

@Composable
fun RockPaperScissorsGame(gameViewModel: GameViewModel = viewModel()) {
    // Collect the game state from the view model
    val gameState by gameViewModel.gameState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Title of the game
        Text(
            text = "Rock Paper Scissors Game",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // List of choices and corresponding image resources
        val choices = listOf("Rock", "Paper", "Scissors")
        val choiceImages = listOf(
            R.drawable.rock,
            R.drawable.paper,
            R.drawable.scissors
        )

        // Display choice buttons with images
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            choices.forEachIndexed { index, choice ->
                Button(onClick = {
                    gameViewModel.playGame(choice)
                }) {
                    Image(
                        painter = painterResource(id = choiceImages[index]),
                        contentDescription = choice,
                        modifier = Modifier.size(64.dp)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Display the user's choice and game result
        if (gameState.userChoice.isNotEmpty()) {
            Text(
                text = "You chose: ${gameState.userChoice}",
                fontSize = 20.sp,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = "Result: ${gameState.result}",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Red
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Display the player and computer scores
        Text(
            text = "Player Score: ${gameState.playerScore}",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Text(
            text = "Computer Score: ${gameState.computerScore}",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Blue
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Reset scores button
        Button(onClick = {
            gameViewModel.resetScores()
        }) {
            Text(text = "Reset Scores")
        }
    }
}
