package mk.gridlib.domain.grid;

import mk.gridlib.domain.conditions.SearchCondition;
import mk.gridlib.domain.conditions.SortCondition;
import mk.gridlib.interfaces.objects.ObjectWithTranslation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GridConfig<T> implements ObjectWithTranslation {

    public static final int DEFAULT_ROWS_COUNT = 100;

    private final Class<T> clazz;

    private final String rowIdField;

    private String gridName;

    private String gridLabel;

    private int defaultRowsCount = DEFAULT_ROWS_COUNT;

    private List<GridColumnConfig<T>> columns;

    private List<SearchCondition> searchConditions;

    private List<SortCondition> defaultSortConditions;

    private Map<String, String> labels;

    public GridConfig(Class<T> clazz, String rowIdField) {
        this.clazz = clazz;
        this.rowIdField = rowIdField;
        this.columns = new ArrayList<>();
        this.defaultSortConditions = new ArrayList<>();
        this.searchConditions = new ArrayList<>();
        this.labels = new HashMap<>();
    }

    @Override
    public String getLabel(String languageTag) {
        if (this.labels.containsKey(languageTag)) {
            return labels.get(languageTag);
        }
        return gridLabel;
    }

    @Override
    public String getLabel() {
        return gridLabel;
    }

    public String getGridName() {
        return gridName;
    }

    public void setGridName(String gridName) {
        this.gridName = gridName;
    }

    public Class<T> getClazz() {
        return clazz;
    }

    public List<GridColumnConfig<T>> getColumns() {
        return columns;
    }

    public void setColumns(List<GridColumnConfig<T>> columns) {
        this.columns = columns;
    }

    public void setDefaultRowsCount(int defaultRowsCount) {
        this.defaultRowsCount = defaultRowsCount;
    }

    public List<SortCondition> getDefaultSortConditions() {
        return defaultSortConditions;
    }

    public void setDefaultSortConditions(List<SortCondition> defaultSortConditions) {
        this.defaultSortConditions = defaultSortConditions;
    }

    public String getGridLabel() {
        return gridLabel;
    }

    public void setGridLabel(String gridLabel) {
        this.gridLabel = gridLabel;
    }

    public Map<String, String> getLabels() {
        return labels;
    }

    public List<SearchCondition> getSearchConditions() {
        return searchConditions;
    }

    public void setSearchConditions(List<SearchCondition> searchConditions) {
        this.searchConditions = searchConditions;
    }

    public void setLabels(Map<String, String> labels) {
        this.labels = labels;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("GridConfig: ").append(gridName).append("\n")
                .append("\tClass: ").append(clazz.getSimpleName()).append("\n");


        for (GridColumnConfig column : columns) {
            builder.append("\t\t").append(column.toString()).append("\n");
        }
        return builder.toString();
    }

    public GridSimpleConfig<T> getSimpleConfig(String language) {
        GridSimpleConfig<T> simpleConfig = new GridSimpleConfig<>();
        simpleConfig.setClazz(this.clazz);
        simpleConfig.setGridName(this.gridName);
        simpleConfig.setDefaultSortConditions(this.defaultSortConditions);
        simpleConfig.setGridLabel(getLabel(language));
        simpleConfig.setDefaultRowsCount(this.defaultRowsCount);

        List<GridColumnSimpleConfig> columns = new ArrayList<>();
        for (GridColumnConfig<T> columnConfig : this.columns) {
            columns.add(columnConfig.getSimpleConfig(language));
        }
        simpleConfig.setColumns(columns);

        return simpleConfig;
    }

    public String getRowIdField() {
        return rowIdField;
    }
}
