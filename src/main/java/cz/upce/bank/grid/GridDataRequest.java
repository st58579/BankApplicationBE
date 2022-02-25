package cz.upce.bank.grid;

import mk.gridlib.domain.conditions.SearchCondition;
import mk.gridlib.domain.conditions.SortCondition;

import java.util.List;

public class GridDataRequest {
    private Integer offset;
    private Integer count;
    private List<SearchCondition> searchConditions;
    private List<SortCondition> sortConditions;

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
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
}
