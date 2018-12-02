package fri.ep

import android.os.AsyncTask
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val tag = MainActivity::class.java.canonicalName
    var task: MyAsyncTask? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        progressBar.max = 100

        btnStart.setOnClickListener {
            task = MyAsyncTask()
            task?.execute(50)
        }

        Log.i(tag, "Metode GUI poganja nit " + Thread.currentThread())
    }

    override fun onStop() {
        task?.cancel(true)
        task = null
        super.onStop()
    }


    class MyAsyncTask : AsyncTask<Int, Double, Unit>() {
        val tag = MyAsyncTask::class.java.canonicalName

        override fun doInBackground(vararg params: Int?): Unit {
            for (i in 0 until params[0]!!) {
                if (isCancelled) {
                    break
                }

                simulateWork()
            }
        }

        private fun simulateWork() {
            Log.i(tag, "Simuliramo opravilo ... čakamo 1 sekundo. Nit = ${Thread.currentThread()}")
            try {
                Thread.sleep(1000)
            } catch (e: InterruptedException) {
                Log.e(tag, "Interrupted", e)
            }
        }
    }

}
