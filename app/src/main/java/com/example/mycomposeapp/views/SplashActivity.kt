package com.example.mycomposeapp.views

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.lifecycleScope
import coil.compose.rememberImagePainter
import com.example.mycomposeapp.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay

@AndroidEntryPoint
class SplashActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            splashLogo()
        }
        gotoMain()
    }

    private fun gotoMain() {
        val intent = Intent(this,MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        val mDelay = 3000L
        lifecycleScope.launchWhenCreated {
            delay(mDelay)
            startActivity(intent)
            finish()
        }

    }
}


@Composable
fun splashLogo(){
   ConstraintLayout(modifier = Modifier.fillMaxSize().background(Color.White)) {
          val (logoImage, text) = createRefs()
       Image(painter = painterResource(id = R.drawable.news), contentDescription ="logoImage", modifier = Modifier.constrainAs(logoImage){
           top.linkTo(parent.top)
           bottom.linkTo(parent.bottom)
           end.linkTo(parent.end)
           start.linkTo(parent.start)
       }.size(100.dp) )

       Text(text = "Daily News", color = Color.Black, fontSize = 20.sp, fontWeight = FontWeight.Bold ,modifier = Modifier.constrainAs(text){
            top.linkTo(logoImage.bottom)
           end.linkTo(logoImage.end)
           start.linkTo(logoImage.start)
       })
   }
}


