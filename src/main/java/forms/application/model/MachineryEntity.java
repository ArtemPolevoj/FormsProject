package forms.application.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "техника")
@Getter
@Setter
@EqualsAndHashCode(of = "serialNumber")
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class MachineryEntity {
    @Id
    @Column(name = "серийный_номер", unique = true)
    private String serialNumber;

    @Column(name = "производитель")
    @NotNull
    private String manufacturer;

    @Column(name = "модель", unique = true)
    @NotNull
    private String model;

    @Column(name = "хозяйственный_номер", unique = true)
    @NotNull
    private String businessNumber;

    @Column(name = "наработка")
    @NotNull
    private String operatingTime;

    @Column(name = "организация", unique = true)
    @NotNull
    private String organization;

    @Column(name = "подразделение", unique = true)
    @NotNull
    private String division;

    @Column(name = "тип", unique = true)
    @NotNull
    private String type;
}
