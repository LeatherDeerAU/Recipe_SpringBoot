package com.example.demohyper;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;



@RestController
class TaskController {
    private final List<Recipe> recipes = new ArrayList<>();
    int id = 1;

    @PostMapping("/api/recipe/new")
    public Map addRecipe(@RequestBody Recipe recipe) {
        recipes.add(recipe);
        return Collections.singletonMap("id:", id++);
    }

    @GetMapping("api/recipe/{id}")
    public Recipe getRecipe(@PathVariable int id) {
        if (id > recipes.size() || id < 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return recipes.get(id - 1);
    }

}


@Data
@AllArgsConstructor
@NoArgsConstructor
class Recipe {
    String name;
    String description;
    String[] ingredients;
    String[] directions;
}


