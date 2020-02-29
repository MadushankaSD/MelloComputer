package io.project.mello.soft.repository.custom.impl;

import io.project.mello.soft.entity.Item;
import io.project.mello.soft.repository.CrudDAOImpl;
import io.project.mello.soft.repository.custom.ItemDAO;
import org.springframework.stereotype.Repository;

@Repository
public class ItemDAOImpl extends CrudDAOImpl<Item,Integer> implements ItemDAO {
}
