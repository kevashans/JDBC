package org;

public class enums {
    public enum positions {
        PG,
        C,
        PF,
        SF,
        SG
    }

    public enum playerCommands {
        FIND_ALL_PLAYERS,
        FIND_PLAYER_BY_ID,
        DELETE_PLAYER_BY_ID,
        INSERT_PLAYER,
        FIND_PLAYER_USING_FILTER;
    }

    public enum scoutCommands{
        FIND_ALL_SCOUTS,
        FIND_SCOUT_BY_ID,
        DELETE_SCOUT_BY_ID,
        INSERT_SCOUT,
        FIND_SCOUT_USING_FILTER
    }
    public enum reportCommands{
        FIND_ALL_REPORTS,
        FIND_REPORT_BY_ID,
        FIND_REPORT_BY_PLAYER_ID,
        FIND_REPORT_BY_SCOUT_ID,
        FIND_REPORT_BY_PLAYER_NAME,
        FIND_REPORT_BY_SEASON
    }
}
