package com.alpha.handler;

import java.io.*;


public class JsonHandler extends Handler{
    public JsonHandler(File file, BufferedWriter writer) throws FileNotFoundException {
        super(file, writer);
    }
}

