package hu.mase.mase_backend.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import hu.mase.mase_backend.models.entity.Receipt;

public interface ReceiptRepository extends JpaRepository<Receipt, UUID> {
    
}
