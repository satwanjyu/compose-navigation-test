package io.github.satwanjyu.compose_navigation

import android.os.Bundle
import android.os.Parcelable
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import dev.olshevski.navigation.reimagined.NavBackHandler
import dev.olshevski.navigation.reimagined.NavHost
import dev.olshevski.navigation.reimagined.navigate
import dev.olshevski.navigation.reimagined.rememberNavController
import io.github.satwanjyu.compose_navigation.ui.theme.ComposenavigationTheme
import kotlinx.parcelize.Parcelize

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposenavigationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavHostScreen()
                }
            }
        }
    }
}

sealed class Screen : Parcelable {

    @Parcelize
    object First : Screen()

    @Parcelize
    data class Second(val id: Int) : Screen()

    @Parcelize
    data class Third(val text: String) : Screen()
}

@Composable
fun NavHostScreen() {
    val navController = rememberNavController<Screen>(
        startDestination = Screen.First
    )

    NavBackHandler(navController)

    NavHost(navController) { screen ->
        when (screen) {
            Screen.First -> Column {
                Text("First screen")
                Button(onClick = {
                    navController.navigate(Screen.Second(id = 42))
                }) {
                    Text("To Second screen")
                }
            }

            is Screen.Second -> Column {
                Text("Second screen: ${screen.id}")
                Button(onClick = {
                    navController.navigate(Screen.Third(text = "Hello"))
                }) {
                    Text("To Third screen")
                }
            }

            is Screen.Third -> {
                Text("Third screen: ${screen.text}")
            }
        }
    }
}
