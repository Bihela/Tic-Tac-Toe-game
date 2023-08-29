package com.example.tic_tac_toegame;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Spinner;

public class SettingsFragment extends Fragment {

    private Spinner boardSizeSpinner;
    private SeekBar winConditionSeekBar;
    private Spinner playerMarkerSpinner;
    private Button applyButton;

    public SettingsFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        boardSizeSpinner = view.findViewById(R.id.boardSizeSpinner);
        winConditionSeekBar = view.findViewById(R.id.winConditionSeekBar);
        playerMarkerSpinner = view.findViewById(R.id.playerMarkerSpinner);
        applyButton = view.findViewById(R.id.applyButton);

        applyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                applySettings();
            }
        });

        return view;
    }

    private void applySettings() {
        int selectedBoardSize = Integer.parseInt(boardSizeSpinner.getSelectedItem().toString());
        int selectedWinCondition = winConditionSeekBar.getProgress() + 3;
        String selectedPlayerMarker = playerMarkerSpinner.getSelectedItem().toString();

        Fragment activeFragment = getParentFragmentManager().findFragmentById(R.id.fragmentContainer);
        if (activeFragment instanceof TwoPlayerGameFragment) {
            ((TwoPlayerGameFragment) activeFragment).applySettings(selectedBoardSize, selectedWinCondition, selectedPlayerMarker);
        } else if (activeFragment instanceof AIGameFragment) {
            ((AIGameFragment) activeFragment).applySettings(selectedBoardSize, selectedWinCondition, selectedPlayerMarker);
        }
    }
}
