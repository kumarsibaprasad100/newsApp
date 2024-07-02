package com.example.mycomposeapp.views

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import com.example.mycomposeapp.models.ArticlesItem
import com.example.mycomposeapp.utils.Resource
import com.example.mycomposeapp.viewModels.NewsViewModels
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var newsViewModels: NewsViewModels
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        newsViewModels = ViewModelProvider(this).get(NewsViewModels::class.java)
        newsViewModels.fetchData("Indian")
        setContent {
            setData(newsViewModels)
        }

    }

    @Composable
    fun startLoader() {
        CircularProgressIndicator(
            modifier = Modifier
                .width(40.dp)
                .padding(8.dp),
            color = MaterialTheme.colorScheme.secondary
        )
    }

    @Composable
    fun setData(newsViewModels: NewsViewModels) {
        val dataState by newsViewModels.data.observeAsState(initial = Resource.Loading)
        when (dataState) {
            is Resource.Success -> {
                val newsdata = (dataState as Resource.Success).data
                if (newsdata != null) {
                    itemView(newsResponse = newsdata, applicationContext, newsViewModels)
                }
            }

            is Resource.Error -> {
                Toast.makeText(applicationContext, (dataState as Resource.Error).message, Toast.LENGTH_SHORT)
            }

            Resource.Loading -> startLoader()
        }


    }

}
/*

@Preview(showBackground = true, widthDp = 80, heightDp = 120)
@Composable
fun previewFun() {
    Text(
        text = "Himanshu the Great",
        color = Color.Blue,
        fontSize = 15.sp,
        fontStyle = FontStyle.Italic,
        fontWeight = FontWeight.Bold
    )

}

@Preview(showBackground = true, widthDp = 80, heightDp = 120)
@Composable
fun previewImage() {
    Image(
        painter = painterResource(id = R.drawable.ic_launcher_background),
        contentDescription = "image",
        contentScale = ContentScale.Fit,
        colorFilter = ColorFilter.tint(Color.Green)
    )
}

@Preview(showBackground = true, widthDp = 150, heightDp = 80)
@Composable
fun previewButton() {
    Button(onClick = { }, enabled = true) {
        Image(
            painter = painterResource(id = androidx.core.R.drawable.ic_call_answer),
            contentDescription = "image",
            contentScale = ContentScale.Fit,
            colorFilter = ColorFilter.tint(Color.Magenta)
        )

        Text(text = "click me", color = Color.Green)
    }

}
@Preview(showBackground = true, widthDp = 300, heightDp = 500)
@Composable
fun PreviewFun(){
    TextField(value = "Hi siba", onValueChange ={},
        label = { Text(text = "Enter name")}
    )
    //circularImage()
}

@Composable
fun profile(){
    Row(Modifier.padding(20.dp)) {
        Image(painter = painterResource(id = R.drawable.logo_initials),
            contentDescription ="image",
            Modifier.size(40.dp))
        Column {
            Text(
                text = "hi Siba", color = Color.Blue,
                fontSize = 20.sp,
                fontStyle = FontStyle.Italic,
                )
            Text(text = "you are a good boy",color = Color.Red,
                fontSize = 20.sp, fontStyle = FontStyle.Italic)
        }
    }
}

@Composable
fun circularImage(){
  Image(painter = painterResource(id = R.drawable.logo_initials),
      contentScale = ContentScale.Crop,
      modifier = Modifier.size(80.dp).clip(CircleShape).border(4.dp, Color.LightGray, CircleShape),
      contentDescription ="" )
}


@Composable
fun textInput(){
    var state = remember {
        mutableStateOf("")
    }
    TextField(value = state.value, onValueChange ={
        state.value = it
    },
        label = { Text(text = "Enter name")},
        enabled = true
    )
}

*/

/*@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun callm() {
    var count : MutableState<Int> = rememberSaveable{ mutableStateOf(0)}
    Column {
        TopAppBar(title = {Text(text = "data", color = Color.Black , fontWeight = FontWeight.Bold)}, colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary
        ))
        nCount(count.value, { count.value++ })
    }

}

@Composable
private fun nCount(count: Int, add: () -> Int) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "total $count times")
        Button(onClick = {
            add()
            Log.i("TAG", "call: clicked ")
        }) {
            Text(text = "click me")
        }
    }
}*/