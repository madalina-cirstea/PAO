package com.data.header;

import com.patient.Patient;

import java.util.ArrayList;
import java.util.List;

public class PatientHeader implements Header {
    public List<String> create() {
        List<String> header = new ArrayList<String>();
        header.add("CNP");
        header.add("name");
        header.add("age");
        header.add("sex");
        header.add("assignedDoctor");
        header.add("details");
        return header;
    }
}
