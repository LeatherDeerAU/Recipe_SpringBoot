package com.example.demohyper.controller;


import com.example.demohyper.model.Recipe;
import com.example.demohyper.service.RecipeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.*;


@RestController
@RequestMapping("/api/recipe")
class RecipeController {
    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @PostMapping("/new")
    public Map addRecipe(@Valid @RequestBody Recipe recipe) {
        Recipe saved = recipeService.save(recipe);
        return Collections.singletonMap("id", saved.getId());
    }

    @GetMapping("/{id}")
    public Recipe getRecipe(@PathVariable("id") int id) {
        return recipeService.findRecipe(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteRecipe(@PathVariable("id") int id) {
        if (recipeService.deleteRecipe(id)) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateRecipe(@PathVariable("id") int id, @Valid @RequestBody Recipe newRecipe) {
        if (recipeService.updateRecipe(id, newRecipe)) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/search/", params = "category")
    public ResponseEntity<List<Recipe>> getRecipesByCategory(@RequestParam String category) {
        return ResponseEntity.ok().body(recipeService.findAllByCategory(category));
    }

    @GetMapping(value = "/search/", params = "name")
    public ResponseEntity<List<Recipe>> getRecipesNameContaining(@RequestParam String name) {
        return ResponseEntity.ok().body(recipeService.findAllByNameContaining(name));
    }
}


