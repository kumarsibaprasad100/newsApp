package com.example.mycomposeapp.views

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter

@Preview
@Composable
fun itemDetails(
    description: String?,
    author: String?,
    link: String?,
    image: String?,
    content: String?,
    context: Context
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize(1f)
            .background(Brush.horizontalGradient(listOf(Color.White, Color.White)))

    ) {
        Card(
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.onPrimary),
            elevation = CardDefaults.cardElevation(10.dp),
            shape = RoundedCornerShape(1.dp),
            modifier = Modifier
                .fillMaxSize()
        )
        {
            Column(Modifier.fillMaxHeight(1f)) {
                Image(
                    painter = rememberImagePainter(image),
                    contentDescription = "",
                    alignment = Alignment.TopStart,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .padding(4.dp)
                        .fillMaxWidth()
                        .fillMaxHeight(.3f)
                )
                Spacer(modifier = Modifier.padding(5.dp))
                Text(
                    text = author.toString(),
                    fontSize = 10.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold, modifier = Modifier
                        .padding(4.dp)
                        .background(Color.Red)
                        .padding(2.dp)
                )
                Spacer(modifier = Modifier.padding(5.dp))
                Text(
                    text = description.toString(),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .fillMaxWidth(1f)
                        .padding(4.dp)
                )
                Spacer(modifier = Modifier.padding(5.dp))
                Text(
                    text = content.toString(),
                    fontSize = 16.sp,
                    letterSpacing = 1.sp,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier
                        .fillMaxWidth(1f)
                        .padding(4.dp)
                )

                ClickableText(
                    text = getAString(link),
                    modifier = Modifier
                        .fillMaxWidth(1f)
                        .padding(4.dp),
                    onClick = {
                        openSite(link, context)
                    })

            }
        }
    }
}

fun openSite(link: String?, context: Context) {
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
    context.startActivity(intent)

}

fun getAString(link: String?): AnnotatedString {
    return AnnotatedString(
        link ?: "https://www.indiatoday.in",
        spanStyle = SpanStyle(
            color = Color.Blue,
            fontWeight = FontWeight.Medium,
            fontSize = 16.sp,
            textDecoration = TextDecoration.Underline
        )
    )
}
