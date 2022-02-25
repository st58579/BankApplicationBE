package mk.gridlib.service.interfaces;

import mk.gridlib.domain.GridExportFile;
import mk.gridlib.domain.conditions.SearchCondition;
import mk.gridlib.domain.conditions.SortCondition;

import java.util.List;

public interface GridExportDataService {

    <T> GridExportFile exportAll(String gridName, String language,
                                 List<SearchCondition> searchConditions,
                                 List<SortCondition> sortConditions);

    <T> GridExportFile exportById(String gridName, String language,
                                  List<SortCondition> sortConditions,
                                  List<Object> ids);
}
