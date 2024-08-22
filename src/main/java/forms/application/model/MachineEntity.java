package forms.application.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
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
@Builder
public class MachineEntity {
    @Id
    @Column(name = "серийный_номер", unique = true)
    private String serialNumber;

    @Column(name = "производитель")
    private String manufacturer;

    @ManyToOne
    @JoinColumn(name = "id_модели", unique = true)
    private ModelEntity model;

    @Column(name = "хозяйственный_номер", unique = true)
    private Integer businessNumber;

    @Column(name = "наработка")
    private Integer operatingTime;

    @ManyToOne
    @JoinColumn(name = "id_организации")
    private OrganizationEntity organization;

    @ManyToOne
    @JoinColumn(name = "id_подразделения")
    private DivisionEntity division;
}
