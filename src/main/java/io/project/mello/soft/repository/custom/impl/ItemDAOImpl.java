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
    public String getItemId(String name) {
        return (String) entityManager.createQuery("SELECT i.itemCode FROM Item i WHERE i.description = ?1").setParameter(1,name).setMaxResults(1).getSingleResult();
    }

    @Override
    public List<Item> searchItem(String data) {
        return entityManager.createQuery("SELECT i FROM Item i WHERE i.description LIKE ?1 OR i.qtyOnHand = ?1 OR i.itemCode LIKE ?1", Item.class).setParameter(1,data).getResultList();
    }

    @Override
    public void updateQty(String id, long qty) {
        entityManager.createQuery("UPDATE Item i SET i.qtyOnHand=(i.qtyOnHand-?1) WHERE i.itemCode=?2").setParameter(1,qty).setParameter(2,id);
    }
}
