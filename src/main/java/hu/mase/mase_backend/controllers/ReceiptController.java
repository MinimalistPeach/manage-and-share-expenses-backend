package hu.mase.mase_backend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hu.mase.mase_backend.models.dto.ReceiptListDTO;
import hu.mase.mase_backend.models.dto.ReceiptSaveDTO;
import hu.mase.mase_backend.services.ReceiptService;

@RestController
@RequestMapping("/receipt")
public class ReceiptController {

    @Autowired
    private ReceiptService receiptService;

    @GetMapping("/getAll")
    public ResponseEntity<List<ReceiptListDTO>> getAllReceipt() {
        List<ReceiptListDTO> receiptListDTOs = receiptService.getAllReceipts();
        return ResponseEntity.ok(receiptListDTOs);
    }

    @PostMapping("/")
    public ResponseEntity<String> saveReceipt(@RequestBody ReceiptSaveDTO receiptSaveDTO) {
        receiptService.saveReceipt(receiptSaveDTO);
        return ResponseEntity.ok("Receipt saved successfully");
    }

}
