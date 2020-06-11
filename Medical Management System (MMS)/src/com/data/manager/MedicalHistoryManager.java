package com.data.manager;

import com.medical.Drug;
import com.medical.MedicalCondition;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MedicalHistoryManager implements DataManager<MedicalCondition> {

    private static MedicalHistoryManager single_instance = null;

    private MedicalHistoryManager() {}

    public static MedicalHistoryManager MedicalHistoryManager()
    {
        // To ensure only one instance is created
        if (single_instance == null)
        {
            single_instance = new MedicalHistoryManager();
        }
        return single_instance;
    }

    public List<Drug> parseDrugs(String drugsString) {
        List<Drug> drugs = new ArrayList<Drug>();

        String drugsDelimiter = "\\|";
        String drugsInfoDelimiter = "\\^";

        List<String> drugsInfo = Arrays.asList(drugsString.split(drugsDelimiter));
        for (String drugInfo:drugsInfo) {
            List<String> info = Arrays.asList(drugInfo.split(drugsInfoDelimiter));
            drugs.add(new Drug(info.get(0), Integer.parseInt(info.get(1))));
        }

        return drugs;
    }


    public List<MedicalCondition> load(String fileName) {
        List<MedicalCondition> medicalConditions = new ArrayList<MedicalCondition>();

        List<List<String>> lines = read(fileName);

        for (int lineIndex = 1; lineIndex < lines.size(); lineIndex++) {
            List<String> line = lines.get(lineIndex);
            medicalConditions.add(new MedicalCondition(line.get(0), line.get(1), parseDrugs(line.get(2))));
        }

        return medicalConditions;
    }

    public String createLine(MedicalCondition medicalCondition, List<String> header) {
        StringBuilder line = new StringBuilder();
        for (int headerItemIndex = 0; headerItemIndex < header.size(); headerItemIndex++) {
            String headerItem = header.get(headerItemIndex);

            if (headerItemIndex != 0)
                line.append(',');

            switch (headerItem) {
                case "name":
                    line.append(medicalCondition.getNameOfIllness());
                    break;
                case "reportDate":
                    line.append(medicalCondition.getFirstDateOfReport());
                    break;
                case "drugs":
                    StringBuilder drugsString = new StringBuilder();
                    List<Drug> drugs = medicalCondition.getDrugs();
                    for (int drugIndex = 0; drugIndex < drugs.size(); drugIndex++) {
                        if (drugIndex != 0)
                            drugsString.append('|');
                        drugsString.append(drugs.get(drugIndex).getName() + '^' + drugs.get(drugIndex).getRecommendedDose());
                    }
                    line.append(drugsString);
                    break;
                default:
                    System.out.println("no match");
            }
        }
        return new String(line);
    }
}