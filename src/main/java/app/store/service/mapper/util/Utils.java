package app.store.service.mapper.util;

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



}

