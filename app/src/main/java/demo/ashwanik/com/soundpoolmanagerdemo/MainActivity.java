package demo.ashwanik.com.soundpoolmanagerdemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SoundPoolManager.CreateInstance();
        List<Integer> sounds = new ArrayList<Integer>();
        sounds.add(R.raw.sound1);
        sounds.add(R.raw.sound2);
        SoundPoolManager.getInstance().setSounds(sounds);
        try {
            SoundPoolManager.getInstance().InitializeSoundPool(this, new ISoundPoolLoaded() {
                @Override
                public void onSuccess() {
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

        SoundPoolManager.getInstance().setPlaySound(true);
        Button playSound1 = (Button)findViewById(R.id.button1);

        playSound1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SoundPoolManager.getInstance().playSound(R.raw.sound1);
            }
        });


        Button playSound2 = (Button)findViewById(R.id.button2);

        playSound2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SoundPoolManager.getInstance().playSound(R.raw.sound2);
            }
        });




    }

    @Override
    protected void onDestroy() {
        SoundPoolManager.getInstance().release();
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
