package com.book.helper;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class MyAppendObjectOutputStream extends ObjectOutputStream {


	public MyAppendObjectOutputStream(OutputStream arg0) throws IOException {
		super(arg0);
		
	}

	@Override
	protected void writeStreamHeader() throws IOException {
        reset();
    }


	
}
