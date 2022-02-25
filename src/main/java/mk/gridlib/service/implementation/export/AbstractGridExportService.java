package mk.gridlib.service.implementation.export;
import mk.gridlib.domain.GridExportFile;
import mk.gridlib.domain.conditions.SearchCondition;
import mk.gridlib.domain.conditions.SortCondition;
import mk.gridlib.domain.grid.GridColumnConfig;
import mk.gridlib.domain.grid.GridConfig;
import mk.gridlib.domain.grid.GridData;
import mk.gridlib.service.interfaces.GridConfigService;
import mk.gridlib.service.interfaces.GridDataService;
import mk.gridlib.service.interfaces.GridExportDataService;

import java.util.*;
import java.util.stream.Collectors;

public abstract class AbstractGridExportService implements GridExportDataService {

    protected final GridConfigService gridConfigService;

    protected final GridDataService gridDataService;

    public AbstractGridExportService(GridConfigService gridConfigService, GridDataService gridDataService) {
        this.gridConfigService = gridConfigService;
        this.gridDataService = gridDataService;
    }

    protected final  <T> GridExportFile createFile(byte[] data, String fileName, String mimeType) {
        GridExportFile gridExportFile = new GridExportFile();
        gridExportFile.setFileName(fileName);
        gridExportFile.setMimeType(mimeType);
        gridExportFile.setData(data);
        return gridExportFile;
    }

    protected  <T> List<GridColumnConfig<T>> getColumnsForExport(GridConfig<T> gridConfig) {
        return gridConfig.getColumns().stream()
                .filter(c -> !c.getIgnoreInExport())
                .sorted(Comparator.comparing(GridColumnConfig::getColumnNumber))
                .collect(Collectors.toList());
    }

    protected  <T> GridData findGridData(String gridName, List<SearchCondition> searchConditions, List<SortCondition> sortConditions) {
        return gridDataService.findGridData(gridName, searchConditions, sortConditions, Integer.MAX_VALUE, 0);
    }

    protected  <T> GridData findGridDataById(String gridName, List<SortCondition> sortConditions, List<Object> ids) {
        return gridDataService.findGridData(gridName, sortConditions, ids);
    }
}
