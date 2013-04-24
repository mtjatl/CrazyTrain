package com.computer;

import java.util.EnumSet;
import com.computer.Attribute;

/**
 * Represents a computer with name, memory (in GB), CPU speed (in GHz), type of video card
 * and price of the system (in dollars)
 * @author Matthew Jones
 *
 */
public class Computer {

	// Name of the computer
	private String name;
	
	// Amount of memory of the computer
	private int memory;
	
	// CPU speed 
	private int speed;
	
	// type of video card
	private VideoCard video;
	
	// Price of the system
	private int price;
	
	// Set of attributes of the computer
	private EnumSet<Attribute> attributes;
	
	
	/**
	 * Constructs a computer object with the give parameters
	 * @param name the name of the computer
	 * @param memory the amount of memory of the computer
	 * @param speed the speed of the CPU
	 * @param video the type of video card
	 * @param price the price of the computer
	 */
	public Computer(String name, int memory, int speed, VideoCard video, int price) {
		this.name = name;
		this.memory = memory;
		this.speed = speed;
		this.video = video;
		this.price = price;
		attributes = EnumSet.noneOf(Attribute.class);
		
	}
	
	/**
	 * Gets the name of the computer.
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Gets the amount of memory the computer has.
	 * @return the memory
	 */
	public int getMemory() {
		return memory;
	}

	/**
	 * Gets the speed of the CPU.
	 * @return the speed
	 */
	public int getSpeed() {
		return speed;
	}

	/**
	 * Gets the type of video card.
	 * @return the video
	 */
	public VideoCard getVideo() {
		return video;
	}

	/**
	 * Gets the price of the computer.
	 * @return the price
	 */
	public int getPrice() {
		return price;
	}
	
	/**
	 * Gets the attributes of the computer
	 * @return the attributes
	 */
	public EnumSet<Attribute> getAttributes() {
		return attributes;
	}
	
	/**
	 * Adds an attribute to the set of attributes of the computer
	 * @param a the attribute to be added
	 */
	public void addAttribute(Attribute a) {
		attributes.add(a);
	}
	
	
	
	/**
	 * determines if a computer contains a given attribute
	 * @param a the attribute to be checked
	 * @return true if the attribute is contained, else false
	 */
	public boolean containsAttribute(Attribute a) {
		if(attributes.contains(a)) {
			return true;
		}
		return false;
	}
	

}
