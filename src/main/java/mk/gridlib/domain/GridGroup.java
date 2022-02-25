package mk.gridlib.domain;

import mk.gridlib.domain.grid.GridConfig;

import java.util.ArrayList;
import java.util.List;

public class GridGroup {

    private String groupName;

    private String groupLabel;

    private List<GridConfig> grids = new ArrayList<>();


    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupLabel() {
        return groupLabel;
    }

    public void setGroupLabel(String groupLabel) {
        this.groupLabel = groupLabel;
    }

    public List<GridConfig> getGrids() {
        return grids;
    }

    public void setGrids(List<GridConfig> grids) {
        this.grids = grids;
    }
}
