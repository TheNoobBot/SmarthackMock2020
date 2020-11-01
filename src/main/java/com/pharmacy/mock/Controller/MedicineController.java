package com.pharmacy.mock.Controller;

import com.pharmacy.mock.Repository;
import com.pharmacy.mock.model.Medicine;
import com.pharmacy.mock.model.requests.MedicineSearchRequest;
import com.pharmacy.mock.model.requests.OrderRequest;
import com.pharmacy.mock.model.requests.SearchMedicineRequest;
import com.pharmacy.mock.model.responses.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/home-pharma")
public class MedicineController implements com.pharmacy.api.HomePharmacyAPI {

    @Autowired
    Repository repository;

    @Value("${name:DEFAULT}")
    private String appId;

    @Override
    public MedicineSearchResponse searchMedicine(MedicineSearchRequest medicineSearchRequest) {
        MedicineSearchResponse medicineSearchResponse = new MedicineSearchResponse();
        List<SearchMedicineResponse> searchMedicineResponses = new ArrayList<>();
        for (SearchMedicineRequest medicineRequest : medicineSearchRequest.getPreSearchedMedicines()
        ) {
            SearchMedicineResponse searchMedicineResponse = new SearchMedicineResponse();

            searchMedicineResponse.setActiveSubstance(medicineRequest.getActiveSubstance());
            searchMedicineResponse.setAmountOfActiveSubstance(medicineRequest.getAmountOfActiveSubstance());

            List<Medicine> medicineList = this.repository
                .searchMedicine(medicineRequest.getActiveSubstance(), medicineRequest.getAmountOfActiveSubstance());

            List<MedicineResponse> medicineResponseList = new ArrayList<>();

            for (Medicine medicine: medicineList
                 ) {
                MedicineResponse medicineResponse = new MedicineResponse();

                medicineResponse.setResourceId(medicine.getId());
                medicineResponse.setInventory(medicine.getQuantity());
                medicineResponse.setTitle(medicine.getName());
                medicineResponse.setPharmacyId(appId);
                medicineResponse.setPrice(medicine.getPrice());

                medicineResponseList.add(medicineResponse);
            }

            searchMedicineResponse.setMedicines(medicineResponseList);
            searchMedicineResponses.add(searchMedicineResponse);
        }

        medicineSearchResponse.setSearchMedicineResponse(searchMedicineResponses);
        return medicineSearchResponse;
    }

    @Override
    public OrderResponse orderMedicines(OrderRequest orderRequest) {
        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setOrderResponseStatus(OrderResponseStatus.OK);
        return orderResponse;
    }
}
