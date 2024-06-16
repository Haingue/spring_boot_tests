package com.haingue.azure.sso;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class MainController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MainController.class);

    @GetMapping
    public ResponseEntity<String> getHome () {
        LOGGER.info("Load home page");
        return ResponseEntity.ok("Hello world !");
    }

    @GetMapping("/graph")
    public ResponseEntity<OAuth2AuthorizedClient> graph(
            @RegisteredOAuth2AuthorizedClient("graph") OAuth2AuthorizedClient graphClient
    ) {
        LOGGER.info("Load graph page");
        // toJsonString() is just a demo.
        // oAuth2AuthorizedClient contains access_token. We can use this access_token to access the resource server.
        return ResponseEntity.ok(graphClient);
    }

    @GetMapping("/info")
    public ResponseEntity<String> getInfo (Principal principal) {
        LOGGER.info("Load getInfo");
        if (principal == null) return ResponseEntity.ok(null);
        return ResponseEntity.ok(principal.toString());
    }

    @GetMapping("/ping")
    public ResponseEntity<String> getPing () {
        LOGGER.info("Load getPing");
        return ResponseEntity.ok("Ping !");
    }

    @GetMapping("/admin/ping")
    public ResponseEntity<String> getAdminPing () {
        LOGGER.info("Load getAdminPing");
        return ResponseEntity.ok("Ping as admin !");
    }

    @GetMapping("/manager/ping")
    public ResponseEntity<String> getManagerPing () {
        LOGGER.info("Load getManagerPing");
        return ResponseEntity.ok("Ping as manager !");
    }

    @GetMapping("/public/ping")
    public ResponseEntity<String> getPublicPing () {
        LOGGER.info("Load getPublicPing");
        return ResponseEntity.ok("Ping as public user !");
    }
}
