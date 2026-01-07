package hu.mase.mase_backend.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import hu.mase.mase_backend.models.entity.ReceiptFile;

public interface ReceiptFileRepository extends JpaRepository<ReceiptFile, UUID> {
    
}
