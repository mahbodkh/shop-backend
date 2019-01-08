package app.store.service;

import app.store.persistence.repository.CommodityRepository;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

@Service
public class CommodityService {

    private final CommodityRepository commodityRepository;

    public CommodityService(CommodityRepository commodityRepository) {
        this.commodityRepository = commodityRepository;
    }

    public boolean isExists(String productId) {
        return commodityRepository.existsById(new ObjectId(productId));
    }
}
