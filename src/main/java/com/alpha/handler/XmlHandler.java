package com.alpha.handler;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;

public class XmlHandler extends Handler {
    public XmlHandler(File file, BufferedWriter writer) {
        super(file, writer);
    }
}
