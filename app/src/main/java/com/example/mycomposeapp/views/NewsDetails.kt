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
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
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
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.rememberImagePainter
import com.example.mycomposeapp.R

@Composable
fun itemDetails(
    description: String?,
    author: String?,
    link: String?,
    image: String?,
    content: String?,
    context: Context
) {
    /*without ConstraintLayout*/
    /*Box(
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
                val painter = rememberImagePainter(data = image?:"",
                    builder = {placeholder(R.drawable.news)
                        error(R.drawable.news)})
                Image(
                    painter = painter,
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
    }*/
    /*with ConstraintLayout*/
    ConstraintLayout(modifier = Modifier.fillMaxSize().padding(10.dp)) {
        val painter = rememberImagePainter(data = image?:"",
            builder = {placeholder(R.drawable.news)
                error(R.drawable.news)})
        val (image,source,desc,contents,url) = createRefs()
        Image(
            painter = painter,
            contentDescription = "",
            alignment = Alignment.TopStart,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .constrainAs(image){ top.linkTo(parent.top) }
                .fillMaxWidth()
                .clip(RoundedCornerShape(corner = CornerSize(8.dp)))
        )
        Text(
            text = author.toString(),
            fontSize = 10.sp,
            color = Color.White,
            fontWeight = FontWeight.Bold, modifier = Modifier.constrainAs(source){
                top.linkTo(image.bottom, margin = 10.dp)
                start.linkTo(parent.start)
            }
                .clip(RoundedCornerShape(corner = CornerSize(6.dp)))
                .background(Color.Red)
                .padding(vertical = 5.dp, horizontal = 5.dp)
        )
        Text(
            text = description.toString(),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .constrainAs(desc){
                    top.linkTo(source.bottom, margin = 10.dp)
                }
                .fillMaxWidth(1f)
        )
        Text(
            text = content.toString(),
            fontSize = 16.sp,
            letterSpacing = 1.sp,
            fontWeight = FontWeight.Normal,
            modifier = Modifier
                .constrainAs(contents){
                    top.linkTo(desc.bottom,margin = 10.dp)
                }
                .fillMaxWidth(1f)
        )

        ClickableText(
            text = getAString(link),
            modifier = Modifier
                .constrainAs(url){
                    top.linkTo(contents.bottom, margin = 10.dp)
                }
                .fillMaxWidth(1f),
            onClick = {
                openSite(link, context)
            })
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
