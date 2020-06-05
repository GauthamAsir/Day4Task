package a.gautham.day4task

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class Counter : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_counter)

        val counterTv = findViewById<TextView>(R.id.counter_tv)
        val increase = findViewById<Button>(R.id.increase)

        var counter = 0

        increase.setOnClickListener {
            counter += 1
            counterTv.text = counter.toString()
        }

    }
}