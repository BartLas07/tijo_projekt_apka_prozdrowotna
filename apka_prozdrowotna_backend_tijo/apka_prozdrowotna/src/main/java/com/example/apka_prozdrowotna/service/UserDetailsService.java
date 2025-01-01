package com.example.apka_prozdrowotna.service;

import com.example.apka_prozdrowotna.model.UserDetails;
import com.example.apka_prozdrowotna.model.meal_period.Snacks;
import com.example.apka_prozdrowotna.repository.UserDetailsRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsService {

    private final UserDetailsRepository userDetailsRepository;

    @Transactional
    public UserDetails saveUserDetails(Integer caloriePool, Double recommendedHydration, Double bmi) {
        UserDetails userDetails = new UserDetails(caloriePool, recommendedHydration,bmi);
        return userDetailsRepository.save(userDetails);
    }

    @Transactional
    public void deleteAllUserDetailsWithBmiNotNull() {
       userDetailsRepository.deleteAllByBmiNotNull();
    }

    @Transactional
    public void deleteAllUserDetailsWithCaloriePoolNotNull() {
        userDetailsRepository.deleteAllByCaloriePoolNotNull();
    }

    @Transactional
    public void deleteAllUserDetailsWithRecommendedHydrationNotNull() {
        userDetailsRepository.deleteAllByRecommendedHydrationNotNull();
    }


}
