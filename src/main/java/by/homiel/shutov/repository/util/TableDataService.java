package by.homiel.shutov.repository.util;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TableDataService {

    List<String> getTableData();

    String getTableName();
}
