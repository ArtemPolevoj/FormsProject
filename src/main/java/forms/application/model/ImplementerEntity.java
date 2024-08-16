package forms.application.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "исполнители")
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ImplementerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ФИО", unique = true)
    private String fullName;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String phone;
}
