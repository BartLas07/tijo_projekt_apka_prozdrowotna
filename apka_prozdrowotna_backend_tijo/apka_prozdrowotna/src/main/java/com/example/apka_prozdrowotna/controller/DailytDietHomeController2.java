package com.example.apka_prozdrowotna.controller;

import com.example.apka_prozdrowotna.model.UserDetails;
import com.example.apka_prozdrowotna.repository.UserDetailsRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Slf4j
@CrossOrigin("*")
@Controller
@RequiredArgsConstructor
public class DailytDietHomeController2 {

    private final UserDetailsRepository userDetailsRepository;

    @ResponseBody
    @GetMapping("/getBmi")
    public ResponseEntity<UserDetails> getBmi() {
        try {
            UserDetails userDetailswithBmi = userDetailsRepository.findByBmiNotNull();
            return ResponseEntity.ok(userDetailswithBmi);
        } catch (Exception e) {
            log.error("Error fetching Bmi for '{}': ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @ResponseBody
    @GetMapping("/getCaloriePool")
    public ResponseEntity<UserDetails> getCaloriePool() {
        try {
            UserDetails userDetailswithCaloriePool = userDetailsRepository.findByCaloriePoolNotNull();
            return ResponseEntity.ok(userDetailswithCaloriePool);
        } catch (Exception e) {
            log.error("Error fetching ingredients for '{}': ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @ResponseBody
    @GetMapping("/getRecommendedHydration")
    public ResponseEntity<UserDetails> getRecommendedHydration() {
        try {
            UserDetails userDetailswithRecommendedHydration = userDetailsRepository.findByRecommendedHydrationNotNull();
            return ResponseEntity.ok(userDetailswithRecommendedHydration);
        } catch (Exception e) {
            log.error("Error fetching ingredients for '{}': ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
