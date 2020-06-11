package com.data.header;

import com.medical.Doctor;

import java.util.ArrayList;
import java.util.List;

public class DoctorHeader implements Header {
    public List<String> create() {
        List<String> header = new ArrayList<String>();
        header.add("specialization");
        header.add("CNP");
        header.add("name");
        header.add("age");
        header.add("sex");
        return header;
    }
}
