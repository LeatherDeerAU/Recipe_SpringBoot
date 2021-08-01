package com.example.demohyper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class TaskController {
    Recipe recipe = new Recipe();

    @PostMapping("/api/recipe")
    public String addRecipe(@RequestBody Recipe recipe) {
        this.recipe = recipe;
        return "recipe added";
    }

    @GetMapping("api/recipe")
    public Recipe getRecipe() {
        return this.recipe;
    }
}


@Data
@AllArgsConstructor
@NoArgsConstructor
class Recipe {
    String name;
    String description;
    String ingredients;
    String directions;
}
