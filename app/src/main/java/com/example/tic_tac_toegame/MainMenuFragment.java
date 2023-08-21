import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class MainMenuFragment extends Fragment {

    // ... (existing code)

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_menu, container, false);

        Button startButton = view.findViewById(R.id.startButton);
        Button settingsButton = view.findViewById(R.id.settingsButton);
        Button exitButton = view.findViewById(R.id.exitButton);

        // Set click listeners and handle button actions
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Replace MainMenuFragment with the desired game fragment
                Fragment gameFragment; // Define the game fragment to launch

                // Check the user's selection for game mode
                // You can use a dialog or another UI element to get the user's choice
                // For now, we'll use TwoPlayerGameFragment as an example
                gameFragment = new TwoPlayerGameFragment();

                // Replace the current fragment with the selected game fragment
                requireFragmentManager().beginTransaction()
                        .replace(R.id.fragmentContainer, gameFragment)
                        .addToBackStack(null) // Allow back navigation to MainMenuFragment
                        .commit();
            }
        });

        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Replace MainMenuFragment with the SettingsFragment
                SettingsFragment settingsFragment = new SettingsFragment();

                // Replace the current fragment with the SettingsFragment
                requireFragmentManager().beginTransaction()
                        .replace(R.id.fragmentContainer, settingsFragment)
                        .addToBackStack(null) // Allow back navigation to MainMenuFragment
                        .commit();
            }
        });

        // Handle other button actions

        return view;
    }
}
