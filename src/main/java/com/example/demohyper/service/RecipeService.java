package com.example.demohyper.service;

import com.example.demohyper.model.Recipe;
import com.example.demohyper.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecipeService {
    private final RecipeRepository recipeRepository;

    @Autowired
    public RecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public Recipe findRecipeByID(int id) {
        return recipeRepository.findRecipeById(id);
    }

    public Recipe save(Recipe toSave) {
        return recipeRepository.save(toSave);
    }

    public void deleteRecipeByID(int id) {
        recipeRepository.deleteById(id);
    }

}
