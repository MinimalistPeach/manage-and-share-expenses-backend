package hu.mase.mase_backend.models.entity;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "receipts")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Receipt {

    @Id
    @GeneratedValue(generator = "uuid2")
    private UUID id;

    @Column(nullable = false)
    private String merchant;

    @Column(nullable = false)
    private Instant receiptDate;

    @Column(nullable = false)
    private Instant createdAt = Instant.now();

    @Column(precision = 10, scale = 2)
    private BigDecimal totalAmount;

    @OneToMany(mappedBy = "receipt", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ReceiptItem> items = new ArrayList<>();

    public void addItem(ReceiptItem item) {
        items.add(item);
        item.setReceipt(this);
    }

    public Receipt(String merchant, Instant receiptDate, List<ReceiptItem> items, BigDecimal totalAmount) {
        this.merchant = merchant;
        this.receiptDate = receiptDate;
        this.items = items;
        this.totalAmount = totalAmount;
    }

}
