package hu.mase.mase_backend.models.dto;

import java.math.BigDecimal;
import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ReceiptListDTO {

    private String merchant;

    private Instant receiptDate;

    private BigDecimal totalAmount;

}
