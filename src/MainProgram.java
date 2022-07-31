import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

//Author: Evan Kumfer
//Description: Create a random encounter generator for dnd
//Last updated: 07/30/2022

public class MainProgram {
	public static void main(String args[]) throws Exception
	{
		Double averageLevelInput = 0.0;
		int numberOfPlayers = 0;
		boolean validInput = true;
		List<Monster> finalEncounter = new ArrayList<Monster>();
		Scanner input = new Scanner(System.in);

		do {
			try {
				System.out.println("Enter the average level of the party: ");
				averageLevelInput = input.nextDouble();

				if(averageLevelInput > 20 || averageLevelInput < 1)
				{
					validInput = false;
					System.out.println("Invalid input - average level must be between 1 aand 20");
				}
				else
					validInput = true;
			} catch (Exception e) {
				validInput = false;
				input.nextLine();
				System.out.println("Invalid Input - Average level must be a double");
			}
		} while (validInput == false);

		do {
			try {
				System.out.println("Enter the number of Player Characters: ");
				numberOfPlayers = input.nextInt();

				if(numberOfPlayers < 1)
				{
					validInput = false;
					System.out.println("Invalid input - Number of players must be greater than 0");
				}
				else
					validInput = true;
			} catch (Exception e) {
				validInput = false;
				input.nextLine();
				System.out.println("Invalid Input - Number of players must be a integer");
			}
			
		} while (validInput == false);


		EcounterChallengeRatingSystem encounterChallengeRatingSystem = new EcounterChallengeRatingSystem(averageLevelInput, numberOfPlayers);
		MonstersFromXML monsterList = new MonstersFromXML();
		
		List<Monster> monstersInEasyXpRange = GetMonsterListFromXMLWithXP(encounterChallengeRatingSystem.ExperiencePointValues[0], monsterList);
		List<Monster> monstersInModerateXpRange = GetMonsterListFromXMLWithXP(encounterChallengeRatingSystem.ExperiencePointValues[1], monsterList);
		List<Monster> monstersInChallengingXpRange = GetMonsterListFromXMLWithXP(encounterChallengeRatingSystem.ExperiencePointValues[2], monsterList);
		List<Monster> monstersInHardXpRange = GetMonsterListFromXMLWithXP(encounterChallengeRatingSystem.ExperiencePointValues[3], monsterList);
		List<Monster> bigBadBosses = GetBigBadBosses(encounterChallengeRatingSystem.ChallengeRating, monsterList);

		Monster randomMonsterInEasy = GetRandomMonsterFromList(monstersInEasyXpRange);
		Monster randomMonsterInModerate = GetRandomMonsterFromList(monstersInModerateXpRange);
		Monster randomMonsterInChallenging = GetRandomMonsterFromList(monstersInChallengingXpRange);
		Monster randomMonsterInHard = GetRandomMonsterFromList(monstersInHardXpRange);
		Monster randomBigBad = GetRandomMonsterFromList(bigBadBosses);

		System.out.println("Below are the following random encounters to choose from:");
		System.out.println("Easy encounter: " + randomMonsterInEasy.Name);
		System.out.println("Moderate encounter: " + randomMonsterInModerate.Name);
		System.out.println("Challenging encounter: " + randomMonsterInChallenging.Name);
		System.out.println("Hard encounter: " + randomMonsterInHard.Name);
		System.out.println("Boss encounter: " + randomBigBad.Name);

		input.nextLine();
		do {
			System.out.print("Please enter the name of the encounter you would like to choose: ");
			String encounterInput = input.nextLine().trim().toLowerCase();


			if(encounterInput.equals("easy") == true)
			{
				finalEncounter = GetFinalEncounterWithXpValues(randomMonsterInEasy, encounterChallengeRatingSystem.ExperiencePointValues[0], encounterChallengeRatingSystem.NumberOfPlayers);
				validInput = true;
			}
			else if(encounterInput.equals("moderate") == true)
			{
				finalEncounter = GetFinalEncounterWithXpValues(randomMonsterInModerate, encounterChallengeRatingSystem.ExperiencePointValues[1], encounterChallengeRatingSystem.NumberOfPlayers);
				validInput = true;
			}
			else if(encounterInput.equals("challenging") == true)
			{
				finalEncounter = GetFinalEncounterWithXpValues(randomMonsterInChallenging, encounterChallengeRatingSystem.ExperiencePointValues[2], encounterChallengeRatingSystem.NumberOfPlayers);
				validInput = true;
			}
			else if(encounterInput.equals("hard") == true)
			{
				finalEncounter = GetFinalEncounterWithXpValues(randomMonsterInHard, encounterChallengeRatingSystem.ExperiencePointValues[3], encounterChallengeRatingSystem.NumberOfPlayers);
				validInput = true;
			}
			else if(encounterInput.equals("boss") == true)
			{
				finalEncounter.add(randomBigBad);
				validInput = true;
			}
			else
			{
				validInput = false;
				System.out.println("Invalid input the options available to enter are: (Easy, Moderate, Challenging, Hard, Boss)");
			}
		} while (validInput == false);

		System.out.println("Below is the full monster details. There are " + finalEncounter.size() + " of the monster(s) below. Good luck, and may your party roll well. \n");

		finalEncounter.get(0).ToString();;
	}

	public static List<Monster> GetMonsterListFromXMLWithXP(int XP, MonstersFromXML monsterListIn)
	{
		List<Monster> Monsters = new ArrayList<Monster>();

		for(int i = 0; i < monsterListIn.Monsters.size(); i++)
		{
			if(monsterListIn.Monsters.get(i).XP <= XP)
			{
				Monsters.add(monsterListIn.Monsters.get(i));
			}
		}
		return Monsters;
	}

	private static List<Monster> GetBigBadBosses(double challengeRating, MonstersFromXML monsterList) {
		List<Monster> monsters = new ArrayList<Monster>();

		for(int i = 0; i < monsterList.Monsters.size(); i++)
		{
			if(monsterList.Monsters.get(i).ChallengeRating == challengeRating)
				monsters.add(monsterList.Monsters.get(i));
		}

		return monsters;
	}
	
	private static Monster GetRandomMonsterFromList(List<Monster> monstersInEasyXpRange) {
		Random rand = new Random();

		int randomInt = rand.nextInt(monstersInEasyXpRange.size());
		return monstersInEasyXpRange.get(randomInt);
	}

	private static List<Monster> GetFinalEncounterWithXpValues(Monster randomMonster, int XP, int numberOfPlayers) {
		List<Monster> monsters = new ArrayList<Monster>();
		double monsterQtyAsDouble = XP / randomMonster.XP;
		if(monsterQtyAsDouble >= numberOfPlayers * 3)
			monsterQtyAsDouble = monsterQtyAsDouble / 2;
		else if (monsterQtyAsDouble >= numberOfPlayers * 2)
			monsterQtyAsDouble = monsterQtyAsDouble / 1.5;

		int monsterQty = (int) monsterQtyAsDouble;

		for(int i = 0; i < monsterQty; i++)
			monsters.add(randomMonster);
		return monsters;
	}
}

