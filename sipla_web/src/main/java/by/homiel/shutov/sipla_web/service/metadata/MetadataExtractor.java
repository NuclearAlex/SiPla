package by.homiel.shutov.sipla_web.service.metadata;

import by.homiel.shutov.sipla_web.config.ConfigDb;
import by.homiel.shutov.sipla_web.repository.util.Table;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

/**
 * * If a new schema with tables will add to database you need to add these tables into metaDataTableNames value!
 **/

@Slf4j
@Component
@AllArgsConstructor
public class MetadataExtractor {
    private static final String PUBLIC_SCHEMA = "public";
    private static final String TABLE_NAME = "TABLE_NAME";
    private static final String COLUMN_NAME = "COLUMN_NAME";

    private final ConfigDb configDbConnection;

    public Map<String, List<String>> getMetaDataMongo() {
//        ListCollectionsIterable<Document> questions = configDbConnection
//                .getMongodbClient()
//                .getDatabase("questions")
//                .listCollections();
        return null;
    }

    public Map<String, List<String>> getMetaDataPg() {
        CopyOnWriteArrayList<String> metaData = new CopyOnWriteArrayList<>();
        Map<String, List<String>> allTablesWithFields = new TreeMap<>();

        try (Connection conn = configDbConnection.getPostgresqlConnection()) {
            DatabaseMetaData md = conn.getMetaData();
            ResultSet tablesPublic = md.getTables(null, PUBLIC_SCHEMA, null, null);
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
                    if (temp.contains(rs.getString(COLUMN_NAME))) {
                        continue;
                    }
                    temp.add(rs.getString(COLUMN_NAME));
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
            metaData.add(rs.getString(TABLE_NAME));
        }
    }
}
