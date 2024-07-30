package forms.application.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "техника")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MachineryEntity {
    @Id
    @Column(name = "серийный_номер", unique = true)
    private String sderialNumber;

    @Column(name = "производитель")
    @NotNull
    private String manufacturer;

    @Column(name = "модель", unique = true)
    @NotNull
    private String model;

    @Column(name = "хозяйственный_номер", unique = true)
    @NotNull
    private Integer businessNumber;

    @Column(name = "наработка")
    @NotNull
    private Integer operatingTime;
}
