package org.example.Service;

import org.example.POJO.Flowerstore;

import java.util.List;

public interface FlowerstoreService {
    Flowerstore findFlowerByName(String Flowername);
    List<Flowerstore> findAllFlower();
    void addflowerstore(Flowerstore flowerstore);
    void updateflowerstoreOutPrince(String flowerstoreName,double OutPrince);
    double buyFlower(String flowerName,long num);
}
