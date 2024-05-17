package com.example.backendsp;

import com.example.backendsp.DAO.entities.Manga;
import com.example.backendsp.DAO.entities.User;
import com.example.backendsp.DAO.repo.UserRepo;
import com.example.backendsp.service.MangaServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.io.File;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class BackendSpApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendSpApplication.class, args);
    }

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
        corsConfiguration.setAllowedHeaders(Arrays.asList("Origin", "Access-Control-Allow-Origin", "Content-Type",
                "Accept", "Authorization", "Origin, Accept", "X-Requested-With",
                "Access-Control-Request-Method", "Access-Control-Request-Headers"));
        corsConfiguration.setExposedHeaders(Arrays.asList("Origin", "Content-Type", "Accept", "Authorization",
                "Access-Control-Allow-Origin", "Access-Control-Allow-Origin", "Access-Control-Allow-Credentials"));
        corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
        return new CorsFilter(urlBasedCorsConfigurationSource);
    }
    private static final String Hostname = "http://localhost:8082/public/img/manga-covers/";
    private static final String Local = "src/main/resources/static/public/img/manga-covers";
    @Autowired
    MangaServices mangaServices;

    @Autowired
    UserRepo userRepo;
    @Bean
    CommandLineRunner start(){
        return args -> {

//            for (int i = 0; i < 20; i++) {
////                Manga c =mangaServices.saveManga(new Manga(,"akagame","url/","Desc",100,null));
////                mangaServices.saveManga(c);
//                Manga manga = new Manga();
//                manga.setTitle("akagame");
//                manga.setImgUrl("/im");
//                manga.setDescription("Desc");
//                manga.setPrice(100);
//                mangaServices.saveManga(manga);
//            }
            User user=new User(null ,"test","test@test","test",null,"User");
            User user1=new User(null ,"test1","test1@test","test",null,"User");
//                    .name("test")
//                    .email("test@test")
//                    .password("test")
//                    .Role("User")
//                    .build();

            User u =userRepo.save(user);
            User u1 =userRepo.save(user1);
            System.out.println(u);
            File folder = new File(Local);
            File[] listOfFiles = folder.listFiles();
            if (listOfFiles != null) {
                for (int i = 0; i < listOfFiles.length; i++) {
                    if (listOfFiles[i].isFile()) {
                        String imageName = listOfFiles[i].getName();
                        String mangaName = imageName.substring(0, imageName.indexOf("."));

                        Manga manga = new Manga();
                        manga.setTitle(mangaName);
                        manga.setImgUrl(Hostname + imageName);
                        manga.setDescription("Description goes here");
                        manga.setPrice(1000);
                        mangaServices.saveManga(manga);
                    }
                }
            }

        };
    }
}
