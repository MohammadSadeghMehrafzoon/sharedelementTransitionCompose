package ir.misterdeveloper.sharedelementtransition

import androidx.annotation.DrawableRes
import kotlin.random.Random

data class Pizza (
    val id: Int = Random.nextInt(),
    val name: String = "",
    val description: String = "",
    @DrawableRes val image: Int
)