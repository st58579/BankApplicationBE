package mk.gridlib.service.implementation.export;
import mk.gridlib.domain.GridExportFile;
import mk.gridlib.domain.conditions.SearchCondition;
import mk.gridlib.domain.conditions.SortCondition;
import mk.gridlib.domain.grid.GridColumnConfig;
import mk.gridlib.domain.grid.GridConfig;
import mk.gridlib.domain.grid.GridData;
import mk.gridlib.service.interfaces.GridConfigService;
import mk.gridlib.service.interfaces.GridDataService;

import java.util.List;
import java.util.Map;

public class GridDashExportService extends AbstractGridExportService {

    private static final String FILE_EXTENSION = ".txt";
    private static final String TXT_MIME_TYPE = "text/plain";
    private static final String DASH_DELIMITER = " - ";

    public GridDashExportService(GridConfigService gridConfigService, GridDataService gridDataService) {
        super(gridConfigService, gridDataService);
    }

    @Override
    public <T> GridExportFile exportAll(String gridName, String language, List<SearchCondition> searchConditions, List<SortCondition> sortConditions) {
        GridConfig<T> gridConfig = gridConfigService.getGridConfig(gridName);
        GridData gridData = findGridData(gridName, searchConditions, sortConditions);
        List<GridColumnConfig<T>> columns = getColumnsForExport(gridConfig);
        return exportFile(gridConfig.getLabel(language), columns , gridData);
    }

    @Override
    public <T> GridExportFile exportById(String gridName, String language, List<SortCondition> sortConditions, List<Object> rowsIndexes) {
        GridConfig<T> gridConfig = gridConfigService.getGridConfig(gridName);
        GridData gridData = findGridDataById(gridName, sortConditions, rowsIndexes);
        List<GridColumnConfig<T>> columns = getColumnsForExport(gridConfig);
        return exportFile(gridConfig.getLabel(language), columns , gridData);
    }
    
    private <T> GridExportFile exportFile(String gridLabel, List<GridColumnConfig<T>> columns, GridData gridData) {
        String data = getStringData(columns, gridData.getRows());
        return createFile(data.getBytes(), gridLabel + FILE_EXTENSION, TXT_MIME_TYPE);
    }

    private <T> String getStringData(List<GridColumnConfig<T>> columns, List<Map<String, Object>> rows) {
        StringBuilder builder = new StringBuilder();
        for (Map<String, Object> row : rows) {
            for (int i = 0; i < columns.size(); i++) {
                GridColumnConfig<T> columnConfig = columns.get(i);
                builder.append(row.get(columnConfig.getFieldName()));
                if (i < columns.size() - 1){
                    builder.append(DASH_DELIMITER);
                }
            }
            builder.append("\n");
        }
        return builder.toString();
    }
}
