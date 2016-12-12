package ep.asynctask;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getCanonicalName();

    private Button button;
    private TextView tv;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "Metode GUI poganja nit " + Thread.currentThread());

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.btnStart);
        tv = (TextView) findViewById(R.id.tvProgress);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setMax(100);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final MyAsyncTask task = new MyAsyncTask();
                task.execute(5);
            }
        });
    }

    private class MyAsyncTask extends AsyncTask<Integer, Double, Void> {
        @Override
        protected Void doInBackground(Integer... params) {
            for (int i = 0; i < params[0]; i++) {
                simulateWork();
            }

            return null;
        }

        private void simulateWork() {
            Log.i(TAG, "Simuliramo opravilo ... čakamo 1 sekundo. Nit = " + Thread.currentThread());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Log.e(TAG, "Interrupted", e);
            }
        }
    }
}
