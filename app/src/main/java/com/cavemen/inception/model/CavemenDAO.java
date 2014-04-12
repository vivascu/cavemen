package com.cavemen.inception.model;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import org.androidannotations.annotations.EBean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.cavemen.inception.util.LogUtils.LOGE;

/**
 * A God DAO, which handles everything related to data.
 * <p/>
 * Created by eandreevici on 4/12/2014.
 */
@EBean
public class CavemenDAO {

    public List<DU> getDUs() {
        try {
            List<DU> result = new ArrayList<DU>();
            ParseQuery<ParseObject> duQuery = ParseQuery.getQuery(DU.TABLE_NAME);
            duQuery.setCachePolicy(ParseQuery.CachePolicy.CACHE_ELSE_NETWORK);
            for (ParseObject du : duQuery.find()) {
                result.add(DU.fromParseObject(du));
            }
            return result;
        } catch (ParseException e) {
            LOGE(CavemenDAO.class.getSimpleName(), e.getLocalizedMessage(), e);
        }
        return Collections.emptyList();
    }

    public List<Floor> getFloorsForDU(DU du) {
        try {
            ParseObject duObject = ParseQuery.getQuery(DU.TABLE_NAME).get(du.getId());
            ParseQuery<ParseObject> floorsQuery = ParseQuery.getQuery(Floor.TABLE_NAME);
            floorsQuery.setCachePolicy(ParseQuery.CachePolicy.CACHE_ELSE_NETWORK);
            floorsQuery.whereEqualTo(Floor.COLUMN_DU, duObject);
            floorsQuery.orderByAscending(Floor.COLUMN_NUMBER);
            List<Floor> floors = new ArrayList<Floor>();
            for (ParseObject floor : floorsQuery.find()) {
                Floor parsedFloor = Floor.fromParseObject(floor);
                floors.add(parsedFloor);
            }
            return floors;
        } catch (ParseException e) {
            LOGE(CavemenDAO.class.getSimpleName(), e.getLocalizedMessage(), e);
        }
        return Collections.emptyList();
    }

    public List<Table> getTablesForFloorId(String floorId) {
        try {
            List<Table> tables = new ArrayList<Table>();
            ParseObject floorObject = ParseQuery.getQuery(Floor.TABLE_NAME).get(floorId);
            ParseQuery<ParseObject> tablesQuery = ParseQuery.getQuery(Table.TABLE_NAME);
            tablesQuery.whereEqualTo(Table.COLUMN_FLOOR, floorObject);
            for (ParseObject table : tablesQuery.find()) {
                tables.add(Table.fromParseObject(table));
            }
            return tables;
        } catch (ParseException e) {
            LOGE(CavemenDAO.class.getSimpleName(), e.getLocalizedMessage(), e);
        }
        return Collections.emptyList();
    }

    public int[] calculateTableStats(Floor floor) {
        try {
            ParseQuery<ParseObject> floorQuery = ParseQuery.getQuery(Floor.TABLE_NAME);
            floorQuery.setCachePolicy(ParseQuery.CachePolicy.CACHE_ELSE_NETWORK);
            ParseObject floorObject = floorQuery.get(floor.getFloorId());
            ParseQuery<ParseObject> tablesQuery = ParseQuery.getQuery(Table.TABLE_NAME);
            tablesQuery.whereEqualTo(Table.COLUMN_FLOOR, floorObject);
            return new int[]{
                    tablesQuery.whereEqualTo(Table.COLUMN_STATUS, TableStatus.EMPTY.ordinal()).count(),
                    tablesQuery.whereEqualTo(Table.COLUMN_STATUS, TableStatus.BOOKED.ordinal()).count(),
                    tablesQuery.whereEqualTo(Table.COLUMN_STATUS, TableStatus.OCCUPIED.ordinal()).count()
            };
        } catch (ParseException e) {
            LOGE(CavemenDAO.class.getSimpleName(), e.getLocalizedMessage(), e);
        }
        return new int[0];
    }

    public int calculateOccupiedTablesPercentage(Floor floor) {
        int[] stats = calculateTableStats(floor);
        if (stats.length == 3) {
            return (int) ((float) (stats[1] + stats[2]) / (stats[0] + stats[1] + stats[2]) * 100);
        }
        return 0;
    }
}
