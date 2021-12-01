package com.switchfully.eurderjpadb.sevices;


import com.switchfully.eurderjpadb.api.dto.items.ItemDTORequest;
import com.switchfully.eurderjpadb.api.dto.items.ItemDTOResponse;
import com.switchfully.eurderjpadb.api.dto.items.UpdateItemDTO;
import com.switchfully.eurderjpadb.api.mappers.ItemMapper;
import com.switchfully.eurderjpadb.domain.entities.Item;
import com.switchfully.eurderjpadb.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class ItemService {

    private final ItemMapper itemMapper;
    private final ItemRepository itemRepository;

    private final UserService userService;

    @Autowired
    public ItemService(ItemMapper itemMapper, ItemRepository itemRepository, UserService userService) {
        this.itemMapper = itemMapper;
        this.itemRepository = itemRepository;
        this.userService = userService;
    }

    public ItemDTOResponse save(ItemDTORequest itemDTORequest, Long adminId) {
        userService.assertValidAdminId(adminId);

        Item created = itemRepository.save(itemMapper.toEntity(itemDTORequest));
        return itemMapper.toResponse(created);
    }

    public ItemDTOResponse update(UpdateItemDTO updateItemDTO, Long itemId, Long adminId) {
        userService.assertValidAdminId(adminId);
        assertItemId(itemId);

        Item update = itemRepository.save(itemMapper.toEntity(updateItemDTO, itemId));
        return itemMapper.toResponse(update);
    }

    public List<ItemDTOResponse> getAllItems() {
        return itemRepository.findAll().stream()
                .map(itemMapper::toResponse)
                .collect(Collectors.toList());
    }



    //HELPER
    public Item getById(Long itemId) {
        return itemRepository.getById(itemId);
    }

    public void assertItemId(Long itemId) {
        Optional<Item> itemOptional = itemRepository.findById(itemId);

        if (itemOptional.isEmpty()) {
            throw new IllegalArgumentException("Item id not found");
        }
    }

}
