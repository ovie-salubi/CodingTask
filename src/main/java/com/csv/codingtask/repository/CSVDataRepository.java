package com.csv.codingtask.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.csv.codingtask.model.CSVData;

public interface CSVDataRepository extends JpaRepository<CSVData, String> {
}