package by.homiel.shutov.sipla_web.utils;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TableDataService {

    List<String> getTableData();

    String getTableName();
}
