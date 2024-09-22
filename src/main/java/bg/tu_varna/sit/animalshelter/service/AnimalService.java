package bg.tu_varna.sit.animalshelter.service;

import bg.tu_varna.sit.animalshelter.model.Animal;
import bg.tu_varna.sit.animalshelter.repository.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnimalService {
    private final AnimalRepository animalRepository;

    @Autowired
    public AnimalService(AnimalRepository animalRepository){
        this.animalRepository = animalRepository;
    }

    public Animal addAnimal(Animal animal){
        return animalRepository.save(animal);
    }

    public List<Animal> showAllAnimals(){
        return animalRepository.findAll();
    }

    public List<Animal> getAnimalsByType(String type){
        return animalRepository.findByType(type);
    }

    public Optional<Animal> getAnimalById(Long id){
        return animalRepository.findById(id);
    }

    public void deleteAnimalById(Long id){
        animalRepository.deleteById(id);
    }

    public Animal updateAnimalById(Long id, Animal animal){
        return animalRepository.findById(id)
                .map(existingAnimal -> {
                    existingAnimal.setType(animal.getType());
                    existingAnimal.setName(animal.getName());
                    existingAnimal.setDate(animal.getDate());
                    return animalRepository.save(existingAnimal);
                })
                .orElseThrow(() -> new RuntimeException("Animal not found with id " + id));
    }
}


