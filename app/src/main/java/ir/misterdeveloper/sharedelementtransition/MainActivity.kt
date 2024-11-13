package ir.misterdeveloper.sharedelementtransition

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ir.misterdeveloper.sharedelementtransition.ui.theme.SharedElementTransitionTheme

@ExperimentalSharedTransitionApi
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val pizzaList = remember {
                mutableStateListOf(
                    Pizza(
                        name = "mixed pizza",
                        description = "The combination of sausage and sausage in pizza was probably invented by us Iranians.",
                        image = R.drawable.mixed_pizza
                    ),
                    Pizza(
                        name = "beef and mushroom pizza",
                        description = "Meat and mushroom pizza is one of the most popular types of pizza in Iran.",
                        image = R.drawable.beef_mushroomm
                    )
                )
            }

            var selectedPizza by remember {
                mutableStateOf<Pizza?>(null)
            }

            var showDetails by remember {
                mutableStateOf(false)
            }

            SharedTransitionLayout {
                AnimatedContent(
                    targetState = showDetails,
                    label = "transition"
                ) { targetState ->
                    if (!targetState) {
                        MainContent(
                            pizzaList = pizzaList,
                            onShowDetails = {
                                selectedPizza = it
                                showDetails = true
                            },
                            animatedVisibilityScope = this@AnimatedContent,
                            sharedTransitionScope = this@SharedTransitionLayout
                        )
                    } else {
                        selectedPizza?.let { pizza ->
                            DetailsContent(
                                pizza = pizza,
                                onBack = {
                                    showDetails = false
                                },
                                animatedVisibilityScope = this@AnimatedContent,
                                sharedTransitionScope = this@SharedTransitionLayout
                            )
                        }
                    }
                }

            }
        }
        }
    }


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SharedElementTransitionTheme {
        Greeting("Android")
    }
}