package com.unitmock.food2forkkmm.datasource.cache

import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver

actual class DriverFactory {
    actual fun createDiver(): SqlDriver {
        return NativeSqliteDriver(RecipeDatabase.Schema, "recipe.db")
    }
}