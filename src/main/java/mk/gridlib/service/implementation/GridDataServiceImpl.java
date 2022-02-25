package mk.gridlib.service.implementation;

import mk.gridlib.domain.LovItemImpl;
import mk.gridlib.domain.conditions.SearchCondition;
import mk.gridlib.domain.conditions.SortCondition;
import mk.gridlib.domain.grid.GridColumnConfig;
import mk.gridlib.domain.grid.GridConfig;
import mk.gridlib.domain.grid.GridData;
import mk.gridlib.enums.COLUMNTYPE;
import mk.gridlib.enums.SEARCHTYPE;
import mk.gridlib.enums.VALUETYPE;
import mk.gridlib.exceptions.GridException;
import mk.gridlib.dao.GridDataDao;
import mk.gridlib.service.interfaces.GridDataService;
import mk.gridlib.dao.GridConfigDao;
import mk.gridlib.interfaces.objects.LovItem;
import mk.gridlib.interfaces.objects.ObjectWithId;
import mk.gridlib.interfaces.objects.ObjectWithLabel;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.*;

public class GridDataServiceImpl implements GridDataService {

    private final static String GRID_ROW_ID = "$_GRID_ROW_ID_$";

    private final GridDataDao dataDao;

    private final GridConfigDao gridConfigDao;

    public GridDataServiceImpl(GridDataDao dataDao, GridConfigDao gridConfigDao) {
        this.dataDao = dataDao;
        this.gridConfigDao = gridConfigDao;
    }

    @Override
    public <T> GridData findGridData(String gridName) {
        GridConfig<T> gridConfig = gridConfigDao.getGridConfig(gridName);
        return findGridData(gridConfig, new ArrayList<>(), gridConfig.getDefaultSortConditions(), null, null);
    }

    @Override
    public <T> GridData findGridData(String gridName, List<SearchCondition> searchConditions, List<SortCondition> sortConditions,
                                     Integer count, Integer offset) {
        GridConfig<T> gridConfig = gridConfigDao.getGridConfig(gridName);
        return findGridData(gridConfig, searchConditions, sortConditions, count, offset);
    }

    @Override
    public <T> GridData findGridData(String gridName, List<SortCondition> sortConditions, List<Object> ids) {
        GridConfig<T> gridConfig = gridConfigDao.getGridConfig(gridName);
        SearchCondition searchCondition = new SearchCondition(SEARCHTYPE.IN, gridConfig.getRowIdField(), ids);
        return findGridData(gridConfig, List.of(searchCondition), sortConditions, Integer.MAX_VALUE, 0);
    }


    private <T> GridData findGridData(GridConfig<T> gridConfig, List<SearchCondition> searchConditions, List<SortCondition> sortConditions,
                                      Integer count, Integer offset) {
        if (count == null) {
            count = GridConfig.DEFAULT_ROWS_COUNT;
        }
        if (offset == null) {
            offset = 0;
        }
        List<SearchCondition> userSearchConditions = changeSearchFieldNameToColumnName(searchConditions, gridConfig);
        List<SearchCondition> gridSearchConditions = changeSearchFieldNameToColumnName(gridConfig.getSearchConditions(), gridConfig);
        List<SortCondition> userSortConditions = changeSortFieldNameToColumnName(sortConditions, gridConfig);

        List<T> data = dataDao.getData(gridConfig.getClazz(), count, offset, gridSearchConditions, userSearchConditions, userSortConditions);
        List<Map<String, Object>> rows = getGridRows(gridConfig.getColumns(), gridConfig.getRowIdField(), data);

        GridData gridData = new GridData();
        gridData.setGridName(gridConfig.getGridName());
        gridData.setSearchConditions(searchConditions);
        gridData.setSortConditions(sortConditions);
        gridData.setCurrentCount(data.size());
        gridData.setCurrentOffset(offset);
        gridData.setRows(rows);

        return gridData;
    }

    private <T> List<Map<String, Object>> getGridRows(List<GridColumnConfig<T>> columns, String rowIdColumn, List<T> data) {
        if (data == null || data.isEmpty()) {
            return new ArrayList<>();
        }
        List<Map<String, Object>> rows = new ArrayList<>();

        for (T objectRow : data) {
            Map<String, Field> fields = extractFields(objectRow);
            Map<String, Object> row = new HashMap<>();

            for (GridColumnConfig<T> column : columns) {
                String fieldName = column.getFieldName();
                Object value = getGridCellValue(objectRow, fields, column);
                row.put(fieldName, value);
            }
            row.put(GRID_ROW_ID, getRowId(objectRow, fields, rowIdColumn));
            rows.add(row);
        }

        return rows;
    }

    private <T> Object getGridCellValue(T objectRow, Map<String, Field> fields, GridColumnConfig<T> column) {
        String fieldName = column.getFieldName();
        COLUMNTYPE columntype = column.getColumnType();
        if (columntype == COLUMNTYPE.CLASS_FIELD){
            Field field = fields.get(fieldName);
            if (field == null) {
                return null;
            } else {
                return extractFieldValue(objectRow, field);
            }
        } else if (columntype == COLUMNTYPE.VIRTUAL_FIELD) {
            if (column.getValueFn() == null) {
                return null;
            }
            return column.getValueFn().apply(objectRow);
        } else if (columntype == COLUMNTYPE.HIDDEN_FIELD) {
            Field field = fields.get(fieldName);
            if (field == null) {
                return null;
            } else {
                return extractFieldValue(objectRow, field);
            }
        } else {
            throw GridException.unknownColumnType(columntype);
        }
    }

    private <T> Object getRowId(T objectRow, Map<String, Field> fields, String rowIdField) {
        Field field = fields.get(rowIdField);
        return extractFieldValue(objectRow, field);
    }


    private Map<String, Field> extractFields(Object object) {
        Class<?> clazz = object.getClass();
        Field[] fields = clazz.getDeclaredFields();
        Map<String, Field> fieldMap = new HashMap<>();
        for (Field field : fields) {
            fieldMap.put(field.getName(), field);
        }
        return fieldMap;
    }

    private Object extractFieldValue(Object object, Field field) {
        try {
            field.setAccessible(true);
            Object value = field.get(object);
            return getValue(value);
        } catch (IllegalAccessException e) {
            return null;
        }
    }

    private Object getValue(Object object) {
        if (object == null) {
            return null;
        }
        VALUETYPE valuetype = VALUETYPE.getType(object.getClass());
        if (valuetype == VALUETYPE.NUMBER) {
            return new BigDecimal(object.toString());
        } else if (valuetype == VALUETYPE.STRING) {
            return object.toString();
        } else if (valuetype == VALUETYPE.DATE) {
            return object;
        } else if (valuetype == VALUETYPE.ENUMERATION) {
            return new LovItemImpl(object, object.toString());
        } else if (valuetype == VALUETYPE.COLLECTION) {
            return getCollectionValue(object);
        } else if (valuetype == VALUETYPE.OBJECT) {
            return getObjectValue(object);
        } else if (valuetype == VALUETYPE.BOOLEAN) {
            return Boolean.parseBoolean(object.toString());
        }else {
            throw new IllegalArgumentException("Unknown value type for class");
        }
    }

    private Object getCollectionValue(Object collection) {
        List<Object> values = new ArrayList<>();
        for (Object o : ((Collection) collection)) {
            values.add(getValue(o));
        }
        return values;
    }

    private Object getObjectValue(Object object) {
        Object id = object.toString();
        String label = object.toString();
        if (LovItem.class.isAssignableFrom(object.getClass())) {
            LovItem e = (LovItem) object;
            id = e.getId();
            label = e.getLabel();
        }
        if (ObjectWithId.class.isAssignableFrom(object.getClass())) {
            ObjectWithId e = (ObjectWithId) object;
            id = e.getId();
        }
        if (ObjectWithLabel.class.isAssignableFrom(object.getClass())) {
            ObjectWithLabel e = (ObjectWithLabel) object;
            label = e.getLabel();
        }

        return new LovItemImpl(id, label);
    }

    private <T> List<SearchCondition> changeSearchFieldNameToColumnName(List<SearchCondition> searchConditions, GridConfig<T> gridConfig) {
        Map<String, SearchCondition> searchConditionMap = new HashMap<>();
        searchConditions.forEach(s -> searchConditionMap.put(s.getFieldName(), s));

        List<SearchCondition> newSearchConditions = new ArrayList<>();
        for (GridColumnConfig<T> columnConfig : gridConfig.getColumns()) {
            SearchCondition searchCondition = searchConditionMap.get(columnConfig.getFieldName());

            if (searchCondition != null) {
                SearchCondition copy = searchCondition.copy();
                if (columnConfig.getTableColumn() != null && !columnConfig.getTableColumn().isEmpty()) {
                    copy.setFieldName(columnConfig.getTableColumn());
                }
                newSearchConditions.add(copy);
            }
        }
        return newSearchConditions;
    }

    private <T> List<SortCondition> changeSortFieldNameToColumnName(List<SortCondition> searchConditions, GridConfig<T> gridConfig) {
        Map<String, SortCondition> sortConditionMap = new HashMap<>();
        searchConditions.forEach(s -> sortConditionMap.put(s.getField(), s));

        List<SortCondition> newSortConditions = new ArrayList<>();
        for (GridColumnConfig<T> columnConfig : gridConfig.getColumns()) {
            SortCondition sortCondition = sortConditionMap.get(columnConfig.getFieldName());

            if (sortCondition != null) {
                SortCondition copy = sortCondition.copy();
                if (columnConfig.getTableColumn() != null && !columnConfig.getTableColumn().isEmpty()) {
                    copy.setField(columnConfig.getTableColumn());
                }
                newSortConditions.add(copy);
            }
        }
        return newSortConditions;
    }
}
