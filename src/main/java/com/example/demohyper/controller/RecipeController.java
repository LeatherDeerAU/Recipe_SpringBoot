package com.example.demohyper.controller;


import com.example.demohyper.model.Recipe;
import com.example.demohyper.service.RecipeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.*;


@RestController
class RecipeController {
    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @PostMapping("/api/recipe/new")
    public Map addRecipe(@Valid @RequestBody Recipe recipe) {
        Recipe saved = recipeService.save(recipe);
        return Collections.singletonMap("id", saved.getId());
    }

    @GetMapping("api/recipe/{id}")
    public ResponseEntity<Recipe> getRecipe(@PathVariable int id) {
        return recipeService.findRecipe(id);
    }

    @DeleteMapping("api/recipe/{id}")
    public ResponseEntity deleteRecipe(@PathVariable int id) {
        return recipeService.deleteRecipe(id);
    }

    @PutMapping("api/recipe/{id}")
    public ResponseEntity updateRecipe(@PathVariable int id, @Valid @RequestBody Recipe newRecipe) {
        return recipeService.updateRecipe(id, newRecipe);
    }

    @GetMapping(value = "api/recipe/search/", params = "category")
    public ResponseEntity<List<Recipe>> getRecipesByCategory(@RequestParam String category) {
        return ResponseEntity.ok().body(recipeService.findAllByCategory(category));
    }

    @GetMapping(value = "api/recipe/search/", params = "name")
    public ResponseEntity<List<Recipe>> getRecipesNameContaining(@RequestParam String name) {
        return ResponseEntity.ok().body(recipeService.findAllByNameContaining(name));
    }
}


