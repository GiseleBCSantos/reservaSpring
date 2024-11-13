package br.com.ifpi.catce.reservaspring.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "data_reserva")
    @DateTimeFormat(pattern = "yyyy-MM-dd", iso = DateTimeFormat.ISO.DATE)
    @NotNull(message = "Data é obrigatório")
    private LocalDate dataReserva;

    @NotNull(message = "Solicitante é obrigatório")
    @ManyToOne
    private Funcionario funcionario;

    @ManyToOne
    private Equipamento equipamento = null;

    @ManyToOne
    private Espaco espaco = null;

}
