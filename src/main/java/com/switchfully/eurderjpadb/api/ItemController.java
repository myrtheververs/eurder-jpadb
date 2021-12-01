package com.switchfully.eurderjpadb.api;


import com.switchfully.eurderjpadb.api.dto.items.ItemDTORequest;
import com.switchfully.eurderjpadb.api.dto.items.ItemDTOResponse;
import com.switchfully.eurderjpadb.api.dto.items.UpdateItemDTO;
import com.switchfully.eurderjpadb.sevices.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/items")
public class ItemController {

    private final ItemService itemService;
    private final Logger logger = LoggerFactory.getLogger(ItemController.class);

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    //GET
    @GetMapping(produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public List<ItemDTOResponse> getAllItems() {
        logger.info("Admin getting all items");
        return itemService.getAllItems();
    }

    //POST
    @PostMapping(consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public ItemDTOResponse createItem(@Valid @RequestBody ItemDTORequest itemDTORequest,
                                      @RequestHeader(value = "id") Long adminId) {
        logger.info("Admin is making new item " + itemDTORequest.getName());
        return itemService.save(itemDTORequest, adminId);
    }

    //PUT
    @PutMapping(path = "{itemId}", consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public ItemDTOResponse updateItem(@PathVariable Long itemId,
                                      @Valid @RequestBody UpdateItemDTO itemDTO,
                                      @RequestHeader(value = "id") Long adminId) {
        logger.info("Admin is updating item with id " + itemId);
        return itemService.update(itemDTO, itemId, adminId);
    }
}
