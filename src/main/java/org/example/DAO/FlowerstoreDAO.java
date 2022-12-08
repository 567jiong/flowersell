package org.example.DAO;

import org.example.POJO.Flowerstore;

import java.util.List;

public interface FlowerstoreDAO {
    Flowerstore findFlowerByName(String Flowername);
    List<Flowerstore> findAllFlower();
    double buyFlower(String flowerName,long num);
    void addflowerstore(Flowerstore flowerstore);
    void updateflowerstoreOutPrince(String flowerstoreName,double OutPrince);
}
