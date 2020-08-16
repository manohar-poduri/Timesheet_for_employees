package com.example.timesheetforemployees;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class finger extends AppCompatActivity {

    TextView txtDisplay;
    ImageView imgPrint;

    private FingerprintManager fingerprintManager;
    private FingerprintManager.AuthenticationCallback authenticationCallback;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finger);

            txtDisplay = findViewById(R.id.txtDisplay);
            imgPrint = findViewById(R.id.imgPrint);
           

            fingerprintManager = (FingerprintManager) getSystemService(FINGERPRINT_SERVICE);

            authenticationCallback = new FingerprintManager.AuthenticationCallback() {
                @Override
                public void onAuthenticationError(int errorCode, CharSequence errString) {
                    txtDisplay.setText("ERROR");
                    imgPrint.setImageResource(R.drawable.ic_fingerprint_error);
                    super.onAuthenticationError(errorCode, errString);
                }

                @Override
                public void onAuthenticationHelp(int helpCode, CharSequence helpString) {
                    txtDisplay.setText("HELP");
                    imgPrint.setImageResource(R.drawable.ic_fingerprint_help);
                    super.onAuthenticationHelp(helpCode, helpString);
                }

                @Override
                public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult result) {
                    txtDisplay.setText("SUCCESS");
                    imgPrint.setImageResource(R.drawable.ic_fingerprint_success);
                    super.onAuthenticationSucceeded(result);
                    Intent intent = new Intent(finger.this, MapsActivity.class);
                    startActivity(intent);
                }

                @Override
                public void onAuthenticationFailed() {
                    txtDisplay.setText("FAILED");
                    imgPrint.setImageResource(R.drawable.ic_fingerprint_failed);
                    super.onAuthenticationFailed();
                }
            };

            fingerprintManager.authenticate(null,null,0,authenticationCallback,null);
        }
    }
