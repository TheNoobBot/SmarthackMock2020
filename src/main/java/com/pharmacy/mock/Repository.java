package com.pharmacy.mock;

import com.pharmacy.mock.model.Medicine;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.*;

@Component
public class Repository {
    private List<Medicine> data = new ArrayList<>();
    Random random = new Random();

    public Repository() {
        this.data = this.openCSV();
    }

    public boolean addMedicine(Medicine company) {
        this.data.add(company);
        return true;
    }

    public List<Medicine> getMedicine() {
        return this.data;
    }

    public List<Medicine> searchMedicine(String activeSubstance, String concentration) {
        Set<Medicine> toReturn = new HashSet<>();
        activeSubstance = activeSubstance.toLowerCase();
        concentration = concentration.toLowerCase();
        for (Medicine medicine : this.data) {
            if(medicine.getQuantity()==0) continue;
            if(toReturn.size()==10) break;
            if (medicine.getActiveSubstance().equals(activeSubstance)
                && medicine.getConcentration().equals(concentration)) {
                toReturn.add(medicine);
            }
        }
        for(Medicine medicine: this.data){
            if(toReturn.size()==10) break;
            if (medicine.getActiveSubstance().contains(activeSubstance)){
                toReturn.add(medicine);
            }
        }

        return new ArrayList<>(toReturn);
    }

    private Medicine getMedicineFromLine(String line) {
        Medicine medicine = new Medicine();
        int num,max=999,min=10;
        try (Scanner rowScanner = new Scanner(line)) {
            rowScanner.useDelimiter(",");
            if(rowScanner.hasNext()){
                medicine.setId(rowScanner.next().toLowerCase());
            }
            if(rowScanner.hasNext()){
                medicine.setName(rowScanner.next().toLowerCase());
            }
            if(rowScanner.hasNext()){
                medicine.setActiveSubstance(rowScanner.next().toLowerCase());
            }
            if(rowScanner.hasNext()){
                medicine.setConcentration(rowScanner.next().toLowerCase());
            }
            medicine.setPrice(random.nextInt(max-min) + 10);
            medicine.setQuantity(random.nextInt(max-min) + 100);
        }
        return medicine;
    }

    private List<Medicine> openCSV(){
        List<Medicine> records = new ArrayList<>();
        try {
            try (Scanner scanner = new Scanner(getClass().getResourceAsStream("/nomenclator.csv"));) {
                while (scanner.hasNextLine()) {
                    records.add(getMedicineFromLine(scanner.nextLine()));
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return records;
    }
}
