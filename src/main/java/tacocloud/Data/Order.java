package tacocloud.Data;

import lombok.Data;
import org.hibernate.validator.constraints.CreditCardNumber;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name="Taco_Order")
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Date placedAt;
    @NotBlank(message = "Podanie imienia jest obowiązkowe.")
    private String name;
    @NotBlank(message = "Podanie ulicy jest obowiązkowe")
    private String street;
    @NotBlank(message = "Podanie miejscowości jest obowiązkowe")
    private String city;
    @NotBlank(message = "Podanie kodu pocztowego jest obowiązkowe")
    private String zip;
   // @CreditCardNumber(message = "Numer karty jest nie prawidłowy")
    private String ccNumber;
    @Pattern(regexp="^(0[1-9]|1[0-2])([\\/])([1-9][0-9])$",
            message="Format daty MM/YY")
    private String ccExpiration;
    @Digits(integer = 3, fraction = 0, message = "Nieprawidłowy kod CVV")
    private String ccCVV;

    @ManyToMany(targetEntity = Taco.class)
    private List<Taco> tacos = new ArrayList<>();

    public void addDesign(Taco design) {
        this.tacos.add(design);
    }

    @PrePersist
    void placedAt(){
        this.placedAt = new Date();
    }
}