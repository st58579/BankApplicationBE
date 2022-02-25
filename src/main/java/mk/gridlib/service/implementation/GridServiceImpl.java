package mk.gridlib.service.implementation;

import mk.gridlib.domain.GridExportFile;
import mk.gridlib.domain.grid.GridConfig;
import mk.gridlib.domain.grid.GridData;
import mk.gridlib.domain.conditions.SearchCondition;
import mk.gridlib.domain.conditions.SortCondition;
import mk.gridlib.enums.EXPORTTYPE;
import mk.gridlib.exceptions.GridException;
import mk.gridlib.service.interfaces.*;
import mk.gridlib.dao.GridConfigDao;

import java.util.List;

public class GridServiceImpl implements GridService {

    private GridDataService gridDataService;

    private GridConfigService gridConfigService;

    private GridConfigDao gridConfigDao;

    private GridExportDataService exportDataCSVService;

    private GridExportDataService exportDataXLSService;

    private GridExportDataService exportDataDashService;

    public GridServiceImpl(GridDataService gridDataService,
                           GridConfigService gridConfigService,
                           GridConfigDao gridConfigDao,
                           GridExportDataService exportDataCSVService,
                           GridExportDataService exportDataXLSService,
                           GridExportDataService exportDataDashService) {
        this.gridDataService = gridDataService;
        this.gridConfigService = gridConfigService;
        this.gridConfigDao = gridConfigDao;
        this.exportDataCSVService = exportDataCSVService;
        this.exportDataXLSService = exportDataXLSService;
        this.exportDataDashService = exportDataDashService;
    }

    @Override
    public void initGrids() {
        gridConfigService.initGrids();
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

    @Override
    public <T> GridData findGridData(String gridName) {
        return gridDataService.findGridData(gridName);
    }

    @Override
    public <T> GridData findGridData(String gridName, List<SearchCondition> searchConditions, List<SortCondition> sortConditions, Integer count , Integer offset) {
        return gridDataService.findGridData(gridName, searchConditions, sortConditions, count, offset);
    }

    @Override
    public <T> GridData findGridData(String gridName, List<SortCondition> sortConditions, List<Object> ids) {
        return gridDataService.findGridData(gridName, sortConditions, ids);
    }

    @Override
    public <T> GridExportFile exportAll(EXPORTTYPE exporttype, String gridName, String language, List<SearchCondition> searchConditions, List<SortCondition> sortConditions) {
        if (exporttype == EXPORTTYPE.CSV) {
            return exportDataCSVService.exportAll(gridName, language, searchConditions, sortConditions);
        } else if (exporttype == EXPORTTYPE.XLS){
            return exportDataXLSService.exportAll(gridName, language, searchConditions, sortConditions);
        } else if (exporttype == EXPORTTYPE.DASH){
            return exportDataDashService.exportAll(gridName, language, searchConditions, sortConditions);
        }
        throw GridException.unknownExportType(exporttype);
    }

    @Override
    public <T> GridExportFile exportById(EXPORTTYPE exporttype, String gridName, String language, List<SortCondition> sortConditions, List<Object> ids) {
        if (exporttype == EXPORTTYPE.CSV) {
            return exportDataCSVService.exportById(gridName, language, sortConditions, ids);
        } else if (exporttype == EXPORTTYPE.XLS){
            return exportDataXLSService.exportById(gridName, language, sortConditions, ids);
        } else if (exporttype == EXPORTTYPE.DASH){
            return exportDataDashService.exportById(gridName, language, sortConditions, ids);
        }
        throw GridException.unknownExportType(exporttype);
    }
}
