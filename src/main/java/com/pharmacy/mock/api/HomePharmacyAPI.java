package com.pharmacy.api;

import com.pharmacy.mock.model.requests.MedicineSearchRequest;
import com.pharmacy.mock.model.requests.OrderRequest;
import com.pharmacy.mock.model.responses.MedicineSearchResponse;
import com.pharmacy.mock.model.responses.OrderResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/home-pharma")
public interface HomePharmacyAPI {

    @PostMapping
    MedicineSearchResponse searchMedicine(@RequestBody MedicineSearchRequest medicineSearchRequest);

    @PostMapping("/order")
    OrderResponse orderMedicines(@RequestBody OrderRequest orderRequest);

}
