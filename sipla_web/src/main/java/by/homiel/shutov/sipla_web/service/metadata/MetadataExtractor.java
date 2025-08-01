package by.homiel.shutov.sipla_web.service.metadata;

import by.homiel.shutov.sipla_web.config.ConfigDb;
import by.homiel.shutov.sipla_web.utils.Table;
import com.mongodb.client.MongoCollection;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.CopyOnWriteArrayList;

import static by.homiel.shutov.sipla_web.utils.Constants.MONGO_COLLECTION;
import static by.homiel.shutov.sipla_web.utils.Constants.MONGO_DB;
import static by.homiel.shutov.sipla_web.utils.Constants.POSTGRES_COLUMN_NAME;
import static by.homiel.shutov.sipla_web.utils.Constants.POSTGRES_PUBLIC_SCHEMA;
import static by.homiel.shutov.sipla_web.utils.Constants.POSTGRES_TABLE_NAME;

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

    public Map<String, List<String>> getMetaDataPg() {
        CopyOnWriteArrayList<String> metaData = new CopyOnWriteArrayList<>();
        Map<String, List<String>> allTablesWithFields = new TreeMap<>();

        try (Connection conn = configDbConnection.getPostgresqlConnection()) {
            DatabaseMetaData md = conn.getMetaData();
            ResultSet tablesPublic = md.getTables(null, POSTGRES_PUBLIC_SCHEMA, null, null);
            addDataToList(tablesPublic, metaData);

            // excluding unused tables and checking ignore list
            checkingIgnoreListAndExcludingUnusedTables(metaData);

            getColumnNameFromEachTable(metaData, md, allTablesWithFields);

        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return allTablesWithFields;
    }

    private void checkingIgnoreListAndExcludingUnusedTables(CopyOnWriteArrayList<String> metaData) {

        metaData.forEach(table -> {
            List<String> list = Arrays
                    .stream(Table.values())
                    .map(Enum::name)
                    .map(String::toLowerCase)
                    .filter(tableName -> !tableName.contains("pkey"))
                    .toList();
            if (!list.contains(table)) {
                metaData.remove(table);
            }
        });
    }

    private void getColumnNameFromEachTable(List<String> tables, DatabaseMetaData md, Map<String, List<String>> result) {
        tables.forEach(tableName -> {
            List<String> temp = new ArrayList<>();
            ResultSet rs;
            try {
                rs = md.getColumns(null, null, tableName, null);

                while (rs.next()) {
                    if (temp.contains(rs.getString(POSTGRES_COLUMN_NAME))) {
                        continue;
                    }
                    temp.add(rs.getString(POSTGRES_COLUMN_NAME));
                }
                result.put(tableName, temp);
            } catch (SQLException e) {
                log.error(e.getMessage(), e);
            }
        });
    }

    public String getSeparator() {
        return configDbConnection.separator;
    }

    private void addDataToList(ResultSet rs, CopyOnWriteArrayList<String> metaData) throws SQLException {
        while (rs.next()) {
            metaData.add(rs.getString(POSTGRES_TABLE_NAME));
        }
    }
}
