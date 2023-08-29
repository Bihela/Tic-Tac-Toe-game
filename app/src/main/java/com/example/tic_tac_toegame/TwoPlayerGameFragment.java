package com.example.tic_tac_toegame;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class TwoPlayerGameFragment extends Fragment {

    private int boardSize;
    private int winCondition;
    private String player1Marker;
    private String player2Marker;

    private Button[][] cells;
    private boolean player1Turn = true;
    private boolean gameEnded = false;

    public TwoPlayerGameFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_two_player_game, container, false);

        cells = new Button[boardSize][boardSize];
        for (int i = 0; i < boardSize; i++) {
            final int rowIndex = i;
            for (int j = 0; j < boardSize; j++) {
                final int columnIndex = j;
                int cellId = getResources().getIdentifier("cell_" + i + "_" + j, "id", requireContext().getPackageName());
                cells[i][j] = view.findViewById(cellId);
                cells[i][j].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onCellClick(rowIndex, columnIndex);
                    }
                });
            }
        }

        return view;
    }

    private void onCellClick(int row, int col) {
        if (!gameEnded && cells[row][col].getText().toString().isEmpty()) {
            if (player1Turn) {
                cells[row][col].setText(player1Marker);
            } else {
                cells[row][col].setText(player2Marker);
            }
            player1Turn = !player1Turn;
            checkForWinOrDraw();
        }
    }

    private void checkForWinOrDraw() {
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
    }

    public void applySettings(int boardSize, int winCondition, String playerMarker) {
        this.boardSize = boardSize;
        this.winCondition = winCondition;
        this.player1Marker = playerMarker;
        this.player2Marker = playerMarker; // Use the same marker for both players
        resetGameBoard();
    }

    private void resetGameBoard() {
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                cells[i][j].setText("");
            }
        }
        player1Turn = true;
        gameEnded = false;
    }
}
