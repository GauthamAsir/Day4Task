package a.gautham.day4task

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private val imagesArray = arrayOf(
            "https://firebasestorage.googleapis.com/v0/b/agjsproject.appspot.com/o/sweets%2Fladoo.jpg?alt=media&token=6e4fc012-6737-41ee-a60f-e7755e7e230d",
            "https://firebasestorage.googleapis.com/v0/b/agjsproject.appspot.com/o/sweets%2Fchikki.jpg?alt=media&token=7c03c318-91df-465a-b405-36a68729e593",
            "https://firebasestorage.googleapis.com/v0/b/agjsproject.appspot.com/o/sweets%2Frasgulla.jpg?alt=media&token=63ce8faf-6fed-4834-bd82-d57f2f7209f1",
            "https://firebasestorage.googleapis.com/v0/b/agjsproject.appspot.com/o/sweets%2Fcham_cham.jpg?alt=media&token=e849f4a0-5c93-4db1-9d57-0fe45c2fe67b",
            "https://firebasestorage.googleapis.com/v0/b/agjsproject.appspot.com/o/sweets%2Fbarfi.jpg?alt=media&token=c016cfe6-ba48-453b-9ff6-f556e6b8444a",
            "https://firebasestorage.googleapis.com/v0/b/agjsproject.appspot.com/o/sweets%2Fgulab_jamun.jpg?alt=media&token=976e37cf-5ceb-4a1f-974b-b9a05348dc73",
            "https://firebasestorage.googleapis.com/v0/b/agjsproject.appspot.com/o/sweets%2Fmysore-pak.png?alt=media&token=a9770964-1e59-4ff1-9223-3cfbf338ea93",
            "https://firebasestorage.googleapis.com/v0/b/agjsproject.appspot.com/o/sweets%2Fmawa_peda.jpeg?alt=media&token=e031275f-94e8-4a13-bf5d-cd2475d433e4"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadImage(imagesArray[0], ladoo)
        loadImage(imagesArray[1], chikki)
        loadImage(imagesArray[2], rasgulla)
        loadImage(imagesArray[3], cham_cham)
        loadImage(imagesArray[4], barfi)
        loadImage(imagesArray[5], gulab_jamun)
        loadImage(imagesArray[6], mysore_pak)
        loadImage(imagesArray[7], mawa_peda)

        ladoo.setOnClickListener{
            startActivity(Intent(this,Counter::class.java)
                .putExtra("img",imagesArray[0]))
        }
        chikki.setOnClickListener{
            startActivity(Intent(this,Counter::class.java)
                .putExtra("img",imagesArray[1]))
        }
        rasgulla.setOnClickListener{
            startActivity(Intent(this,Counter::class.java)
                .putExtra("img",imagesArray[2]))
        }
        cham_cham.setOnClickListener{
            startActivity(Intent(this,Counter::class.java)
                .putExtra("img",imagesArray[3]))
        }
        barfi.setOnClickListener{
            startActivity(Intent(this,Counter::class.java)
                .putExtra("img",imagesArray[4]))
        }
        gulab_jamun.setOnClickListener{
            startActivity(Intent(this,Counter::class.java)
                .putExtra("img",imagesArray[5]))
        }
        mysore_pak.setOnClickListener{
            startActivity(Intent(this,Counter::class.java)
                .putExtra("img",imagesArray[6]))
        }
        mawa_peda.setOnClickListener{
            startActivity(Intent(this,Counter::class.java)
                .putExtra("img",imagesArray[7]))
        }

        val toast = Toast.makeText(applicationContext, "Click on image to navigate to counter activity", Toast.LENGTH_LONG)
        toast.setGravity(Gravity.CENTER, 0, 0)
        toast.show()

    }

    private fun loadImage(url: String, img: ImageView) {
        Picasso.get().load(url).into(img)
    }

    override fun onClick(v: View?) {
        startActivity(Intent(this,Counter::class.java).putExtra("img","Img"))
    }

}