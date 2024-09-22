package bg.tu_varna.sit.animalshelter.repository;


import bg.tu_varna.sit.animalshelter.model.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {
    List<Animal> findByType(String type);
}

