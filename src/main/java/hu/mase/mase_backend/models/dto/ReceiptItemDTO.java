package hu.mase.mase_backend.models.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ReceiptItemDTO {
    private String name;

    private BigDecimal price;
}
