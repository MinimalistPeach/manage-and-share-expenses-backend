package hu.mase.mase_backend.services;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.mase.mase_backend.models.dto.ReceiptListDTO;
import hu.mase.mase_backend.models.dto.ReceiptSaveDTO;
import hu.mase.mase_backend.models.entity.Receipt;
import hu.mase.mase_backend.models.entity.ReceiptFile;
import hu.mase.mase_backend.models.entity.ReceiptItem;
import hu.mase.mase_backend.repositories.ReceiptFileRepository;
import hu.mase.mase_backend.repositories.ReceiptRepository;

@Service
public class ReceiptService {

    @Autowired
    private ReceiptRepository receiptRepository;

    @Autowired
    private ReceiptFileRepository receiptFileRepository;

    public void saveReceipt(ReceiptSaveDTO receiptSaveDTO) {
        List<ReceiptItem> items = new ArrayList<>();
        receiptSaveDTO.getItems().forEach(itemDTO -> {
            ReceiptItem item = new ReceiptItem(
                    itemDTO.getName(),
                    itemDTO.getPrice());
            items.add(item);
        });
        Receipt receiptToBeSaved = new Receipt(
                receiptSaveDTO.getMerchant(),
                receiptSaveDTO.getReceiptDate(),
                items,
                receiptSaveDTO.getTotalAmount());
        receiptRepository.save(receiptToBeSaved);
        byte[] imageBytes = Base64.getDecoder().decode(receiptSaveDTO.getContent());
        receiptFileRepository.save(new ReceiptFile(receiptToBeSaved,
                imageBytes,
                receiptSaveDTO.getMimeType(),
                receiptSaveDTO.getSize()));
    }

    public List<ReceiptListDTO> getAllReceipts() {

        List<Receipt> receipts = receiptRepository.findAll();
        List<ReceiptListDTO> receiptListDTOs = new ArrayList<>();

        receipts.forEach(receipt -> {
            ReceiptListDTO dto = new ReceiptListDTO();
            dto.setMerchant(receipt.getMerchant());
            dto.setReceiptDate(receipt.getReceiptDate());
            dto.setTotalAmount(receipt.getTotalAmount());
            receiptListDTOs.add(dto);
        });
        return receiptListDTOs;
    }

}
