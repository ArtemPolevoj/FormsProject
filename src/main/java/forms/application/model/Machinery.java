package forms.application.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "техника")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Machinery {
    @Id
    @Column(name = "серийный_номер", unique = true)
    private String sderialNumber;

    @Column(name = "машина", unique = true)
    private String drawingIndex;

    @Column(name = "хозяйственный_номер", unique = true)
    private Integer businessNumber;

    @Column(name = "наработка")
    private Integer operatingTime;
}
