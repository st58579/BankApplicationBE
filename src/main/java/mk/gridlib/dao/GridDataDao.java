package mk.gridlib.dao;

import mk.gridlib.domain.conditions.SearchCondition;
import mk.gridlib.domain.conditions.SortCondition;

import java.util.List;

public interface GridDataDao {

    <T> List<T> getData(Class<T> entity, int count, int offset,
                        List<SearchCondition> gridSearchConditions,
                        List<SearchCondition> userSearchConditions,
                        List<SortCondition> sortConditions);
}
