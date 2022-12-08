package org.example.DAO;

import org.example.POJO.Flower;

import java.util.List;

public interface FlowerDAO {
    void addFlower(Flower flower);
    void updateFlowerNumber(String flowerName,long num);
    void reduceFlowerNumber(String flowerName,long num);
    long findFlowerNumber(String flowerName);
    List<Flower> findAllFlower();
    void updatebuyflowernum(String flowername,long num);
    long findbuyflowernum(String flowername);
}
