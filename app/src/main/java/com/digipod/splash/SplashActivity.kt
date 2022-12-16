package com.digipod.splash

import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.animation.ValueAnimator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowManager
import android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import kotlinx.coroutines.Runnable

class SplashActivity : AppCompatActivity() {
    private lateinit var ivHeart:ImageView
    private lateinit var ivTop:ImageView
    private lateinit var ivBeat:ImageView
    private lateinit var ivBottom:ImageView
    private lateinit var textView: TextView
    private var delay = 200;
    private lateinit var charSequence:CharSequence
    private var index:Int = 0
    private var mHandler =  Handler()


    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash2)
        ivHeart = findViewById(R.id.iv_heart)
        ivBeat = findViewById(R.id.iv_beat)
        ivBottom = findViewById(R.id.iv_bottom)
        ivTop = findViewById(R.id.iv_top)
        textView = findViewById(R.id.text_view)

//Set full screen
        window.setFlags(
            FLAG_FULLSCREEN,
            FLAG_FULLSCREEN
        )

//Initialize top animation

        val animation1 = AnimationUtils.loadAnimation(this, R.anim.top_wave)

//Start top animation

        ivTop.animation = animation1

//Initialize object animator

         val objectAnimator:ObjectAnimator=ObjectAnimator.ofPropertyValuesHolder(
            ivHeart,
            PropertyValuesHolder.ofFloat("scaleX", 1.2f),
            PropertyValuesHolder.ofFloat("scaleY", 1.2f)
        )

//Set duration
        objectAnimator.duration = 500

//Set repeat count

        objectAnimator.repeatCount = ValueAnimator.INFINITE

//Set repeat mode

        objectAnimator.repeatMode = ValueAnimator.REVERSE
        objectAnimator.start()
        animatText("CliniNet")

//tLoad GIF

        Glide.with(this)
            .load("https://firebasestorage.googleapis.com/v0/b/applibrary-94f52.appspot.com/o/heart_beat.gif?alt=media&token=a5f5e774-77fb-4056-a047-13948c183f5f")
            .asGif()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(ivBeat)

//Initialize bottom animation

        val animation2 = AnimationUtils.loadAnimation(this, R.anim.bottom_wave)

//Start top animation

        ivBottom.animation = animation2
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, SignInActivity::class.java))
            finish()
        },3000)
    }





private var runnable:Runnable= Runnable {
    run{

                textView.text = charSequence.subSequence(0, index++);
//Check condition
                if (index <= charSequence.length) {
                   mHandler.postDelayed(runnable, delay.toLong())
                }
            }}


    private fun animatText(cs: CharSequence) {
        charSequence=cs
        index=0
        textView.text = ""
        mHandler.removeCallbacks(runnable)
        mHandler.postDelayed(runnable, delay.toLong())

    }}


//Run handler
