package com.gusSystem.crud.api.documents;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.Set;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Client {

    @Id
    private String id;

    @NotEmpty(message = "Nome não pode ser vazio")
    private String name;

    @Email(message = "Email inválido!")
    @NotEmpty(message = "Nome não pode ser vazio")
    private String email;

    @CPF
    private String cpf;

    @DBRef
    private Set<Telephone> telephones;



}
