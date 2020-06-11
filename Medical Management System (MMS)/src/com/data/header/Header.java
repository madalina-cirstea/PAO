package com.data.header;

import java.util.List;

public interface Header {
    public List<String> create();

    public default String toLine(List<String> header) {
        StringBuilder headerLine = new StringBuilder();
        headerLine.append(header.get(0));
        for (int i = 1; i < header.size(); i++) {
            headerLine.append(',');
            headerLine.append(header.get(i));
        }
        return new String(headerLine);
    }
}
