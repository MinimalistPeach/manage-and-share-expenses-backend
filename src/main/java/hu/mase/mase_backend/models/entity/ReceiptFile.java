package hu.mase.mase_backend.models.entity;

import java.util.UUID;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "receipt_files")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReceiptFile {

    @Id
    @GeneratedValue(generator = "uuid2")
    private UUID id;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receipt_id", nullable = false)
    private Receipt receipt;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(nullable = false)
    private byte[] content;

    @Column(nullable = false)
    private String mimeType;

    @Column(nullable = false)
    private long size;

    public ReceiptFile(Receipt receipt, byte[] content, String mimeType, long size) {
        this.receipt = receipt;
        this.content = content;
        this.mimeType = mimeType;
        this.size = size;
    }

}
