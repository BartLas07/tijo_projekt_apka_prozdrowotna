package com.example.apka_prozdrowotna.repository;

import com.example.apka_prozdrowotna.model.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserDetailsRepository extends JpaRepository<UserDetails, Integer> {
    UserDetails findByBmiNotNull();
    UserDetails findByCaloriePoolNotNull();
    UserDetails findByRecommendedHydrationNotNull();
    List<UserDetails> findAllByBmiNotNull();
    List<UserDetails> findAllByCaloriePoolNotNull();
    List<UserDetails> findAllByRecommendedHydrationNotNull();
    void deleteAllByBmiNotNull();
    void deleteAllByCaloriePoolNotNull();
    void deleteAllByRecommendedHydrationNotNull();
}
