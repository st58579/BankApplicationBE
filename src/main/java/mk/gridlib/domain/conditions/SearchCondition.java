package mk.gridlib.domain.conditions;

import mk.gridlib.enums.SEARCHTYPE;

import java.util.List;

public class SearchCondition implements Cloneable {
    private SEARCHTYPE searchType;
    private String fieldName;
    private Object value1;
    private Object value2;
    private List<Object> values;

    public SearchCondition(){
    }

    public SearchCondition(SEARCHTYPE searchType, String fieldName, Object value1, Object value2) {
        this.searchType = searchType;
        this.fieldName = fieldName;
        this.value1 = value1;
        this.value2 = value2;
    }

    public SearchCondition(SEARCHTYPE searchType, String fieldName, List<Object> values) {
        this.searchType = searchType;
        this.fieldName = fieldName;
        this.values = values;
    }

    public SEARCHTYPE getSearchType() {
        return searchType;
    }

    public void setSearchType(SEARCHTYPE searchType) {
        this.searchType = searchType;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public Object getValue1() {
        return value1;
    }

    public void setValue1(Object value1) {
        this.value1 = value1;
    }

    public Object getValue2() {
        return value2;
    }

    public void setValue2(Object value2) {
        this.value2 = value2;
    }

    public List<Object> getValues() {
        return values;
    }

    public void setValues(List<Object> values) {
        this.values = values;
    }

    public SearchCondition copy() {
        SearchCondition searchCondition = new SearchCondition();
        searchCondition.setFieldName(fieldName);
        searchCondition.setSearchType(searchType);
        searchCondition.setValue1(value1);
        searchCondition.setValue2(value2);
        searchCondition.setValues(values);
        return searchCondition;
    }
}
