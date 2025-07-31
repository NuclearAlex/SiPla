package by.homiel.shutov.sipla_web.service.metadata;

import by.homiel.shutov.sipla_web.config.ConfigDb;
import com.mongodb.client.MongoCollection;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.springframework.stereotype.Component;

import static by.homiel.shutov.sipla_web.utils.Constants.MONGO_COLLECTION;
import static by.homiel.shutov.sipla_web.utils.Constants.MONGO_DB;

/**
 * * If a new schema with tables will add to database you need to add these tables into metaDataTableNames value!
 **/

@Slf4j
@Component
@AllArgsConstructor
public class MetadataExtractor {

    private final ConfigDb configDbConnection;

    public MongoCollection<Document> getMetaDataMongo() {
        return configDbConnection
                .getMongodbClient()
                .getDatabase(MONGO_DB)
                .getCollection(MONGO_COLLECTION);
    }

    public String getSeparator() {
        return configDbConnection.separator;
    }
}
