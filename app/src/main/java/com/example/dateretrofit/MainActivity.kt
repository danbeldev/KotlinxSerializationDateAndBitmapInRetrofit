package com.example.dateretrofit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.dateretrofit.date.model.Autor
import com.example.dateretrofit.date.repository.ApiAutorRepository
import com.example.dateretrofit.date.retrofitInst.RetrofitInst
import com.example.dateretrofit.ui.theme.DateRetrofitTheme
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.serialization.ExperimentalSerializationApi

@ExperimentalSerializationApi
class MainActivity : ComponentActivity() {

    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val apiAutorRepository = ApiAutorRepository(autorApi = RetrofitInst.apiAutor)
        val mainViewModelFactory = MainViewModelFactory(apiAutorRepository = apiAutorRepository)
        mainViewModel = ViewModelProvider(this, mainViewModelFactory).get(MainViewModel::class.java)

        setContent {
            var autors by remember { mutableStateOf(listOf<Autor>()) }

            lifecycleScope.launchWhenStarted {
                mainViewModel.responseAutors.onEach {
                    autors = it
                }.collect()
            }

            LazyColumn(content = {
                items(autors){ item ->
                    Text(
                        text = item.middleName.toString(),
                        modifier = Modifier.padding(5.dp)
                    )

                    Text(
                        text = item.birthDate.toString(),
                        modifier = Modifier.padding(5.dp)
                    )

                    item.photo?.let {
                        Image(
                            bitmap = item.photo.asImageBitmap(),
                            contentDescription = null
                        )
                    }
                }
            })
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    DateRetrofitTheme {
        Greeting("Android")
    }
}