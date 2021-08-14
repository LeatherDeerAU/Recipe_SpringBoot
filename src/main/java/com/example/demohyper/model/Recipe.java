package com.example.demohyper.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    Integer id;

    @NotBlank
    String name;

    @NotBlank
    String description;

    @NotBlank
    String category;

    @UpdateTimestamp
    LocalDateTime date;

    @Size(min = 1)
    @ElementCollection
    @NotNull
    List<String> ingredients;

    @Size(min = 1)
    @ElementCollection
    @NotNull
    List<String> directions;
}
