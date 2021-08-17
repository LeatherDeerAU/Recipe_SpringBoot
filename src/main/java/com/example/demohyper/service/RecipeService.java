package com.example.demohyper.service;

import com.example.demohyper.model.Recipe;
import com.example.demohyper.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class RecipeService {
    private final RecipeRepository recipeRepository;

    @Autowired
    public RecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public Recipe findRecipe(int id) {
        Optional<Recipe> opt = Optional.ofNullable(recipeRepository.findRecipeById(id));
        if (opt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        } else {
            return opt.get();
        }
    }

    public Recipe save(Recipe toSave) {
        return recipeRepository.save(toSave);
    }

    public boolean deleteRecipe(int id) {
        Optional<Recipe> opt = Optional.ofNullable(recipeRepository.findRecipeById(id));
        if (opt.isPresent()) {
            recipeRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public boolean updateRecipe(int id, Recipe newRecipe) {
        Optional<Recipe> optionalRecipe = Optional.ofNullable(recipeRepository.findRecipeById(id));
        if (optionalRecipe.isPresent()) {
            newRecipe.setId(id);
            recipeRepository.save(newRecipe);
            return true;
        }
        return false;
    }

    public List<Recipe> findAllByCategory(String category) {
        return recipeRepository.findAllByCategoryIgnoreCaseOrderByDateDesc(category);
    }

    public List<Recipe> findAllByNameContaining(String name) {
        return recipeRepository.findAllByNameContainingIgnoreCaseOrderByDateDesc(name);
    }


}
