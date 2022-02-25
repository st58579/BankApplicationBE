package cz.upce.bank.grid;

import cz.upce.bank.eb.entity.*;
import cz.upce.bank.eb.grids.ZaplaceneUveryGrid;
import mk.gridlib.dao.GridDataDao;
import mk.gridlib.domain.conditions.SearchCondition;
import mk.gridlib.domain.conditions.SortCondition;
import mk.gridlib.enums.SEARCHTYPE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class GridDataRepository implements GridDataDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private Map<Class, String> classTableMap = new HashMap<>();

    private Map<Class, RowMapper> rowMappers = new HashMap<>();

    @PostConstruct
    public void init() {

        classTableMap.put(Klienti.class, "KLIENTI");
        rowMappers.put(Klienti.class, Klienti.getClientMapper());

        classTableMap.put(Ucty.class, "UDAJE_O_UCTECH");
        rowMappers.put(Ucty.class, Ucty.getUctyMapper());

        classTableMap.put(Karty.class, "UDAJE_O_KARTACH");
        rowMappers.put(Karty.class, Karty.getKartyMapper());

        classTableMap.put(Adresy.class, "UDAJE_O_ADRESACH");
        rowMappers.put(Adresy.class, Adresy.getAdresyViewMapper());

        classTableMap.put(KlientiAdresy.class, "UDAJE_O_ADRESACH_A_KLIENTECH");
        rowMappers.put(KlientiAdresy.class, KlientiAdresy.getClientAddressesMapper());

        classTableMap.put(Dokumenty.class, "UDAJE_O_DOKUMENTECH");
        rowMappers.put(Dokumenty.class, Dokumenty.getDokumentyMapper());

        classTableMap.put(Transakce.class, "UDAJE_O_TRANSAKCICH");
        rowMappers.put(Transakce.class, Transakce.getTransakceMapper());

        classTableMap.put(Uvery.class, "UDAJE_O_UVERECH");
        rowMappers.put(Uvery.class, Uvery.getUveryMapper());

        classTableMap.put(ZaplaceneUvery.class, "UVERY_ZAPLACENE");
        rowMappers.put(ZaplaceneUvery.class, ZaplaceneUvery.getZaplaceneUveryMapper());

        classTableMap.put(User.class, "UDAJE_O_UZIVATELICH");
        rowMappers.put(User.class, User.getUserViewMapper());

        classTableMap.put(Logovani.class, "DATABAZE_LOG");
        rowMappers.put(Logovani.class, Logovani.getLogovaniMapper());

        classTableMap.put(DatabaseObject.class, "ALL_USED_OBJECTS");
        rowMappers.put(DatabaseObject.class, DatabaseObject.getDatabaseObjectMapper());

        classTableMap.put(Recommendation.class, "PRANI");
        rowMappers.put(Recommendation.class, Recommendation.getRecommendationMapper());
    }

    @Override
    public <T> List<T> getData(Class<T> clazz, int count, int offset, List<SearchCondition> gridSearchCondition, List<SearchCondition> userSearchConditions, List<SortCondition> sortConditions) {

        StringBuilder sql = new StringBuilder(getBaseSelect(clazz));

        List<Object> gridSqlparams = new LinkedList<>();
        List<String> gridWhereParams = new LinkedList<>();
        for (SearchCondition searchCondition : gridSearchCondition) {
            SEARCHTYPE searchtype = searchCondition.getSearchType();
            String where = getWhere(searchCondition);
            gridWhereParams.add(where);
            if (searchCondition.getSearchType().equals(SEARCHTYPE.BETWEEN)) {
                gridSqlparams.add(searchCondition.getValue1());
                gridSqlparams.add(searchCondition.getValue2());
            } else if (searchtype == SEARCHTYPE.IN) {
                gridSqlparams.add(searchCondition.getValues());
            } else {
                gridSqlparams.add(searchCondition.getValue1());
            }
        }

        if (gridSqlparams.size() > 0) {
            sql.append(" WHERE ");
            String whereString = String.join(" AND ", gridWhereParams);
            sql.append(whereString);
        }

        List<Object> userSqlparams = new LinkedList<>();
        List<String> userWhereParams = new LinkedList<>();
        for (SearchCondition searchCondition : userSearchConditions) {
            SEARCHTYPE searchtype = searchCondition.getSearchType();
            String where = getWhere(searchCondition);
            userWhereParams.add(where);
            if (searchCondition.getSearchType().equals(SEARCHTYPE.BETWEEN)) {
                userSqlparams.add(searchCondition.getValue1());
                userSqlparams.add(searchCondition.getValue2());
            } else if (searchtype == SEARCHTYPE.IN) {
                userSqlparams.add(searchCondition.getValues());
            } else {
                userSqlparams.add(searchCondition.getValue1());
            }
        }
        if (userSqlparams.size() > 0) {
            if (gridSqlparams.isEmpty()) {
                sql.append(" WHERE ");
            }
            sql.append(" (");
            String whereString = String.join(" AND ", userWhereParams);
            sql.append(whereString);
            sql.append(") ");
        }

        sortConditions.sort(Comparator.comparing(SortCondition::getSortOrder));
        if (sortConditions.size() > 0) {
            List<String> sorts = sortConditions.stream().map(s -> " " + s.getField() + " " + s.getSortOrder()).collect(Collectors.toList());
            String orderByStr = String.join(" ", sorts);
            sql.append(" ORDER BY ").append(orderByStr);
        }

        // Oracle version
        sql.append(" OFFSET ").append(offset).append(" ROWS ").append(" FETCH NEXT ").append(count).append(" ROWS ONLY");

        // H2 Version
        /*sql.append(" limit  ").append(count).append(" offset ").append(offset);*/

        List<Object> params = new ArrayList<>(gridSqlparams);
        params.addAll(userSqlparams);

        return jdbcTemplate.query(sql.toString(), rowMappers.get(clazz), params.toArray());
    }

    private String getBaseSelect(Class clazz) {
        String tableName = classTableMap.get(clazz);

        if (tableName == null) {
            throw new IllegalArgumentException("Unknown class " + clazz.getSimpleName());
        }

        return "SELECT * from " + tableName;
    }
    private String getWhere(SearchCondition condition) {
        SEARCHTYPE searchtype = condition.getSearchType();
        String fieldName = condition.getFieldName();

        if (searchtype == SEARCHTYPE.MORE_OR_EQUALS) {
            return fieldName + " >= ? ";
        } else if (searchtype == SEARCHTYPE.MORE) {
            return fieldName + " > ? ";
        } else if (searchtype == SEARCHTYPE.LESS_OR_EQUALS) {
            return fieldName + " <= ? ";
        } else if (searchtype == SEARCHTYPE.LESS) {
            return fieldName + " >= ? ";
        } else if (searchtype == SEARCHTYPE.EQUALS) {
            return fieldName + " = ? ";
        } else if (searchtype == SEARCHTYPE.CONTAINS) {
            return fieldName + " like '%'||?||'%' ";
        }
        throw new UnsupportedOperationException("Pofixi menya");
    }

}
