package io.project.mello.soft.bussiness.custom.impl;

import io.project.mello.soft.bussiness.custom.ItemBO;
import io.project.mello.soft.dto.ItemDTO;
import io.project.mello.soft.entity.Item;
import io.project.mello.soft.repository.custom.ItemDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Component
public class ItemBOImpl implements ItemBO {
    @Autowired
    ItemDAO itemDAO;

    @Override
    public void saveItem(ItemDTO item) {
    itemDAO.save(new Item(item.getItemCode(),item.getDescription(),item.getUnitPrice(),item.getQtyOnHand()));
    }

    @Override
    public void updateItem(ItemDTO item) {
    itemDAO.update(new Item(item.getItemCode(),item.getDescription(),item.getUnitPrice(),item.getQtyOnHand()));
    }

    @Override
    public void deleteItem(String itemId) {
    itemDAO.delete(itemId);
    }

    @Transactional(readOnly = true)
    @Override
    public List<ItemDTO> findAllItems() {
        List<ItemDTO> object = new ArrayList<>();
        List<Item> all = itemDAO.findAll();
        for (Item data:
             all) {
            object.add(new ItemDTO(data.getItemCode(),data.getDescription(),data.getUnitPrice(),data.getQtyOnHand()));
        }
        return object;
    }

    @Transactional(readOnly = true)
    @Override
    public String getItemId(String name) {
        return itemDAO.getItemId(name);
    }



    @Transactional(readOnly = true)
    @Override
    public ItemDTO findItem(String itemId){
            Item item = itemDAO.find(itemId);
            return new ItemDTO(item.getDescription(),item.getUnitPrice(),item.getQtyOnHand());
    }

    @Transactional(readOnly = true)
    @Override
    public List<ItemDTO> searchItems(String datas) {
        List<ItemDTO> object = new ArrayList<>();
        List<Item> items = itemDAO.searchItem(datas);
        for (Item data:
                items) {
            object.add(new ItemDTO(data.getItemCode(),data.getDescription(),data.getUnitPrice(),data.getQtyOnHand()));
        }
        return object;
    }
}
