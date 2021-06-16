package com.unitmock.food2forkkmm.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import com.unitmock.food2forkkmm.android.presentation.navigation.Navigation
import com.unitmock.food2forkkmm.datasource.network.KtorClientFactory
import com.unitmock.food2forkkmm.datasource.network.RecipeServiceImpl
import com.unitmock.food2forkkmm.datasource.network.model.RecipeDto
import com.unitmock.food2forkkmm.datasource.network.toRecipe
import com.unitmock.food2forkkmm.domain.util.DatetimeUtil
import dagger.hilt.android.AndroidEntryPoint
import io.ktor.client.request.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @ExperimentalStdlibApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { Navigation() }
    }

    companion object {
        const val TOKEN = "Token 9c8b06d329136da358c2d00e76946b0111ce2c48"
        const val BASE_URL = "https://food2fork.ca/api/recipe"
    }
}
