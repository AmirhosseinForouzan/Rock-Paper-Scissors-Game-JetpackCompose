package ir.amirhosein.rockpaperscissorsgame.viewModel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ir.amirhosein.rockpaperscissorsgame.model.GameState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class GameViewModel(private val stateHandle: SavedStateHandle) : ViewModel() {

    // Keys for SavedStateHandle
    companion object {
        private const val USER_CHOICE_KEY = "user_choice"
        private const val RESULT_KEY = "result"
        private const val PLAYER_SCORE_KEY = "player_score"
        private const val COMPUTER_SCORE_KEY = "computer_score"
    }

    // Initial state from SavedStateHandle or default values
    private val _gameState = MutableStateFlow(
        GameState(
            userChoice = stateHandle[USER_CHOICE_KEY] ?: "",
            result = stateHandle[RESULT_KEY] ?: "",
            playerScore = stateHandle[PLAYER_SCORE_KEY] ?: 0,
            computerScore = stateHandle[COMPUTER_SCORE_KEY] ?: 0
        )
    )

    val gameState: StateFlow<GameState> = _gameState

    fun playGame(userChoice: String) {
        viewModelScope.launch {
            val choices = listOf("Rock", "Paper", "Scissors")
            val computerChoice = choices.random()
            val result: String
            val winner: String

            when {
                userChoice == computerChoice -> {
                    result = "It's a tie! Computer also chose $computerChoice."
                    winner = "draw"
                }
                (userChoice == "Rock" && computerChoice == "Scissors") ||
                        (userChoice == "Paper" && computerChoice == "Rock") ||
                        (userChoice == "Scissors" && computerChoice == "Paper") -> {
                    result = "You win! Computer chose $computerChoice."
                    winner = "player"
                }
                else -> {
                    result = "You lose! Computer chose $computerChoice."
                    winner = "computer"
                }
            }

            // Update the state
            _gameState.value = _gameState.value.copy(
                userChoice = userChoice,
                result = result,
                playerScore = if (winner == "player") _gameState.value.playerScore + 1 else _gameState.value.playerScore,
                computerScore = if (winner == "computer") _gameState.value.computerScore + 1 else _gameState.value.computerScore
            )

            // Save state to SavedStateHandle
            stateHandle[USER_CHOICE_KEY] = _gameState.value.userChoice
            stateHandle[RESULT_KEY] = _gameState.value.result
            stateHandle[PLAYER_SCORE_KEY] = _gameState.value.playerScore
            stateHandle[COMPUTER_SCORE_KEY] = _gameState.value.computerScore
        }
    }

    fun resetScores() {
        _gameState.value = GameState()

        // Save state to SavedStateHandle
        stateHandle[USER_CHOICE_KEY] = _gameState.value.userChoice
        stateHandle[RESULT_KEY] = _gameState.value.result
        stateHandle[PLAYER_SCORE_KEY] = _gameState.value.playerScore
        stateHandle[COMPUTER_SCORE_KEY] = _gameState.value.computerScore
    }
}

