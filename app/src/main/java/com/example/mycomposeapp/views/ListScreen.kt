package com.example.mycomposeapp.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mycomposeapp.R

data class Catagory(val name:String,val job:String,val image:Int)

fun getCatagoryList(): MutableList<Catagory>{
    val list = mutableListOf<Catagory>()
    list.add(Catagory("Sibaprasad kabat","Doctor", R.drawable.logo_initials))
    list.add(Catagory("Raj kumar","Engineer", R.drawable.logo_initials))
    list.add(Catagory("Ram Dash","Teacher", R.drawable.logo_initials))
    list.add(Catagory("Rakesh kumar","Student", R.drawable.logo_initials))
    list.add(Catagory("Sibaprasad kabat","Doctor", R.drawable.logo_initials))
    list.add(Catagory("Raj kumar","Engineer", R.drawable.logo_initials))
    list.add(Catagory("Ram Dash","Teacher", R.drawable.logo_initials))
    list.add(Catagory("Rakesh kumar","Student", R.drawable.logo_initials))
    list.add(Catagory("Sibaprasad kabat","Doctor", R.drawable.logo_initials))
    list.add(Catagory("Raj kumar","Engineer", R.drawable.logo_initials))
    list.add(Catagory("Ram Dash","Teacher", R.drawable.logo_initials))
    list.add(Catagory("Rakesh kumar","Student", R.drawable.logo_initials))
    list.add(Catagory("Sibaprasad kabat","Doctor", R.drawable.logo_initials))
    list.add(Catagory("Raj kumar","Engineer", R.drawable.logo_initials))
    list.add(Catagory("Ram Dash","Teacher", R.drawable.logo_initials))
    list.add(Catagory("Rakesh kumar","Student", R.drawable.logo_initials))
    return list
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(heightDp = 400)
@Composable
fun previewFun(){
    Column() {
        TopAppBar(title = { Text(text = "Category", color = Color.Red , fontWeight = FontWeight.Bold ) }, colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary
        ))
        LazyColumn {
            items(getCatagoryList()){
                itemView(name = it.name, job = it.job, image =it.image )
            }
        }
    }
}

@Composable
fun itemView(name: String, job: String, image: Int) {
   Card(
       elevation = CardDefaults.cardElevation(),
       modifier = Modifier
           .fillMaxWidth()
           .padding(8.dp))
   {
       Row(verticalAlignment = Alignment.CenterVertically,
           modifier = Modifier.padding(8.dp)) {
           Image(painter = painterResource(id = image), contentDescription ="",
               modifier = Modifier
                   .size(40.dp)
                   .padding(8.dp))
           dataItem(name,job)
       }
   }
}

@Composable
public fun dataItem(name: String, job: String) {
    Column() {
        Text(text = name, fontWeight = FontWeight.Bold)
        Text(text = job, fontWeight = FontWeight.Thin)
    }
}