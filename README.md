# Rock Paper Scissors Game

This is a simple Rock Paper Scissors game implemented using Jetpack Compose and MVVM architecture in Kotlin. The game allows users to play against the computer, keeps track of scores, and provides a reset option to clear the scores.

## Features

- Play Rock Paper Scissors against the computer.
- Keeps track of player and computer scores.
- Provides a reset button to clear the scores.
- Uses Jetpack Compose for the UI.
- Follows MVVM architecture for state management.

## Screenshots

![Screenshot](screenshot.png)

## Getting Started

### Prerequisites

- Android Studio
- Kotlin

### Installation

1. Clone the repository:
    ```sh
    git clone https://github.com/AmirhosseinForouzan/Rock-Paper-Scissors-Game-JetpackCompose
    ```
2. Open the project in Android Studio.

3. Build and run the project on an Android device or emulator.

### Usage

- Click on one of the buttons (Rock, Paper, Scissors) to make your choice.
- The result will be displayed along with the computer's choice.
- The scores for both the player and the computer will be updated.
- Click the "Reset Scores" button to reset the scores to zero.

## Code Structure

### GameState.kt

Defines the state of the game including user choice, result, player score, and computer score.

### GameViewModel.kt

Contains the game logic, updates the game state, and interacts with the UI through StateFlow.

### MainActivity.kt

Sets up the UI using Jetpack Compose and collects the game state from the ViewModel.

## Dependencies

- Jetpack Compose
- Kotlin

## Acknowledgements

- Jetpack Compose documentation
- MVVM architecture guidelines

