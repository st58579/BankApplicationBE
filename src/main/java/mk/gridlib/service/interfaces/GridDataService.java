package mk.gridlib.service.interfaces;

import mk.gridlib.domain.grid.GridData;
import mk.gridlib.domain.conditions.SearchCondition;
import mk.gridlib.domain.conditions.SortCondition;

import java.util.List;
import java.util.Optional;

public interface GridDataService {


    <T> GridData findGridData(String gridName);

    <T> GridData findGridData(String gridName, List<SearchCondition> searchConditions, List<SortCondition> sortConditions,
                              Integer count, Integer offset);

    <T> GridData findGridData(String gridName, List<SortCondition> sortConditions, List<Object> ids);
}
