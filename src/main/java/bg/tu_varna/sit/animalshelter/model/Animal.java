package bg.tu_varna.sit.animalshelter.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

@Entity
@Table(name = "animals")
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // Използвайте, ако искате JPA да генерира ID
    private Long id;

    @NotBlank(message = "Type is mandatory")
    private String type;

    @NotBlank(message = "Name is mandatory")
    private String name;

    @NotBlank(message = "Date is mandatory")
    private String date;

    // Конструктор без аргументи (необходим за JPA)
    public Animal() {
    }

    public Animal(@JsonProperty("id") Long id,
                  @JsonProperty("type") String type,
                  @JsonProperty("name") String name,
                  @JsonProperty("date") String date) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.date = date;
    }

    // Гетъри и сетъри
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type){
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date){
        this.date = date;
    }
}

