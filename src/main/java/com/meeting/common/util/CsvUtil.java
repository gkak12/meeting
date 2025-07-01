package com.meeting.common.util;

import java.nio.charset.StandardCharsets;

public class CsvUtil {

    public static byte[] downloadCsvFile(StringBuilder sb){
        byte[] bom = new byte[]{(byte) 0xEF, (byte) 0xBB, (byte) 0xBF};
        byte[] data = sb.toString().getBytes(StandardCharsets.UTF_8);
        byte[] body = new byte[bom.length + data.length];
        System.arraycopy(bom, 0, body, 0, bom.length);
        System.arraycopy(data, 0, body, bom.length, data.length);

        return body;
    }
}
