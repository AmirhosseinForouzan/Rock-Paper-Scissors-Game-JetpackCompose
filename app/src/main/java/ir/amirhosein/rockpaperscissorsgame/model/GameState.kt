package ir.amirhosein.rockpaperscissorsgame.model

data class GameState(
    val userChoice: String = "",
    val result: String = "",
    val playerScore: Int = 0,
    val computerScore: Int = 0
)