package app.store.service.mapper.util;

import app.store.persistence.domain.Authority;
import app.store.persistence.domain.enums.ProductStatus;
import org.bson.types.ObjectId;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Service;

@Service
@Mapper
public class Utils {

    public ObjectId mapObjectId(String id) {
        return new ObjectId(id);
    }

    public String mapId(ObjectId id) {
        return id.toString();
    }

    public String mapProductStatus(ProductStatus productStatus) {
        return productStatus.toString();
    }

    public ProductStatus mapProductStatusString(String value) {
        return ProductStatus.valueOf(value);
    }

    public String mapAuthority(Authority authority) {
        return authority.getName();
    }

    public Authority mapAuthority(String authority) {
        Authority auth = new Authority();
        auth.setName(authority);
        return auth;
    }

}

