package bg.tu_varna.sit.animalshelter.controller;


import bg.tu_varna.sit.animalshelter.model.Animal;
import bg.tu_varna.sit.animalshelter.service.AnimalService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RequestMapping("api/animals")
@RestController
public class AnimalController {
    private final AnimalService animalService;

    @Autowired
    public AnimalController(AnimalService animalService) {
        this.animalService = animalService;
    }

    @PostMapping
    public ResponseEntity<Animal> addAnimal(@Valid @NotNull @RequestBody Animal animal){
        Animal savedAnimal = animalService.addAnimal(animal);
        return new ResponseEntity<>(savedAnimal, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Animal>> showAllAnimals(){
        List<Animal> animals = animalService.showAllAnimals();
        return new ResponseEntity<>(animals, HttpStatus.OK);
    }

    @GetMapping(path = "Type/{type}")
    public ResponseEntity<List<Animal>> getAnimalByType(@PathVariable("type") String type){
        List<Animal> animals = animalService.getAnimalsByType(type);
        return new ResponseEntity<>(animals, HttpStatus.OK);
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<Animal> getAnimalById(@PathVariable("id") Long id){
        Optional<Animal> animal = animalService.getAnimalById(id);
        return animal.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<Void> deleteAnimalById(@PathVariable("id") Long id){
        Optional<Animal> animal = animalService.getAnimalById(id);
        if(animal.isPresent()){
            animalService.deleteAnimalById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<Animal> updateAnimalById(@PathVariable("id") Long id, @Valid @RequestBody Animal animal){
        try {
            Animal updatedAnimal = animalService.updateAnimalById(id, animal);
            return new ResponseEntity<>(updatedAnimal, HttpStatus.OK);
        } catch (RuntimeException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
