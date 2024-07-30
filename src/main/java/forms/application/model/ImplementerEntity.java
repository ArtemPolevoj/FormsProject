package forms.application.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "исполнители")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImplementerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "фамилия")
    @NotNull
    private String surname;

    @Column(name = "имя")
    @NotNull
    private String name;

    @Column(name = "отчество")
    @NotNull
    private String patronymic;

    private String email;

    private String phone;
}
