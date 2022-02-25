package cz.upce.bank.grid;

import mk.gridlib.domain.grid.GridData;
import mk.gridlib.domain.grid.GridSimpleConfig;
import mk.gridlib.service.interfaces.GridService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/core/grids")
@CrossOrigin(origins = "*")
public class GridController {

    @Autowired
    private GridService gridService;

    @GetMapping("/{gridName}")
    public @ResponseBody
    GridSimpleConfig getGridConfig(@PathVariable("gridName") String gridName) {
        return gridService.getGridConfig(gridName).getSimpleConfig("en");
    }

    @GetMapping("/{gridName}/values")
    public @ResponseBody
    GridData getGridValues(@PathVariable("gridName") String gridName) {
        return gridService.findGridData(gridName);
    }

    @PostMapping("/{gridName}/values")
    public @ResponseBody
    GridData getGridValues(@PathVariable("gridName") String gridName,
                                        @RequestBody GridDataRequest request) {
        return gridService.findGridData(gridName, request.getSearchConditions(),
                request.getSortConditions(), request.getCount(), request.getOffset());
    }

}
