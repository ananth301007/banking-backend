package com.example.banking_backend.repository;

import com.example.banking_backend.entity.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TransferRepository extends JpaRepository<Transfer, Long> {


    List<Transfer> findByFromAccountIdOrderByTimestampDesc(String fromAccountId);

    List<Transfer> findByToAccountIdOrderByTimestampDesc(String toAccountId);
}

