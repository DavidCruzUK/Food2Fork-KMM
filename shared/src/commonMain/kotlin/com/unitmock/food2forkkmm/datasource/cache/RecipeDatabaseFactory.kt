package com.unitmock.food2forkkmm.datasource.cache

import com.squareup.sqldelight.db.SqlDriver
import com.unitmock.food2forkkmm.domain.model.Recipe
import com.unitmock.food2forkkmm.domain.util.DatetimeUtil

class RecipeDatabaseFactory(
    private val driverFactory: DriverFactory
) {
    fun createDatabase(): RecipeDatabase {
        return RecipeDatabase(driverFactory.createDiver())
    }
}

expect class DriverFactory {
    fun createDiver(): SqlDriver
}

fun Recipe_Entity.toRecipe(): Recipe {
    val datetimeUtil = DatetimeUtil()
    return Recipe(
        id = id.toInt(),
        title = title,
        publisher = publisher,
        featuredImage = featured_image,
        rating = rating.toInt(),
        sourceUrl = source_url,
        ingredients = ingredients.convertIngredientsToList(),
        dateAdded = datetimeUtil.toLocalDate(date_added),
        dateUpdated = datetimeUtil.toLocalDate(date_updated),
    )
}

fun List<Recipe_Entity>.toRecipeList(): List<Recipe> {
    return map { it.toRecipe() }
}

fun Recipe.toRecipeEntity(): Recipe_Entity {
    val datetimeUtil = DatetimeUtil()
    return Recipe_Entity(
        id = id.toLong(),
        title = title,
        publisher = publisher,
        featured_image =featuredImage,
        rating = rating.toLong(),
        source_url = sourceUrl,
        ingredients = ingredients.convertIngredientsToString(),
        date_added = datetimeUtil.toEpochMilliseconds(dateAdded),
        date_updated = datetimeUtil.toEpochMilliseconds(dateUpdated),
    )
}

fun List<String>.convertIngredientsToString(): String {
    val ingredientsString = StringBuilder()
    for (ingredient in this) {
        ingredientsString.append("$ingredient,")
    }
    return ingredientsString.toString()
}

fun String.convertIngredientsToList(): List<String> = split(",")