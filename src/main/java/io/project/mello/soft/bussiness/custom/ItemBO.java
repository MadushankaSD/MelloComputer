package io.project.mello.soft.bussiness.custom;

import io.project.mello.soft.bussiness.SuperBO;
import io.project.mello.soft.dto.CustomerDTO;
import io.project.mello.soft.dto.ItemDTO;

import java.util.List;

public interface ItemBO extends SuperBO {

    void saveItem(ItemDTO item);

    void updateItem(ItemDTO item);

    void deleteItem(String itemId) ;

    List<ItemDTO> findAllItems();

    String getItemId(String name);

    ItemDTO findItem(String itemId);

    List<ItemDTO> searchItems(String datas);

}
