package mk.gridlib.builders.gridcolumn;

import mk.gridlib.builders.GridConfigBuilder;
import mk.gridlib.enums.COLUMNTYPE;

public class GridClassColumnBuilder<T> extends GridColumnBuilder<T> {
    public GridClassColumnBuilder(String fieldName, GridConfigBuilder<T> gridBuilder) {
        super(fieldName, gridBuilder);
        this.column.setColumnType(COLUMNTYPE.CLASS_FIELD);
    }
}
