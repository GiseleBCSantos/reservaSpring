package br.com.ifpi.catce.reservaspring.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Espaco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Descrição não pode estar vazia")
    @Size(min = 3, max = 100, message = "Tamanho deve ser entre 3 e 100 caracteres")
    private String descricao;

    private boolean status;

    @Override
    public String toString() {
        return this.descricao;
    }
}
