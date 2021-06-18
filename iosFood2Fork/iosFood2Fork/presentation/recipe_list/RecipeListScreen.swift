//
//  RecipeListScreen.swift
//  iosFood2Fork
//
//  Created by David Cruz Anaya on 17/06/2021.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI
import shared

struct RecipeListScreen: View {
    
    // dependencies
    private let networkModule: NetworkModule
    private let cacheModule: CacheModule
    private let searchRecipeModule: SearchRecipesModule
    
    @ObservedObject var viewModel: RecipeListViewModel
    
    init(networkModule: NetworkModule, cacheModule: CacheModule) {
        self.networkModule = networkModule
        self.cacheModule = cacheModule
        self.searchRecipeModule = SearchRecipesModule(networkModule: self.networkModule, cacheModule: self.cacheModule)
        self.viewModel = RecipeListViewModel(searchRecipes: searchRecipeModule.searchRecipes, foodCategoryUtil: FoodCategoryUtil())
    }
    
    var body: some View {
        VStack {
            Text("\(viewModel.state.page)")
            Button(action: {
                viewModel.updateState(page: Int(viewModel.state.page + 1))
            }, label: {Text("Increment Page")})
        }
    }
}

//struct RecipeListScreen_Previews: PreviewProvider {
//    static var previews: some View {
//        RecipeListScreen()
//    }
//}
