package com.example.mycomposeapp.views

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DescActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val description = intent.getStringExtra("description")
        val author = intent.getStringExtra("Author")
        val link = intent.getStringExtra("link")
        val content = intent.getStringExtra("content")
        val image = intent.getStringExtra("image")
        setContent {
            showNewsDesc(description, author,link,image,content,this)
        }
    }
}

@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun showNewsDesc(
    description: String?,
    author: String?,
    link: String?,
    image: String?,
    content: String?,
    descActivity: DescActivity
) {
    val context = LocalContext.current

    BackHandler {
        (context as? Activity)?.finish()
    }
    Column {
        TopAppBar(
            title = {
                Text(
                    text = "Detailed News",
                    fontSize = 18.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            },
            colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Gray),
            navigationIcon = {
                IconButton(onClick = {(context as? Activity)?.finish()}) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.White
                    )

                }
            }
        )
        itemDetails(description, author,link,image,content,descActivity)
    }

}