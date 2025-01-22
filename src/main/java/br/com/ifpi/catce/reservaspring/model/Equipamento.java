package br.com.ifpi.catce.reservaspring.model;

import jakarta.persistence.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.domain.Page;


@Entity
@Table(name = "equipamento")
@EqualsAndHashCode
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Equipamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "A descrição é obrigatória")
    private String descricao;

    @NotNull(message = "A quantidade disponivel é obrigatória")
    @Column(name = "quantidade_disponivel")
    @Min(value = 1, message = "Menor valor para quantidade disponível é 1")
    private int quantidadeDisponivel;

    @NotNull(message = "A quantidade total é obrigatória")
    @Column(name = "quantidade_total")
    @Min(value = 1, message = "Menor valor para quantidade total é 1")
    private int quantidadeTotal;

    @Override
    public String toString() {
        return this.descricao;
    }
}
