package com.unitmock.food2forkkmm.datasource.cache

import android.content.Context
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver

actual class DriverFactory(
    private val context: Context
) {
    actual fun createDiver(): SqlDriver {
        return AndroidSqliteDriver(RecipeDatabase.Schema, context, "recipe.db")
    }
}