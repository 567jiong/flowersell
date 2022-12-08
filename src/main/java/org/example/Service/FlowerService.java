package org.example.Service;

import org.example.POJO.Flower;

import java.util.List;

public interface FlowerService {
    void addFlower(Flower flower);
    void updateFlowerNumber(String flowerName,long num);
    long findFlowerNumber(String flowerName);
    void reduceFlowerNumber(String flowerName,long num);
    List<Flower> findAllFlower();
}
