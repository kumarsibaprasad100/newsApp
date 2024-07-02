package com.example.mycomposeapp.views

import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.ImageLoader
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.mycomposeapp.R
import com.example.mycomposeapp.models.ArticlesItem
import com.example.mycomposeapp.ui.theme.lightRed
import com.example.mycomposeapp.viewModels.NewsViewModels

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun itemView(newsResponse: List<ArticlesItem?>?, context: Context, newsViewModels: NewsViewModels) {
    val s = ArrayList<ArticlesItem?>()
    if (newsResponse != null) {
        s.addAll(newsResponse)
    }
    var textState by remember { mutableStateOf("") }
    Column {
        TopAppBar(
            title = {
                Text(
                    text = "Today's News",
                    fontSize = 18.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            },
            colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Gray)
        )
        Row {
            TextField(value = "$textState",
                onValueChange = { textState = it },
                label = { Text("Search your news") },
                modifier = Modifier
                    .padding(4.dp)
                    .fillMaxWidth(.7f)
            )
            Button(
                onClick = { newsViewModels.fetchData(textState.toString()) },
                Modifier
                    .padding(4.dp)
                    .fillMaxWidth(1f)
                    .align(Alignment.CenterVertically)
            , colors = ButtonDefaults.buttonColors(Color.Gray), shape = MaterialTheme.shapes.small) {
                Text(text = "Search", fontWeight = FontWeight.Bold)

            }
        }
        LazyColumn(content = {
            items(s) {
                listView(items = it, context = context)
            }
        }, modifier = Modifier.background(Color.White))

    }
}

@Preview
@OptIn(ExperimentalCoilApi::class)
@Composable
private fun listView(items: ArticlesItem?, context: Context) {
    Card(
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.onPrimary),
        elevation = CardDefaults.cardElevation(10.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                val intent = Intent(context, DescActivity::class.java)
                intent.putExtra("description", items?.description)
                intent.putExtra("Author", items?.source?.name ?: "Unknown Author")
                intent.putExtra("image", items?.urlToImage)
                intent.putExtra("content", items?.content)
                intent.putExtra("link", items?.url)
                intent.flags = FLAG_ACTIVITY_NEW_TASK
                context.startActivity(intent)
            }
    ) {
        Row(
            modifier = Modifier
                .padding(8.dp)
        ) {
            val painter = rememberImagePainter(data = items?.urlToImage?:"",
                builder = {placeholder(R.drawable.news)
                    error(R.drawable.news)})
            Image(
                painter = painter,
                contentDescription = "quotes",
                alignment = Alignment.TopStart,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(90.dp)
                    .clip(RoundedCornerShape(corner = CornerSize(6.dp)))

            )
            Spacer(modifier = Modifier.padding(4.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = items?.title.toString(),
                    color = Color.White,
                    fontSize = 14.sp,
                    maxLines = 2,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier
                        .clip(RoundedCornerShape(corner = CornerSize(6.dp)))
                        .shadow(2.dp)
                        .fillMaxWidth(1f)
                        .padding(0.dp, 0.dp, 6.dp, 8.dp)
                        .background(color = lightRed)
                        .padding(4.dp)

                )

                Text(
                    text = buildAnnotatedString {
                        append("Author: ")
                        append(items?.author ?: "Unknown Author")
                        append("  Published on: ")
                        append(items?.publishedAt)
                    },
                    color = Color.Gray,
                    fontSize = 8.sp,
                    maxLines = 1,
                    fontWeight = FontWeight.Thin,
                    modifier = Modifier
                        .padding(0.dp, 0.dp, 0.dp, 8.dp)

                )
                Box(
                    modifier = Modifier
                        .padding(0.dp, 0.dp, 6.dp, 0.dp)
                        .background(Color.Gray)
                        .height(1.dp)
                        .fillMaxWidth(1f)

                )
                Text(
                    text = items?.content.toString(),
                    color = Color.Black,
                    fontSize = 14.sp,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier.padding(0.dp, 4.dp, 6.dp, 0.dp)
                )

            }
        }
    }
}

