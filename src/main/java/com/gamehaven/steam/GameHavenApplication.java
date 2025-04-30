package com.gamehaven.steam;

import com.gamehaven.steam.model.Game;
import com.gamehaven.steam.model.GameImport;
import com.gamehaven.steam.services.GameImageService;
import com.gamehaven.steam.services.GameImportService;
import com.gamehaven.steam.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@SpringBootApplication
public class GameHavenApplication {


    public static void main(String[] args) {
        SpringApplication.run(GameHavenApplication.class, args);
    }

}
