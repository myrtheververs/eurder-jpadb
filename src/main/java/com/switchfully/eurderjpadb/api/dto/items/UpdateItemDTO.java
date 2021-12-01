package com.switchfully.eurderjpadb.api.dto.items;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

public class UpdateItemDTO {

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

    private UpdateItemDTO(String name, String description, BigDecimal price, int amountInStock) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.amountInStock = amountInStock;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public int getAmountInStock() {
        return amountInStock;
    }

    public static class UpdateItemDTOBuilder {
        private String name;
        private String description;
        private BigDecimal price;
        private int amountInStock;

        public static UpdateItemDTO.UpdateItemDTOBuilder item() {
            return new UpdateItemDTO.UpdateItemDTOBuilder();
        }

        public UpdateItemDTO build() {
            return new UpdateItemDTO(name, description, price, amountInStock);
        }


        public UpdateItemDTO.UpdateItemDTOBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public UpdateItemDTO.UpdateItemDTOBuilder withDescription(String description) {
            this.description = description;
            return this;
        }

        public UpdateItemDTO.UpdateItemDTOBuilder withPrice(BigDecimal price) {
            this.price = price;
            return this;
        }

        public UpdateItemDTO.UpdateItemDTOBuilder withAmountInStock(int amountInStock) {
            this.amountInStock = amountInStock;
            return this;
        }
    }
}
