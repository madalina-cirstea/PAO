package com.data.header;

import com.data.header.Header;
import com.medical.MedicalCondition;

import java.util.ArrayList;
import java.util.List;

public class MedicalHistoryHeader implements Header {
    public List<String> create() {
        List<String> header = new ArrayList<String>();
        header.add("name");
        header.add("reportDate");
        header.add("drugs");
        return header;
    }
}
