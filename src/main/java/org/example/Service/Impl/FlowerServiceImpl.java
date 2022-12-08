package org.example.Service.Impl;

import org.example.DAO.FlowerDAO;
import org.example.Factory.DBFactory;
import org.example.POJO.Flower;
import org.example.Service.FlowerService;

import java.util.List;


public class FlowerServiceImpl implements FlowerService {
    FlowerDAO flowerDAO = DBFactory.getInstance().getFlowerDAO();
    @Override
    public void addFlower(Flower flower) {
        flowerDAO.addFlower(flower);
    }

    @Override
    public void updateFlowerNumber(String flowerName, long num) {
        flowerDAO.updateFlowerNumber(flowerName,num);
    }

    @Override
    public long findFlowerNumber(String flowerName) {
        return flowerDAO.findFlowerNumber(flowerName);
    }

    @Override
    public void reduceFlowerNumber(String flowerName, long num) {
        flowerDAO.reduceFlowerNumber(flowerName,num);
    }

    @Override
    public List<Flower> findAllFlower() {
        return flowerDAO.findAllFlower();
    }
}
