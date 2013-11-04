package com.example.algo;

public class Key {

	private int start;
	private int twist;
	
	public Key(int start, int twist){
		this.start = start;
		this.twist = twist;
	}
	
	public int getStart(){
		return this.start;
	}
	
	public int getTwist(){
		return this.twist;
	}
	
	public String toString(){
		return ( + this.start + "," + this.twist); 
	}
}
