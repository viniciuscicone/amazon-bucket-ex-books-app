package com.api.booksManager.domain;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDTO {

    @NotNull
    @NotBlank
    @NotEmpty(message = "{nome.NotEmpty} name")
    @Size(min = 1, message = "{nome.Size}")
    @Pattern(regexp = "^[A-Za-záàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ ]+$",message = "Permitido apenas String")
    String nameBook;
    @Valid
    @NotNull
    @NotBlank
    @NotEmpty(message = "{nome.NotEmpty} autor")
    @Size(min = 1, message = "{nome.Size}")
    @Pattern(regexp = "^[A-Za-záàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ ]+$",message = "Permitido apenas String")
    String autor;
    @NotNull
    @NotBlank
    @NotEmpty(message = "{nome.NotEmpty} sinopse")
    @Size(min = 1, message = "{nome.Size}")
    @Pattern(regexp = "^[A-Za-záàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ ]+$",message = "Permitido apenas String")
    String sinopse;
    @NotNull(message = "Nota não pode ser nula e deve ser entre 1 a 5")
    @Min(value = 1, message = "Permitido apenas notas de 1 a 5")
    @Max(value = 5, message = "Permitido apenas notas de 1 a 5")
    int nota;

}

