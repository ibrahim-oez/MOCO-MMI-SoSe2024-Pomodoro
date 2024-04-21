package th.koeln.pomodoro

import android.content.Context
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)  // <-- verweist auf die XML

        /*---------------Tragt bitte alle XML-Elemente hier ein !--------------*/
        val timerView = findViewById<TextView>(R.id.timer) // Timer-View
        val startButton = findViewById<Button>(R.id.start_btn) // Start Button
        /*---------------------------------------------------------------------*/

        //Timer beim drücken vom Button starten
        startButton.setOnClickListener {
            timer(timerView,30)
        }

    }
}

// Funktion die einen Timer erstellt
/**
 * @param timerTextView ist eine TextView Komponente (siehe activity_main.xml)
 * @param min gibt an wie viele Minuten der Timer haben soll
 */

fun timer(timerTextView:TextView, min:Int){
   val duration = 60000L * min // Minuten in Millisekunden
   val counter = object : CountDownTimer(duration, 1000) {
         override fun onTick(millisUntilFinished: Long) {
             val secondsLeft = millisUntilFinished / 1000
             val minutes = secondsLeft / 60 //bsp. 120 / 60 = 2 => 2min
             val seconds = secondsLeft % 60 //bsp. 135 % 60 | Rest 15 => 15 sec
             val timeLeftFormat = String.format("%02d:%02d", minutes, seconds)

             timerTextView.text = timeLeftFormat //neues Format = min:sec => 2m:15s => 2:15
         }

         override fun onFinish() {
             timerTextView.text = "00:00"
             timerTextView.textAlignment = TextView.TEXT_ALIGNMENT_CENTER // nach dem der Timer abläuft Text in der Mitte halten

         }
     }
    counter.start()
}

