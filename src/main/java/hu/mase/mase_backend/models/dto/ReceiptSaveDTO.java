package hu.mase.mase_backend.models.dto;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ReceiptSaveDTO {

    private String merchant;

    private Instant receiptDate;

    private BigDecimal totalAmount;

    private String content;

    private String mimeType;

    private long size;

    private List<ReceiptItemDTO> items = new ArrayList<>();

}
