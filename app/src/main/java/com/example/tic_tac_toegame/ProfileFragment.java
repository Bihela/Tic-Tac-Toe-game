package com.example.tic_tac_toegame;

import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class ProfileFragment extends Fragment {

    private EditText userNameEditText;
    private ImageView selectedAvatarImageView;
    private Button saveProfileButton;

    private SharedPreferences sharedPreferences;

    public ProfileFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        userNameEditText = view.findViewById(R.id.userNameEditText);
        selectedAvatarImageView = view.findViewById(R.id.selectedAvatarImageView);
        saveProfileButton = view.findViewById(R.id.saveProfileButton);

        sharedPreferences = requireContext().getSharedPreferences("UserProfile", 0);

        String savedUserName = sharedPreferences.getString("userName", "");
        int savedAvatarId = sharedPreferences.getInt("avatarId", R.drawable.avatar);

        userNameEditText.setText(savedUserName);
        selectedAvatarImageView.setImageResource(savedAvatarId);

        selectedAvatarImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int newAvatarId = getSelectedAvatarId();
                selectedAvatarImageView.setImageResource(newAvatarId);
            }
        });

        saveProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveUserProfile();
            }
        });

        return view;
    }

    private int getSelectedAvatarId() {
        // Implement your logic to retrieve the selected avatar ID here
        // Replace the following line with your actual logic.
        int selectedAvatarId = R.drawable.avatar; // Replace with the actual resource ID

        return selectedAvatarId;
    }

    private void saveUserProfile() {
        String userName = userNameEditText.getText().toString();
        int selectedAvatarId = getSelectedAvatarId();

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("userName", userName);
        editor.putInt("avatarId", selectedAvatarId);
        editor.apply();
    }
}
