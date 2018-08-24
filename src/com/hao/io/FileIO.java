package com.hao.io;

//³éÏóÀà£¬¸¸Àà
public abstract class FileIO {
	public abstract void checkFile(String filename);
	public abstract void write(String data,boolean mode);
	public abstract String read();
}
