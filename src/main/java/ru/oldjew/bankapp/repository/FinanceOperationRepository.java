package ru.oldjew.bankapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.oldjew.bankapp.model.FinanceOperation;

import java.util.List;

public interface FinanceOperationRepository extends JpaRepository<FinanceOperation, Long> {

    @Query("select f from FIN_OPERATIONS f where f.user.id = ?1")
    List<FinanceOperation> findOperationsByUserId(Long userId);
}
