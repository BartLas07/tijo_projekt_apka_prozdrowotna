package com.example.apka_prozdrowotna.repository;

import com.example.apka_prozdrowotna.model.DateDietDay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DietDayRepository extends JpaRepository<DateDietDay, Integer> {

}
