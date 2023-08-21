import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class MainMenuFragment extends Fragment {

    public MainMenuFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_menu, container, false);

        Button startButton = view.findViewById(R.id.startButton);
        Button settingsButton = view.findViewById(R.id.settingsButton);
        Button exitButton = view.findViewById(R.id.exitButton);

        // Set click listeners and handle button actions

        return view;
    }
}
