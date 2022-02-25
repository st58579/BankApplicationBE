package mk.gridlib.domain.grid;

import mk.gridlib.enums.COLUMNTYPE;
import mk.gridlib.enums.SEARCHTYPE;
import mk.gridlib.enums.CONTENTTYPE;
import mk.gridlib.enums.VALUETYPE;
import mk.gridlib.interfaces.functional.ValueFn;
import mk.gridlib.interfaces.objects.ObjectWithTranslation;

import java.util.HashMap;
import java.util.Map;

public class GridColumnConfig<T> implements ObjectWithTranslation {
    private String fieldName;
    private String label;
    private String description;

    private SEARCHTYPE searchType;
    private VALUETYPE valueType;
    private COLUMNTYPE columnType;
    private CONTENTTYPE contentType;

    private boolean ignoreInExport;

    private int columnNumber;

    private String tableColumn;

    private ValueFn<T, ?> valueFn;

    private Map<String, String> labels = new HashMap<>();

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    @Override
    public String getLabel() {
        return label;
    }

    @Override
    public String getLabel(String languageTag) {
        if (!labels.containsKey(languageTag)){
            return getLabel();
        }
        return labels.get(languageTag);
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public VALUETYPE getValueType() {
        return valueType;
    }

    public void setValueType(VALUETYPE valueType) {
        this.valueType = valueType;
    }

    public Map<String, String> getLabels() {
        return labels;
    }

    public COLUMNTYPE getColumnType() {
        return columnType;
    }

    public void setColumnType(COLUMNTYPE columnType) {
        this.columnType = columnType;
    }

    public void setLabels(Map<String, String> labels) {
        this.labels = labels;
    }

    public SEARCHTYPE getSearchType() {
        return searchType;
    }

    public void setSearchType(SEARCHTYPE searchType) {
        this.searchType = searchType;
    }

    public ValueFn<T, ?> getValueFn() {
        return valueFn;
    }

    public void getVirtualValueFn(ValueFn<T, ?> valueFn) {
        this.valueFn = valueFn;
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

    public void setValueFn(ValueFn<T, ?> valueFn) {
        this.valueFn = valueFn;
    }

    public boolean getIgnoreInExport() {
        return ignoreInExport;
    }

    public void setIgnoreInExport(boolean ignoreInExport) {
        this.ignoreInExport = ignoreInExport;
    }

    public String getTableColumn() {
        return tableColumn;
    }

    public void setTableColumn(String tableColumn) {
        this.tableColumn = tableColumn;
    }

    public boolean isIgnoreInExport() {
        return ignoreInExport;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Grid column: \n")
                .append("\tLabel: ").append(label).append("\n")
                .append("\tField name: ").append(fieldName).append("\n")
                .append("\tSearch type: ").append(searchType).append("\n")
                .append("\tValue type: ").append(valueType).append("\n")
                .append("\tColumn type: ").append(columnType).append("\n")
                .append("\tLabels :");

        for (Map.Entry<String, String> label : labels.entrySet()) {
            builder.append("\n\t\t").append(label.getKey()).append("->").append(label.getValue());
        }
        return builder.toString();
    }

    public GridColumnSimpleConfig getSimpleConfig(String language) {
        GridColumnSimpleConfig simpleConfig = new GridColumnSimpleConfig();
        simpleConfig.setFieldName(getFieldName());
        simpleConfig.setLabel(getLabel(language));
        simpleConfig.setColumnType(getColumnType());
        simpleConfig.setSearchType(getSearchType());
        simpleConfig.setValueType(getValueType());
        simpleConfig.setDescription(getDescription());
        simpleConfig.setColumnNumber(getColumnNumber());
        simpleConfig.setContentType(getContentType());
        return simpleConfig;
    }
}
