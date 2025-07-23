package by.homiel.shutov.repository.util;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;

@Service
public class FileDataService {
    private final Map<String, TableDataService> repoMap;

    public FileDataService(List<TableDataService> repos) {
        this.repoMap = repos.stream().collect(toMap(TableDataService::getTableName, identity()));
    }

    public List<String> getFileData(String name) {
        TableDataService tableDataService = repoMap.get(name);
        if (tableDataService == null) {
            return List.of();
        }
        return tableDataService.getTableData();
    }
}
