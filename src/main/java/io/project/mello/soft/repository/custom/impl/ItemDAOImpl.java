package io.project.mello.soft.repository.custom.impl;

import io.project.mello.soft.entity.Customer;
import io.project.mello.soft.entity.Item;
import io.project.mello.soft.repository.CrudDAOImpl;
import io.project.mello.soft.repository.custom.ItemDAO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ItemDAOImpl extends CrudDAOImpl<Item,String> implements ItemDAO {
    @Override
    public String getLastItemId() {
        return null;
    }

    @Override
    public List<Item> searchItem(String data) {
        return entityManager.createQuery("SELECT i FROM Item i WHERE i.description LIKE ?1 OR i.qtyOnHand = ?1 OR i.itemCode LIKE ?1", Item.class).setParameter(1,data).getResultList();
    }
}
