package mk.gridlib.domain.grid;

import mk.gridlib.domain.conditions.SearchCondition;
import mk.gridlib.domain.conditions.SortCondition;

import java.util.List;
import java.util.Map;

public class GridData {

    private String gridName;

    private int currentCount;

    private int currentOffset;

    private List<SearchCondition> searchConditions;

    private List<SortCondition> sortConditions;

    private List<Map<String, Object>> rows;

    public String getGridName() {
        return gridName;
    }

    public void setGridName(String gridName) {
        this.gridName = gridName;
    }

    public int getCurrentCount() {
        return currentCount;
    }

    public void setCurrentCount(int currentCount) {
        this.currentCount = currentCount;
    }

    public int getCurrentOffset() {
        return currentOffset;
    }

    public void setCurrentOffset(int currentOffset) {
        this.currentOffset = currentOffset;
    }

    public List<SearchCondition> getSearchConditions() {
        return searchConditions;
    }

    public void setSearchConditions(List<SearchCondition> searchConditions) {
        this.searchConditions = searchConditions;
    }

    public List<SortCondition> getSortConditions() {
        return sortConditions;
    }

    public void setSortConditions(List<SortCondition> sortConditions) {
        this.sortConditions = sortConditions;
    }

    public List<Map<String, Object>> getRows() {
        return rows;
    }

    public void setRows(List<Map<String, Object>> rows) {
        this.rows = rows;
    }
}
