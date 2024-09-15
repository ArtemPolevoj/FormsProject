package forms.application.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.Set;

@Entity
@Table(name = "типы_машин")
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class TypeMachineEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "тип", unique = true)
    @NotNull
    private String typeMachine;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "вопросы_типы_машин",
            joinColumns = @JoinColumn(name = "id_тип_машины"),
            inverseJoinColumns = @JoinColumn(name = "id_вопроса")
    )
    @Fetch(value = FetchMode.SUBSELECT)
    @JsonIgnore
    private Set<QuestionEntity> questions;
}