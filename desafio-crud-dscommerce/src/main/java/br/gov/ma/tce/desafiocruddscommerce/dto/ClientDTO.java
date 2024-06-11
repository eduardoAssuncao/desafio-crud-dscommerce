package br.gov.ma.tce.desafiocruddscommerce.dto;

import br.gov.ma.tce.desafiocruddscommerce.entities.Client;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
public class ClientDTO{

    private Long id;
    @Size(min = 3, max = 80, message = "Nome precisar ter de 3 a 80 caracteres")
    @NotBlank(message = "Campo requerido")
    private String name;
    @Size(min = 11, max = 11, message = "Campo precisa ter 11 números")
    @NotBlank( message = "Campo requerido")
    private String cpf;
    @Positive(message = "A renda precisa ser positiva")
    private Double income;
    @NotNull(message = "Campo requerido")
    @Past
    private LocalDate birthDate;
    @NotNull(message = "Campo requerido")
    private Integer children;

    public ClientDTO(Client client){
        this.id = client.getId();
        this.name = client.getName();
        this.cpf = client.getCpf();
        this.income = client.getIncome();
        this.birthDate = client.getBirthDate();
        this.children = client.getChildren();
    }
}
