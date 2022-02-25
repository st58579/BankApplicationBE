package cz.upce.bank.grid;

import mk.gridlib.domain.conditions.SearchCondition;
import mk.gridlib.domain.conditions.SortCondition;

import java.util.List;

public class GridDataExportRequest {
    private List<SearchCondition> searchConditions;
    private List<SortCondition> sortConditions;

    private List<Object> ids;

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

    public List<Object> getIds() {
        return ids;
    }

    public void setIds(List<Object> ids) {
        this.ids = ids;
    }
}
