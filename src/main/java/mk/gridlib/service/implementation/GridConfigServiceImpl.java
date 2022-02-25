package mk.gridlib.service.implementation;

import mk.gridlib.anotations.GridField;
import mk.gridlib.anotations.Translation;
import mk.gridlib.domain.grid.GridColumnConfig;
import mk.gridlib.domain.grid.GridConfig;
import mk.gridlib.enums.COLUMNTYPE;
import mk.gridlib.enums.SEARCHTYPE;
import mk.gridlib.enums.VALUETYPE;
import mk.gridlib.exceptions.GridException;
import mk.gridlib.service.interfaces.GridConfigService;
import mk.gridlib.dao.GridConfigDao;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GridConfigServiceImpl implements GridConfigService {

    private GridConfigDao gridConfigDao;

    public GridConfigServiceImpl(GridConfigDao gridConfigDao) {
        this.gridConfigDao = gridConfigDao;
    }

    @Override
    public void initGrids() {
        List<GridConfig<?>> gridConfigs = gridConfigDao.getGrids();
        for (GridConfig<?> gridConfig : gridConfigs) {
            initClassFieldsColumns(gridConfig);
            initCalculatedValuesColumns(gridConfig);
            initHiddenColumns(gridConfig);
        }
    }

    @Override
    public List<GridConfig<?>> getGrids() {
        return gridConfigDao.getGrids();
    }

    @Override
    public <T> void addGridConfig(GridConfig<T> config) {
        gridConfigDao.addGridConfig(config);
    }

    @Override
    public <T> GridConfig<T> getGridConfig(String name) {
        return gridConfigDao.getGridConfig(name);
    }

    private void initClassFieldsColumns(GridConfig<?> gridConfig) {
        Class<?> clazz = gridConfig.getClazz();
        Map<String, GridColumnConfig<?>> columns = extractColumns(gridConfig,COLUMNTYPE.CLASS_FIELD);

        List<Field> fields = Arrays.stream(clazz.getDeclaredFields())
                .filter(this::isGridField)
                .filter(f -> columns.containsKey(f.getName()))
                .collect(Collectors.toList());

        for (Field field : fields) {
            GridColumnConfig<?> column = columns.get(field.getName());
            VALUETYPE valuetype = VALUETYPE.getType(field.getType());
            column.setValueType(valuetype);
            if (column.getSearchType() == null) {
                column.setSearchType(SEARCHTYPE.NONE);
            }
            initFromGridFieldAnnotation(field, column);
        }
    }

    private void initCalculatedValuesColumns(GridConfig<?> gridConfig) {
        Map<String, GridColumnConfig<?>> columns = extractColumns(gridConfig,COLUMNTYPE.VIRTUAL_FIELD);
        for (GridColumnConfig<?> column : columns.values()) {
            if (column.getValueFn() == null) {
                throw GridException.missingParam(gridConfig.getGridName(), column.getFieldName(), "calculatedValueFn");
            }
            Method[] methods = column.getValueFn().getClass().getMethods();
            Class<?> clazz = methods[0].getReturnType();
            VALUETYPE valuetype = VALUETYPE.getType(clazz);
            column.setValueType(valuetype);
            column.setSearchType(SEARCHTYPE.NONE);
        }
    }

    private void initHiddenColumns(GridConfig<?> gridConfig) {
        Class<?> clazz = gridConfig.getClazz();
        Map<String, GridColumnConfig<?>> columns = extractColumns(gridConfig,COLUMNTYPE.HIDDEN_FIELD);

        List<Field> fields = Arrays.stream(clazz.getDeclaredFields())
                .filter(this::isGridField)
                .filter(f -> columns.containsKey(f.getName()))
                .collect(Collectors.toList());

        for (Field field : fields) {
            GridColumnConfig<?> column = columns.get(field.getName());
            VALUETYPE valuetype = VALUETYPE.getType(field.getType());
            column.setValueType(valuetype);
            column.setSearchType(SEARCHTYPE.NONE);
            initFromGridFieldAnnotation(field, column);
        }
    }

    private void initFromGridFieldAnnotation(Field field, GridColumnConfig<?> column) {
        if (!field.isAnnotationPresent(GridField.class)) {
            return;
        }
        GridField gridField = field.getAnnotation(GridField.class);
        if (column.getLabel() == null || column.getLabel().isEmpty()) {
            column.setLabel(gridField.defaultLabel());
        }
        for (Translation translation : gridField.translations()) {
            if (!column.getLabels().containsKey(translation.languageTag())) {
                column.getLabels().put(translation.languageTag(), translation.label());
            }
        }
    }

    private boolean isGridField(Field field) {
        return true;
    }

    private Map<String, GridColumnConfig<?>> extractColumns(GridConfig<?> config, COLUMNTYPE columntype) {
        List<? extends GridColumnConfig<?>> columnConfigs = config.getColumns();
        Map<String, GridColumnConfig<?>> map = new HashMap<>();
        columnConfigs.forEach(c -> {
                    if (c.getColumnType() == columntype){
                        map.put(c.getFieldName(), c);
                    }
                });
        return map;
    }
}
