import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class AIGameFragment extends Fragment {

    private int boardSize;
    private int winCondition;
    private String playerMarker;

    private Button[][] cells; // Represent the game cells
    private boolean gameEnded = false; // Check if the game has ended

    public AIGameFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ai_game, container, false);

        // Initialize the game cells UI elements
        cells = new Button[boardSize][boardSize];
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                int cellId = getResources().getIdentifier("cell_" + i + "_" + j, "id", requireContext().getPackageName());
                cells[i][j] = view.findViewById(cellId);
                cells[i][j].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onCellClick(i, j);
                    }
                });
            }
        }

        // ... (other initialization logic)

        return view;
    }

    private void onCellClick(int row, int col) {
        if (!gameEnded && cells[row][col].getText().toString().isEmpty()) {
            // Set the human player's marker on the clicked cell
            cells[row][col].setText(playerMarker);

            // Check for win or draw after each move
            checkForWinOrDraw();

            // AI makes its move
            makeAIMove();

            // Check for win or draw after AI move
            checkForWinOrDraw();
        }
    }


    private void makeAIMove() {
        // Generate random row and column indices
        int randomRow = new Random().nextInt(boardSize);
        int randomCol = new Random().nextInt(boardSize);

        // Check if the randomly selected cell is empty
        if (cells[randomRow][randomCol].getText().toString().isEmpty()) {
            // Set the AI's marker on the randomly selected cell
            cells[randomRow][randomCol].setText(playerMarker);

            // Check for win or draw after AI's move
            checkForWinOrDraw();

            // Toggle player turns
            isPlayerOneTurn = !isPlayerOneTurn;
        } else {
            // If the randomly selected cell is not empty, try again
            makeAIMove();
        }
    }

    private void checkForWinOrDraw() {
        // Check rows, columns, and diagonals for a win
        for (int i = 0; i < boardSize; i++) {
            if (checkLine(cells[i])) {
                endGame(cells[i][0].getText().toString() + " wins!");
                return;
            }

            Button[] column = new Button[boardSize];
            for (int j = 0; j < boardSize; j++) {
                column[j] = cells[j][i];
            }
            if (checkLine(column)) {
                endGame(cells[0][i].getText().toString() + " wins!");
                return;
            }
        }

        Button[] diagonal1 = new Button[boardSize];
        Button[] diagonal2 = new Button[boardSize];
        for (int i = 0; i < boardSize; i++) {
            diagonal1[i] = cells[i][i];
            diagonal2[i] = cells[i][boardSize - i - 1];
        }
        if (checkLine(diagonal1) || checkLine(diagonal2)) {
            endGame(cells[0][0].getText().toString() + " wins!");
            return;
        }

        // Check for a draw
        boolean isDraw = true;
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                if (cells[i][j].getText().toString().isEmpty()) {
                    isDraw = false;
                    break;
                }
            }
        }
        if (isDraw) {
            endGame("It's a draw!");
        }
    }

    private boolean checkLine(Button[] line) {
        String marker = line[0].getText().toString();
        for (Button cell : line) {
            if (!cell.getText().toString().equals(marker)) {
                return false;
            }
        }
        return !marker.isEmpty();
    }

    private void endGame(String message) {
        gameEnded = true;
        // Display the game outcome message, update stats, etc.
        // In AI mode, you can include a different message like "You lost!" for the player
    }


