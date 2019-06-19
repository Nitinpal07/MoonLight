package nitin.luckyproject.moonlight;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    SeekBar seekBar;
    TextView progress;
    Context context;
    int brightness;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seekBar =findViewById(R.id.seek);
        progress =findViewById(R.id.progress);
        context =getApplicationContext();
        brightness = Settings.System.getInt(context.getContentResolver(),Settings.System.SCREEN_BRIGHTNESS,0);


        //Setting up current screen brightness to seekbar
        seekBar.setProgress(brightness);

        progress.setText("Screen Brightness :" + brightness);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean fromUser) {
                boolean settingsCanWrite = Settings.System.canWrite(context);

                progress.setText("Screen Brightness :" + i);
                //Chnaging Brightness on seekbar movement

                if(settingsCanWrite)
                {
                    Settings.System.putInt(context.getContentResolver(), Settings.System.SCREEN_BRIGHTNESS, i);
                 }
                else{
                    Toast.makeText(MainActivity.this, "ajja", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent( "android.settings.action.MANAGE_WRITE_SETTINGS");
                    intent.setData(Uri.parse("package:nitin.luckyproject.moonlight"));
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
