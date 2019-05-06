package app.store.persistence.repository;

import app.store.persistence.domain.Category;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.core.query.TextQuery;

import java.util.List;

public abstract class CategoryRepositoryImpl implements CategoryRepository {


    private final MongoOperations mongoOperations;

    protected CategoryRepositoryImpl(MongoOperations mongoOperations) {
        this.mongoOperations = mongoOperations;
    }

    @Override
    public List<Category> getAllBy(String text) {
        TextCriteria criteria = TextCriteria
                .forDefaultLanguage()
                .matching(text);
        Query query = TextQuery
                .queryText(criteria)
                .sortByScore();
        return mongoOperations.find(query, Category.class);
    }
}
