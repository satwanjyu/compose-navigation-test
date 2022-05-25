package io.github.satwanjyu.compose_navigation

import android.os.Bundle
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
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import io.github.satwanjyu.compose_navigation.destinations.NextScreenDestination
import io.github.satwanjyu.compose_navigation.destinations.WelcomeScreenDestination
import io.github.satwanjyu.compose_navigation.ui.theme.ComposenavigationTheme

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
                    DestinationsNavHost(navGraph = NavGraphs.root)
                }
            }
        }
    }
}

@RootNavGraph(start = true)
@Destination
@Composable
fun WelcomeScreen(
    navigator: DestinationsNavigator
) {
    Column {
        Text(text = "Welcome")
        Button(onClick = { navigator.navigate(NextScreenDestination()) }) {
            Text(text = "To Next")
        }
    }
}

@Destination
@Composable
fun NextScreen(
    navigator: DestinationsNavigator
) {
    Column {
        Text(text = "Next")
        Button(onClick = { navigator.navigate(WelcomeScreenDestination()) }) {
            Text(text = "To Welcome")
        }
    }
}