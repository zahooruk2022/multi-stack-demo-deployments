package com.example.dbdemo.controller;

import com.example.dbdemo.config.AppConfig;
import com.example.dbdemo.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    @Autowired
    private AppConfig appConfig;

    @Autowired
    private PetRepository petRepository;

    @Value("${spring.datasource.url}")
    private String datasourceUrl;

    @GetMapping("/")
    public String index(Model model) {
        String javaVersion = System.getProperty("java.version");
        String springBootVersion = org.springframework.boot.SpringBootVersion.getVersion();

        // Auto-detect database type from JDBC URL
        String databaseType = detectDatabaseType(datasourceUrl);

        model.addAttribute("uuid", appConfig.getUuid());
        model.addAttribute("version", appConfig.getVersion());
        model.addAttribute("deploymentColor", appConfig.getDeploymentColor());
        model.addAttribute("framework", "Spring Boot");
        model.addAttribute("frameworkVersion", springBootVersion != null ? springBootVersion : "3.5.7");
        model.addAttribute("language", "Java");
        model.addAttribute("languageVersion", javaVersion);
        model.addAttribute("runtime", "JVM");
        model.addAttribute("database", databaseType);
        model.addAttribute("pets", petRepository.findAll());

        return "index";
    }

    private String detectDatabaseType(String jdbcUrl) {
        if (jdbcUrl == null) {
            return "Unknown Database";
        }
        if (jdbcUrl.contains("mysql")) {
            return "MySQL";
        } else if (jdbcUrl.contains("postgresql")) {
            return "PostgreSQL";
        }
        return "Unknown Database";
    }
}
