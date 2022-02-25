package mk.gridlib.domain.conditions;

import mk.gridlib.enums.SORTORDER;

public class SortCondition {
    private String field;
    private SORTORDER sortOrder;
    private int conditionNo;

    public SortCondition() {
    }

    public SortCondition(String field, SORTORDER sortOrder, int conditionNo) {
        this.field = field;
        this.sortOrder = sortOrder;
        this.conditionNo = conditionNo;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public SORTORDER getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(SORTORDER sortOrder) {
        this.sortOrder = sortOrder;
    }

    public int getConditionNo() {
        return conditionNo;
    }

    public void setConditionNo(int conditionNo) {
        this.conditionNo = conditionNo;
    }

    public SortCondition copy() {
        SortCondition sortCondition = new SortCondition();
        sortCondition.setField(field);
        sortCondition.setConditionNo(conditionNo);
        sortCondition.setSortOrder(sortOrder);
        return sortCondition;
    }
}
