package tacocloud.Data;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Data
public class Taco {

    private Long id;
    private Date createdAt;
    @Size(min=5, message = "Nazwa musi się składać z min. pięciu znaków!!!")
    private String name;
    @NotNull(message = "Musisz wybrać przynajmniej jeden składnik!")
    private List<String> ingredients; //kiedyś był String
}

