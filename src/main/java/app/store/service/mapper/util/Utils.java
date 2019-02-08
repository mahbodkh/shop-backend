package app.store.service.mapper.util;

import app.store.persistence.domain.Authority;
import app.store.persistence.domain.enums.CartStatus;
import org.bson.types.ObjectId;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Service;

@Service
@Mapper
public class Utils {

    public ObjectId mapObjectId(String id) {
        if (id != null)
            return new ObjectId(id);
        return null;
    }

    public String mapId(ObjectId id) {
        if (id == null) {
            return "";
        }
        return id.toString();
    }

    public String mapProductStatus(CartStatus cartStatus) {
        return cartStatus.toString();
    }

    public CartStatus mapProductStatusString(String value) {
        return CartStatus.valueOf(value);
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

