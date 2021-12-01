package com.switchfully.eurderjpadb.api.dto.items;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

@Builder
@Getter
@AllArgsConstructor
public class ItemDTORequest {
    @NotBlank(message = "Name can not be empty")
    @NotNull
    private final String name;

    @NotBlank(message = "Description can not be empty")
    @NotNull
    private final String description;

    @Positive(message = "Price needs to be greater than zero")
    private final BigDecimal price;

    @PositiveOrZero(message = "Stock can't be negative")
    private final int amountInStock;




}
