package cz.upce.bank.eb.entity;

import org.springframework.jdbc.core.RowMapper;

import java.time.LocalDateTime;

public class Logovani {

    private int logId;

    private String tableName;

    private String query;

    private String desc;

    private LocalDateTime logTime;

    public static RowMapper<Logovani> getLogovaniMapper() {
        return (rs, rowNum) -> {
            Logovani logovani = new Logovani();
            logovani.setLogId(rs.getInt("LOG_ID"));
            logovani.setDesc(rs.getString("POPIS"));
            logovani.setLogTime(rs.getTimestamp("DATUM").toLocalDateTime());
            logovani.setQuery(rs.getString("DOTAZ"));
            logovani.setTableName(rs.getString("JMENO_TABULKY"));
            return logovani;
        };
    }

    public int getLogId() {
        return logId;
    }

    public void setLogId(int logId) {
        this.logId = logId;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public LocalDateTime getLogTime() {
        return logTime;
    }

    public void setLogTime(LocalDateTime logTime) {
        this.logTime = logTime;
    }
}
