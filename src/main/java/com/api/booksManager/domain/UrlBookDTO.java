package com.api.booksManager.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class UrlBookDTO {
    @NotNull
    @NotBlank
    private String id;
}
