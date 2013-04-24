package com.computer;


import java.util.ArrayList;

import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;
import org.drools.runtime.StatefulKnowledgeSession;




/**
 * Test that the rules are applied correctly and answers the questions that are provided.
 * @author Matthew Jones
 *
 */
public class testComputer {

	/**
	 * Create an array of the available computers.
	 * Fire all rules at the computers.
	 * Answer the provided questions.
	 * @param args Not used
	 */
	public static void main(String[] args) {
		
		StatefulKnowledgeSession session = null;
		
		//Create the computers
		Computer machine1 = new Computer("Jack (machine1)", 2, 5, VideoCard.NONE, 800);
		Computer machine2 = new Computer("Kelly (machine2)", 8, 3, VideoCard.AVERAGE, 1500);
		Computer machine3 = new Computer("Aimee (machine3)", 1, 1, VideoCard.NONE, 400);
		Computer machine4 = new Computer("Sharon (machine4)", 16, 4, VideoCard.PREMIUM, 3000);
		Computer machine5 = new Computer("Ozzy (machine5)", 32, 5, VideoCard.NONE, 8000);
		Computer machine6 = new Computer("Robert (machine6)", 2, 2, VideoCard.PREMIUM, 5500);
		Computer[] computers = {machine1, machine2, machine3, machine4, machine5};
		ArrayList<Computer> computerList = new ArrayList<Computer>();
		for(int i = 0; i < 5; i++) {
			computerList.add(computers[i]);
		}
		
		// Create session and fire all rules
		try {
			KnowledgeBuilder builder = KnowledgeBuilderFactory.newKnowledgeBuilder();
			builder.add(ResourceFactory.newClassPathResource("com/computer/rules/computerTypeRules.drl"), ResourceType.DRL);
			if(builder.hasErrors()) {
				throw new RuntimeException(builder.getErrors().toString());
			}
			KnowledgeBase knowledgeBase = KnowledgeBaseFactory.newKnowledgeBase();
			knowledgeBase.addKnowledgePackages(builder.getKnowledgePackages());
			session = knowledgeBase.newStatefulKnowledgeSession();
			
			for(int i = 0; i < computers.length; i++) {
				for(int j = i + 1; j < computers.length; j++) {
					session.insert(computers[i]);
					session.insert(computers[j]);
					session.fireAllRules();
					session.dispose();
					session = knowledgeBase.newStatefulKnowledgeSession();
				}
				;
			}
			session.insert(machine6);
			session.fireAllRules();
			/*
			FactHandle computerFact = session.insert(machine1);
			session.fireAllRules();
			session.update(computerFact, machine2);
			session.fireAllRules();
			session.update(computerFact, machine3);
			session.fireAllRules();
			session.update(computerFact, machine4);
			session.fireAllRules();
			session.update(computerFact, machine5);
			session.fireAllRules();
			session.update(computerFact, machine6);
			session.fireAllRules();
			*/
			
			/*
			for(int i = 0; i < computers.length; i++) {
				session.insert(computers[i]);
			}
			
			session.insert(machine1);
			session.fireAllRules();
			*/
		} catch(Throwable t) {
			t.printStackTrace();
		} finally {
			session.dispose();
		}
		
		System.out.println();
		
		//Answer three provided quesitons
		safeGame(computers);
		riskyCrunch(computers);
		hotGame(computers);
		
		// Describe machine 6
		System.out.println(machine6.getName() + " has the following properties");
		System.out.println(machine6.getAttributes());
		
	}
	
	/*
	 * Determines if there are any safe gaming computers
	 */
	private static void safeGame(Computer[] computers) {
		System.out.println("The options for a safe gaming computer are: ");
		boolean exist = false;
		for(int i = 0; i < computers.length; i++) {
			if(computers[i].getAttributes().contains(Attribute.GAME) && 
					!computers[i].getAttributes().contains(Attribute.RISKY)) {
				System.out.println(computers[i].getName());
				exist = true;
			}
		}
		if(!exist) {
			System.out.println("There are no safe gaming computers.");
		}
		System.out.println();
	}
	
	/*
	 * determines if there are any risky computers that can crunch numbers
	 */
	private static void riskyCrunch(Computer[] computers) {
		System.out.println("The following are risky number crunchers: ");
		boolean exist = false;
		for(int i = 0; i < computers.length; i++) {
			if(computers[i].getAttributes().contains(Attribute.RISKY) && 
					computers[i].getAttributes().contains(Attribute.CRUNCH)) {
				System.out.println(computers[i].getName());
				exist = true;
			}
		}
		if(!exist) {
			System.out.println("There are no number crunchers that are risky.");
		}
		System.out.println();
	}
	
	/*
	 * determines if there are any hot gaming computers
	 */
	
	private static void hotGame(Computer[] computers) {
		System.out.println("The following are hot gaming computers: ");
		boolean exist = false;
		for(int i = 0; i < computers.length; i++) {
			if(computers[i].getAttributes().contains(Attribute.HOT) && 
					computers[i].getAttributes().contains(Attribute.GAME)) {
				System.out.println(computers[i].getName());
				exist = true;
			}
		}
		if(!exist) {
			System.out.println("There are no number hot gaming computers available.");
		}
		System.out.println();
	}

}
