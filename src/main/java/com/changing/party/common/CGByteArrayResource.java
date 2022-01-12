package com.changing.party.common;

import org.springframework.core.io.ByteArrayResource;

public class CGByteArrayResource extends ByteArrayResource {
    public CGByteArrayResource(byte[] byteArray) {
        super(byteArray);
    }

    @Override
    public String getFilename() {
        return "image.png";
    }
}
