package com.company.account.repository;

import com.company.account.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransActionRepository extends JpaRepository<Transaction,String> {
}
