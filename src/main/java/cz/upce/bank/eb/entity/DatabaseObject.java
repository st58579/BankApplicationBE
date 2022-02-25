package cz.upce.bank.eb.entity;

import org.springframework.jdbc.core.RowMapper;

public class DatabaseObject {

    private String objectName;
    private String objectType;

    public static RowMapper<DatabaseObject> getDatabaseObjectMapper(){
        return (rs, rowNum) -> {
            DatabaseObject databaseObject = new DatabaseObject();
            databaseObject.setObjectName(rs.getString("OBJECT_NAME"));
            databaseObject.setObjectType(rs.getString("OBJECT_TYPE"));
            return databaseObject;
        };
    }

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    public String getObjectType() {
        return objectType;
    }

    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }
}
