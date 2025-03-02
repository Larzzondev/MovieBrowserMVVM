package com.marlhex.moviebrowsermvvm

import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.marlhex.moviebrowsermvvm.ui.theme.MovieBrowserMVVMTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieBrowserMVVMTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    val api = MovieApi()
                    val factory = MovieViewModelFactory(api)
                    val viewModel: MovieViewModel = ViewModelProvider(this, factory).get(MovieViewModel::class.java)

                    NavHost(navController = navController, startDestination = "movieList") {
                        composable("movieList") {
                            MovieListScreen(
                                viewModel = viewModel,
                                onMovieClick = { title, imageUrl ->
                                    val encodedUrl = Uri.encode(imageUrl)
                                    navController.navigate("movieDetail/$title/$encodedUrl")
                                }
                            )
                        }
                        composable("movieDetail/{title}/{imageUrl}") { backStackEntry ->
                            val title = backStackEntry.arguments?.getString("title") ?: "Unknown Movie"
                            val imageUrl = backStackEntry.arguments?.getString("imageUrl") ?: ""
                            MovieDetailScreen(title = title, imageUrl = imageUrl)
                        }
                    }
                }
            }
        }
    }
}