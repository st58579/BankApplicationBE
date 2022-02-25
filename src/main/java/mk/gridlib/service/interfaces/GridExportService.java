package mk.gridlib.service.interfaces;

import mk.gridlib.domain.GridExportFile;
import mk.gridlib.domain.conditions.SearchCondition;
import mk.gridlib.domain.conditions.SortCondition;
import mk.gridlib.enums.EXPORTTYPE;

import java.util.List;

public interface GridExportService {
    <T> GridExportFile exportAll(EXPORTTYPE exporttype, String gridName, String language,
                                 List<SearchCondition> searchConditions, List<SortCondition> sortConditions);

    <T> GridExportFile exportById(EXPORTTYPE exporttype, String gridName, String language,
                                  List<SortCondition> sortConditions, List<Object> ids);
}
