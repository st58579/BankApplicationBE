package mk.gridlib.builders.gridcolumn;

import mk.gridlib.builders.GridConfigBuilder;
import mk.gridlib.enums.COLUMNTYPE;

import mk.gridlib.interfaces.functional.ValueFn;

public class GridVirtualColumnBuilder<T> extends GridColumnBuilder<T> {

    public GridVirtualColumnBuilder(String fieldName, GridConfigBuilder<T> gridBuilder) {
        super(fieldName, gridBuilder);
        column.setColumnType(COLUMNTYPE.VIRTUAL_FIELD);
    }

    public GridVirtualColumnBuilder<T> fn(ValueFn<T, ?> fn){
        this.column.getVirtualValueFn(fn);
        return this;
    }
}
