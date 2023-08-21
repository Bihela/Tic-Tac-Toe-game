import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Check if the fragment container is empty (initial launch)
        if (savedInstanceState == null) {
            // Load the main menu fragment by default
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragmentContainer, new MainMenuFragment())
                    .commit();
        }
    }
}
