package app.store.persistence.repository;

import app.store.persistence.domain.Product;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.core.query.TextQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductRepositoryCustomImpl implements ProductRepositoryCustom {

    private final MongoTemplate mongoTemplate;

    protected ProductRepositoryCustomImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

//    public Document getAllBy(String searchString, Criteria filterCriteria) {
//        Document textSearch = new Document();
//        textSearch.put("text", "product");
//        textSearch.put("search", searchString);
//        textSearch.put("filter", org.springframework.data.mongodb.core.query.Query.query(filterCriteria).getQueryObject());
//        textSearch.put("limit", 10);
//        textSearch.put("project", new Document("_id", 1));
//        return mongoOperations.executeCommand(textSearch);
//    }

    @Override
    public List<Product> onTextValueQuery(String text) {
        TextCriteria criteria = TextCriteria
                .forDefaultLanguage()
                .matching(text);
        Query query = TextQuery
                .queryText(criteria)
                .sortByScore();
        return mongoTemplate.find(query, Product.class);
    }

}
