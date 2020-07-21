package tacocloud.Data;

import lombok.Data;
import org.springframework.jmx.export.annotation.ManagedAttribute;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class Taco {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Size(min=5, message = "Nazwa musi się składać z min. pięciu znaków!!!")
    private String name;

    private Date createdAt;

    @ManyToMany(targetEntity = Ingredient.class)
    @Size(min=1 , message = "Muisz wybrac przynajmniej jeden składnik!")
    private List<Ingredient> ingredients;

    @PrePersist
    void createdAt() {
        this.createdAt = new Date();
    }
}

