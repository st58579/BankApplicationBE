package mk.gridlib.domain.grid;

import mk.gridlib.enums.COLUMNTYPE;
import mk.gridlib.enums.SEARCHTYPE;
import mk.gridlib.enums.CONTENTTYPE;
import mk.gridlib.enums.VALUETYPE;

public class GridColumnSimpleConfig {
    private String fieldName;
    private String label;
    private String description;

    private SEARCHTYPE searchType;
    private VALUETYPE valueType;
    private COLUMNTYPE columnType;
    private CONTENTTYPE contentType;

    private int columnNumber;

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public SEARCHTYPE getSearchType() {
        return searchType;
    }

    public void setSearchType(SEARCHTYPE searchType) {
        this.searchType = searchType;
    }

    public VALUETYPE getValueType() {
        return valueType;
    }

    public void setValueType(VALUETYPE valueType) {
        this.valueType = valueType;
    }

    public COLUMNTYPE getColumnType() {
        return columnType;
    }

    public void setColumnType(COLUMNTYPE columnType) {
        this.columnType = columnType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getColumnNumber() {
        return columnNumber;
    }

    public void setColumnNumber(int columnNumber) {
        this.columnNumber = columnNumber;
    }

    public CONTENTTYPE getContentType() {
        return contentType;
    }

    public void setContentType(CONTENTTYPE contentType) {
        this.contentType = contentType;
    }
}
