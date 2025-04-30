package com.gamehaven.steam.services.GameImport;

import java.util.List;

public class RawgGameDTO {
    public String name;
    public String description;
    public String released;
    public List<Developer> developers;

    public static class Developer {
        public String name;
    }
}
