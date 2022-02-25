package mk.gridlib.builders.gridcolumn;

import mk.gridlib.builders.GridConfigBuilder;
import mk.gridlib.enums.COLUMNTYPE;

public class GridHiddenColumnBuilder<T> extends GridColumnBuilder<T> {

    public GridHiddenColumnBuilder(String fieldName, GridConfigBuilder<T> gridBuilder) {
        super(fieldName, gridBuilder);
        column.setColumnType(COLUMNTYPE.HIDDEN_FIELD);
        column.setIgnoreInExport(true);
    }
}
