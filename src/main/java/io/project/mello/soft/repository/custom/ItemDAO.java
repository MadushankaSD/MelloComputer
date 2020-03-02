package io.project.mello.soft.repository.custom;

import io.project.mello.soft.entity.Customer;
import io.project.mello.soft.entity.Item;
import io.project.mello.soft.repository.CrudDAO;

import java.util.List;

public interface ItemDAO extends CrudDAO<Item,String> {
    String getItemId(String name);

    List<Item> searchItem(String data);

    void updateQty(String id, long qty);
}
