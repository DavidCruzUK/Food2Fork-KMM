//
//  RecipeListViewModel.swift
//  iosFood2Fork
//
//  Created by David Cruz Anaya on 17/06/2021.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI
import shared

class RecipeListViewModel: ObservableObject {
    
    let searchRecipes: SearchRecipes
    let foodCategoryUtil: FoodCategoryUtil
    
    @Published var state: RecipeListState = RecipeListState()
    
    init(searchRecipes: SearchRecipes, foodCategoryUtil: FoodCategoryUtil) {
        self.searchRecipes = searchRecipes
        self.foodCategoryUtil = foodCategoryUtil
    }
    
    func onTriggerEvent(stateEvent: RecipeListEvent) {
        switch stateEvent {
        case is RecipeListEvent.LoadRecipes:
            doNothing()
        case is RecipeListEvent.NewSearch:
            doNothing() 
        case is RecipeListEvent.NextPage:
            doNothing()
        case is RecipeListEvent.OnUpdateQuery:
            doNothing()
        case is RecipeListEvent.OnSelectCategory:
            doNothing()
        case is RecipeListEvent.OnRemoveHeadMessageFromQueue:
            doNothing()
        default:
            doNothing()
        }
    }
    
    func doNothing() {
        
    }
    
    func updateState(isLoading: Bool? = nil, page: Int? = nil, query: String? = nil, queue: Queue<GenericMessageInfo>? = nil) {
        let currentState = (self.state.copy() as! RecipeListState)
        self.state = self.state.doCopy(
            isLoading: isLoading ?? currentState.isLoading,
            page: Int32(page ?? Int(currentState.page)),
            query: query ?? currentState.query,
            selectedCategory: currentState.selectedCategory,
            recipes: currentState.recipes,
            queue: queue ?? currentState.queue)
        
        
    }
}
