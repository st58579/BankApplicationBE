package mk.gridlib.builders.gridcolumn;

import mk.gridlib.builders.GridConfigBuilder;
import mk.gridlib.domain.grid.GridColumnConfig;
import mk.gridlib.enums.COLUMNTYPE;
import mk.gridlib.enums.CONTENTTYPE;
import mk.gridlib.enums.SEARCHTYPE;

import java.util.Comparator;

public abstract class GridColumnBuilder<T> {

    protected final GridConfigBuilder<T> gridBuilder;

    protected final GridColumnConfig<T> column;

    public GridColumnBuilder(String fieldName, GridConfigBuilder<T> gridBuilder){
        this.gridBuilder = gridBuilder;
        this.column = new GridColumnConfig<>();
        column.setFieldName(fieldName);
        column.setLabel(fieldName);
        column.setContentType(CONTENTTYPE.TEXT);
    }

    public GridColumnBuilder<T> label(String label) {
        column.setLabel(label);
        return this;
    }

    public GridColumnBuilder<T> label(String language, String label) {
        this.column.getLabels().put(language, label);
        return this;
    }

    public GridColumnBuilder<T> tableColumn(String columnName) {
        this.column.setTableColumn(columnName);
        return this;
    }

    public GridColumnBuilder<T> description(String label) {
        column.setDescription(label);
        return this;
    }

    public GridColumnBuilder<T> searchType(SEARCHTYPE searchtype) {
        this.column.setSearchType(searchtype);
        return this;
    }

    public GridColumnBuilder<T> contentType(CONTENTTYPE contentType) {
        this.column.setContentType(contentType);
        return this;
    }

    public GridColumnBuilder<T> exportIgnore(boolean ignore) {
        this.column.setIgnoreInExport(ignore);
        return this;
    }

    public GridConfigBuilder<T> end() {
        if (this.column.getColumnType() == COLUMNTYPE.HIDDEN_FIELD) {
            this.column.setColumnNumber(calculateNextColumnNoForHidden());
        } else {
            this.column.setColumnNumber(calculateNextColumnNo());
        }
        this.gridBuilder.getColumns().put(column.getFieldName(), column);
        return this.gridBuilder;
    }

    private int calculateNextColumnNo() {
        int lastNumber = this.gridBuilder.getColumns().values()
                .stream()
                .filter(c -> c.getColumnType() != COLUMNTYPE.HIDDEN_FIELD)
                .map(GridColumnConfig::getColumnNumber)
                .max(Comparator.comparingInt(c -> c))
                .orElse(-1);

        return lastNumber + 1;
    }

    private int calculateNextColumnNoForHidden() {
        int lastNumber = this.gridBuilder.getColumns().values()
                .stream()
                .filter(c -> c.getColumnType() == COLUMNTYPE.HIDDEN_FIELD)
                .map(GridColumnConfig::getColumnNumber)
                .min(Comparator.comparingInt(c -> c))
                .orElse(0);

        return lastNumber - 1;
    }
}
