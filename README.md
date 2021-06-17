# Food2Fork

## Dependencies:
* Need Java 11 *
* Android Arctic Fox 2020.3.1 Canary 8 *


### Example of GenericMessageInfo:
```kotlin
val messageInfoBuilder = GenericMessageInfo.Builder()
    .id(UUID.randomUUID().toString())
    .title("seriously?")
    .uiComponentType(UIComponentType.Dialog)
    .description("That's isn't clever")
    .positive(
        PositiveAction(
            positiveBtnTxt = "COOL",
            onPositiveAction = {
                state.value = state.value.copy(query = "cake")
                onTriggerEvent(RecipeListEvent.NewSearch)
            }
        )
    )
    .negative(
        NegativeAction(
            negativeBtnTxt = "Ouch!",
            onNegativeAction = {
                state.value = state.value.copy(query = "Cookies")
                onTriggerEvent(RecipeListEvent.NewSearch)
            }
        )
    )
appendToMessageQueue(messageInfoBuilder.build())
```