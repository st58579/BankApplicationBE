package mk.gridlib.main;

import mk.gridlib.dao.GridConfigDao;
import mk.gridlib.dao.GridDataDao;
import mk.gridlib.service.implementation.export.GridDashExportService;
import mk.gridlib.service.interfaces.GridConfigService;
import mk.gridlib.service.interfaces.GridDataService;
import mk.gridlib.service.interfaces.GridExportDataService;
import mk.gridlib.service.interfaces.GridService;
import mk.gridlib.service.implementation.GridServiceImpl;
import mk.gridlib.service.implementation.GridConfigServiceImpl;
import mk.gridlib.service.implementation.GridDataServiceImpl;
import mk.gridlib.dao.GridConfigDaoImpl;

public class GridSystem {

    private GridConfigDao gridConfigDao;

    private GridConfigService gridConfigService;

    private GridDataService gridDataService;

    private GridDataDao gridDataDao;

    private GridService gridService;

    private GridExportDataService gridExportCSVDataService;

    private GridExportDataService gridExportXLSDataService;

    private GridExportDataService gridExportDashService;

    private GridSystem(GridDataDao gridDataDao, GridConfigDao gridConfigDao) {
        this.gridConfigDao = gridConfigDao;
        this.gridDataDao = gridDataDao;
        this.gridConfigService = new GridConfigServiceImpl(gridConfigDao);
        this.gridDataService = new GridDataServiceImpl(gridDataDao, gridConfigDao);
        this.gridExportDashService = new GridDashExportService(gridConfigService, gridDataService);
        this.gridService = new GridServiceImpl(gridDataService, gridConfigService, gridConfigDao, gridExportCSVDataService, gridExportXLSDataService, gridExportDashService);
    }

    public static GridService init(GridDataDao dataDao, GridConfigDao gridConfigDao) {
        return new GridSystem(dataDao, gridConfigDao).gridService;
    }

    public static GridService init(GridDataDao dataDao) {
        return new GridSystem(dataDao, new GridConfigDaoImpl()).gridService;
    }
}
