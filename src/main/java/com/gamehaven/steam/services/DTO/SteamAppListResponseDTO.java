package com.gamehaven.steam.services.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.util.List;

/**
 * Root response object for Steam app list API
 */
@Data
public class SteamAppListResponseDTO {
    private ApplistDTO applist;

    /**
     * Container for apps data
     */
    @Data
    public static class ApplistDTO {
        private AppsDTO apps;
    }

    /**
     * Container for app array
     */
    @Data
    public static class AppsDTO {
        @JsonProperty("app")
        private List<AppDTO> appList;
    }

    /**
     * Individual Steam app data
     */
    @Data
    public static class AppDTO {
        private long appid;
        private String name;
    }
}