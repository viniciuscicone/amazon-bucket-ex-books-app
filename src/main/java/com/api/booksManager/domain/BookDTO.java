package com.api.booksManager.domain;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor(staticName = "build")
@NoArgsConstructor
public class BookDTO {

    @NotNull
    @NotBlank
    @NotEmpty(message = "{nome.NotEmpty} task")
    @Size(min = 1, message = "{nome.Size}")
    @Pattern(regexp = "^[A-Za-záàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ ]+$",message = "Permitido apenas String")
    String name;
    @Valid
    @NotNull
    @NotBlank
    @NotEmpty(message = "{nome.NotEmpty} autor")
    @Size(min = 1, message = "{nome.Size}")
    @Pattern(regexp = "^[A-Za-záàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ ]+$",message = "Permitido apenas String")
    String autor;

}