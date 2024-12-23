package com.example.apka_prozdrowotna.controller;

import com.example.apka_prozdrowotna.model.dto.UserDetailsDTO;
import com.example.apka_prozdrowotna.repository.UserDetailsRepository;
import com.example.apka_prozdrowotna.service.UserDetailsService;
import com.example.apka_prozdrowotna.service.for_controller.BmiService;
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
public class BmiController {

    private final BmiService bmiService;
    private final UserDetailsService userDetailsService;
    private final UserDetailsRepository userDetailsRepository;

    @PostMapping("/api/postBmi")
    public ResponseEntity<?> postBmi(@RequestBody UserDetailsDTO userDetailsDTO) {
        try {
            Double bmi = bmiService.calculateBmi(userDetailsDTO.getWeight(),userDetailsDTO.getHeight());
            if(!userDetailsRepository.findAllByBmiNotNull().isEmpty()){
                userDetailsService.deleteAllUserDetailsWithBmiNotNull();
            }
            userDetailsService.saveUserDetails(null,null,bmi);
            return ResponseEntity.ok(bmi);
        } catch (Exception e) {
            log.error("Invalid JSON data: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid JSON data");
        }
    }
}
