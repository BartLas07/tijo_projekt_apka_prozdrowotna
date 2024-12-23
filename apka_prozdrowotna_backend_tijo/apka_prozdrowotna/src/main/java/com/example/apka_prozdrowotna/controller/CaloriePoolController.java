package com.example.apka_prozdrowotna.controller;

import com.example.apka_prozdrowotna.model.dto.UserDetailsDTO;
import com.example.apka_prozdrowotna.repository.UserDetailsRepository;
import com.example.apka_prozdrowotna.service.UserDetailsService;
import com.example.apka_prozdrowotna.service.for_controller.CaloriePoolService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Slf4j
@CrossOrigin("*")
@Controller
@RequiredArgsConstructor
public class CaloriePoolController {


    private final CaloriePoolService caloriePoolService;
    private final UserDetailsService userDetailsService;
    private final UserDetailsRepository userDetailsRepository;

    @PostMapping("/api/postCaloriePool")
    public ResponseEntity<?> postCaloriePool(@RequestBody UserDetailsDTO userDetailsDTO) {
        try {
            Integer caloriePool = caloriePoolService.calculateCaloriePool(userDetailsDTO.getGender(),userDetailsDTO.getAge(),userDetailsDTO.getWeight(),
                    userDetailsDTO.getHeight(),userDetailsDTO.getActivityLevel(),userDetailsDTO.getWeightPreference());
            if(!userDetailsRepository.findAllByCaloriePoolNotNull().isEmpty()){
                userDetailsService.deleteAllUserDetailsWithCaloriePoolNotNull();
            }
            userDetailsService.saveUserDetails(caloriePool,null,null);
            return ResponseEntity.ok(caloriePool);
        } catch (Exception e) {
            log.error("Invalid JSON data: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid JSON data");
        }
    }

}
