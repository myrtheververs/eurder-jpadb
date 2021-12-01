package com.switchfully.eurderjpadb.api.mappers;


import com.switchfully.eurderjpadb.api.dto.items.ItemDTORequest;
import com.switchfully.eurderjpadb.api.dto.items.ItemDTOResponse;
import com.switchfully.eurderjpadb.api.dto.items.UpdateItemDTO;
import com.switchfully.eurderjpadb.domain.entities.Item;
import org.springframework.stereotype.Component;

@Component
public class ItemMapper {


    public ItemDTOResponse toResponse(Item item) {
        return ItemDTOResponse.builder()
                .id(item.getId())
                .name(item.getName())
                .description(item.getDescription())
                .price(item.getPrice())
                .amountInStock(item.getAmountInStock())
                .build();
    }

    public Item toEntity(ItemDTORequest itemDTORequest) {
        return Item.builder()
                .name(itemDTORequest.getName())
                .description(itemDTORequest.getDescription())
                .price(itemDTORequest.getPrice())
                .amountInStock(itemDTORequest.getAmountInStock())
                .build();
    }

    public Item toEntity(UpdateItemDTO updateItemDTO, Long itemUUID) {
        return Item.builder()
                .id(itemUUID)
                .name(updateItemDTO.getName())
                .description(updateItemDTO.getDescription())
                .price(updateItemDTO.getPrice())
                .amountInStock(updateItemDTO.getAmountInStock())
                .build();
    }


}
