package mk.gridlib.exceptions;

import mk.gridlib.enums.COLUMNTYPE;
import mk.gridlib.enums.EXPORTTYPE;

public class GridException extends RuntimeException {
    private GridException(String message) {
        super(message);
    }

    public static GridException nonUniqueName(String name) {
        return new GridException("Grid " + name + " already defined");
    }

    public static GridException gridNotExists(String name) {
        return new GridException("Grid " + name + " doesnt exists");
    }

    public static GridException fieldInEntityNotExists(String className, String fieldName) {
        return new GridException("Field " + fieldName + " doesnt exists in class " + className);
    }

    public static GridException wrongColumnType(COLUMNTYPE columntype, String methodName) {
        return new GridException("Type " + columntype + " cant be used with method " + methodName);
    }

    public static GridException missingParam(String gridName, String columnName, String param) {
        return new GridException("For grid " + gridName + ": " + columnName + " param " + param + " is null or empty");
    }

    public static GridException contextIsNull() {
        return new GridException("Grid context is not initialized");
    }

    public static GridException badRowsCount() {
        return new GridException("Rows count should be greater than 0");
    }

    public static GridException unknownColumnType(COLUMNTYPE columntype) {return new GridException("Unknown column type: " + columntype);}

    public static GridException unknownExportType(EXPORTTYPE exporttype) {return new GridException("Unknown export type: " + exporttype);}

    public static GridException ioException(String message) {return new GridException(message);}

    public static GridException todo() {
        return new GridException("TODO");
    }
}
