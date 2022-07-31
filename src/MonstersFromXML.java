import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class MonstersFromXML {
	
	public List<Monster> Monsters = new ArrayList<Monster>();
	
	public MonstersFromXML() throws Exception {
		File xml = new File("src/MonsterList.xml");
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.parse(xml);
		
		NodeList listOfNodes = document.getElementsByTagName("monster");
		
		for(int i = 0; i < listOfNodes.getLength(); i++)
		{
			Node monsterNode = listOfNodes.item(i);
			Element monster = (Element) monsterNode;
			
			Monsters.add(ConvertNodeToMonster(monster));
		}
	}

	private Monster ConvertNodeToMonster(Element monster) {
		Monster monsterToAdd = new Monster();
		monsterToAdd.Name = GetNameFromXml(monster);
		monsterToAdd.Size = GetSizeFromXml(monster);
		monsterToAdd.Type = GetTypeFromXml(monster);
		monsterToAdd.Alignment = GetAlignmentFromXml(monster);
		monsterToAdd.AC = GetACFromXml(monster);
		monsterToAdd.HP = GetHpFromXml(monster);
		monsterToAdd.Speed = GetSpeedFromXml(monster);
		monsterToAdd.AbilityScores = GetAbilityScoresFromXml(monster);
		monsterToAdd.Saves = GetSavesFromXml(monster);
		monsterToAdd.Skills = GetSkillsFromXml(monster);
		monsterToAdd.Immunities = GetImmunitiesFromXml(monster);
		monsterToAdd.Senses = GetSenseFromXml(monster);
		monsterToAdd.PassivePerception = GetPassivePerceptionFromXml(monster);
		monsterToAdd.Languages = GetLanguagesFromXml(monster);
		monsterToAdd.ChallengeRating = GetChallengeRatingFromXml(monster);
		monsterToAdd.XP = SetXPValue(monsterToAdd.ChallengeRating);
		monsterToAdd.Traits = GetTraitsFromXml(monster);
		monsterToAdd.Actions = GetActionsFromXML(monster);
		monsterToAdd.Reactions = GetReactionsFromXML(monster);
		monsterToAdd.LegendaryActions = GetLegendaryActionsFromXML(monster);
		
		return monsterToAdd;
	}






	private String GetNameFromXml(Element monster) {
		return monster.getElementsByTagName("name").item(0).getTextContent();
	}

	private String GetSizeFromXml(Element monster) {
		return monster.getElementsByTagName("size").item(0).getTextContent();
	}
	
	private String GetTypeFromXml(Element monster) {
		return monster.getElementsByTagName("type").item(0).getTextContent();
	}

	private String GetAlignmentFromXml(Element monster) {
		return monster.getElementsByTagName("alignment").item(0).getTextContent();
	}

	private int GetACFromXml(Element monster) {
		String armorClassAsString = monster.getElementsByTagName("ac").item(0).getTextContent();
		int armorClass;
		if(armorClassAsString.length() > 2)
		{
			String[] armorClassAsStringSplit = armorClassAsString.split(" ");
			armorClass = Integer.parseInt(armorClassAsStringSplit[0]);
		}
		else
		{
			armorClass = Integer.parseInt(armorClassAsString);
		}
		return armorClass;
	}

	private String GetHpFromXml(Element monster) {
		return monster.getElementsByTagName("hp").item(0).getTextContent();
	}

	private String GetSpeedFromXml(Element monster) {
		return monster.getElementsByTagName("speed").item(0).getTextContent();
	}

	private List<AbilityScore> GetAbilityScoresFromXml(Element monster) {
		List<AbilityScore> abilityScores = new ArrayList<AbilityScore>();
		abilityScores.add(new Strength(Integer.parseInt(monster.getElementsByTagName("str").item(0).getTextContent())));
		abilityScores.add(new Dexterity(Integer.parseInt(monster.getElementsByTagName("dex").item(0).getTextContent())));
		abilityScores.add(new Constitution(Integer.parseInt(monster.getElementsByTagName("con").item(0).getTextContent())));
		abilityScores.add(new Intelligence(Integer.parseInt(monster.getElementsByTagName("int").item(0).getTextContent())));
		abilityScores.add(new Wisdom(Integer.parseInt(monster.getElementsByTagName("wis").item(0).getTextContent())));
		abilityScores.add(new Charisma(Integer.parseInt(monster.getElementsByTagName("cha").item(0).getTextContent())));
		
		return abilityScores;
	}

	private String GetSavesFromXml(Element monster) {
		if(monster.getElementsByTagName("save").item(0) != null)
			return monster.getElementsByTagName("save").item(0).getTextContent();
		else
			return "";
	}
	
	private String GetSkillsFromXml(Element monster) {
		if(monster.getElementsByTagName("skill").item(0) != null)
			return monster.getElementsByTagName("skill").item(0).getTextContent();
		else	
			return "";
	}

	private String GetImmunitiesFromXml(Element monster) {
		if(monster.getElementsByTagName("immune").item(0) != null)
			return monster.getElementsByTagName("immune").item(0).getTextContent();
		else
			return "";
	}
		
	private String GetSenseFromXml(Element monster) {
		if(monster.getElementsByTagName("senses").item(0) != null)
			return monster.getElementsByTagName("senses").item(0).getTextContent();
		else
			return "";
	}

	private int GetPassivePerceptionFromXml(Element monster) {
		return Integer.parseInt(monster.getElementsByTagName("passive").item(0).getTextContent());
	}

	private String GetLanguagesFromXml(Element monster) {
		if(monster.getElementsByTagName("languages").item(0) != null) {
			String languagesString = monster.getElementsByTagName("languages").item(0).getTextContent();
			return languagesString;
		}
		else
			return "";
	}

	private double GetChallengeRatingFromXml(Element monster) {
		String challengeRatingString = monster.getElementsByTagName("cr").item(0).getTextContent();
		int characterExists = challengeRatingString.indexOf("/");
		if(characterExists != -1)
		{
			String[] challengRatingStringSplit = challengeRatingString.split("/");
			double numerator = Double.parseDouble(challengRatingStringSplit[0]);
			double denominator = Double.parseDouble(challengRatingStringSplit[1]);
			return (numerator / denominator);
		}
		else
			return Double.parseDouble(challengeRatingString);
	}

	private int SetXPValue(double challengeRating) {
		if(challengeRating == 0)
			return 10;
		else if(challengeRating == .125)
			return 25;
		else if(challengeRating == .25)
			return 50;
		else if(challengeRating == .5)
			return 100;
		else if(challengeRating == 1)
			return 200;
		else if(challengeRating == 2)
			return 450;
		else if(challengeRating == 3)
			return 700;
		else if(challengeRating == 4)
			return 1100;
		else if(challengeRating == 5)
			return 1800;
		else if(challengeRating == 6)
			return 2300;
		else if(challengeRating == 7)
			return 2900;
		else if(challengeRating == 8)
			return 3900;
		else if(challengeRating == 9)
			return 5000;
		else if(challengeRating == 10)
			return 5900;
		else if(challengeRating == 11)
			return 7200;
		else if(challengeRating == 12)
			return 8400;
		else if(challengeRating == 13)
			return 10000;
		else if(challengeRating == 14)
			return 11500;
		else if(challengeRating == 15)
			return 13000;
		else if(challengeRating == 16)
			return 15000;
		else if(challengeRating == 17)
			return 18000;
		else if(challengeRating == 18)
			return 20000;
		else if(challengeRating == 19)
			return 22000;
		else if(challengeRating == 20)
			return 25000;
		else if(challengeRating == 21)
			return 33000;
		else if(challengeRating == 22)
			return 41000;
		else if(challengeRating == 23)
			return 50000;
		else if(challengeRating == 24)
			return 62000;
		else if(challengeRating == 25)
			return 75000;
		else if(challengeRating == 26)
			return 90000;
		else if(challengeRating == 27)
			return 105000;
		else if(challengeRating == 28)
			return 120000;
		else if(challengeRating == 29)
			return 135000;
		else
			return 155000;
	}

	private List<Trait> GetTraitsFromXml(Element monster) {
		List<Trait> traits = new ArrayList<Trait>();

		NodeList traitsFromXml = monster.getElementsByTagName("trait");
		for(int i = 0; i < traitsFromXml.getLength(); i++)
		{
			Element traitFromXml = (Element) traitsFromXml.item(i);
			Trait trait = new Trait(traitFromXml.getElementsByTagName("name").item(0).getTextContent(), traitFromXml.getElementsByTagName("text").item(0).getTextContent());
			if(traitFromXml.getElementsByTagName("attack").item(0) != null)
				trait.Attack = traitFromXml.getElementsByTagName("attack").item(0).getTextContent();
			traits.add(trait);
		}

		return traits;
	}

	
	private List<Action> GetActionsFromXML(Element monster) {
		List<Action> actions = new ArrayList<Action>();

		NodeList actionsFromXml = monster.getElementsByTagName("action");
		for(int i = 0; i < actionsFromXml.getLength(); i++)
		{
			Element actionFromXml = (Element) actionsFromXml.item(i);
			Action action = new Action(actionFromXml.getElementsByTagName("name").item(0).getTextContent(), actionFromXml.getElementsByTagName("text").item(0).getTextContent());
			if(actionFromXml.getElementsByTagName("attack").item(0) != null)
				action.Attack = actionFromXml.getElementsByTagName("attack").item(0).getTextContent();
			actions.add(action);
		}

		return actions;
	}

	private List<Action> GetReactionsFromXML(Element monster) {
		List<Action> actions = new ArrayList<Action>();

		NodeList actionsFromXml = monster.getElementsByTagName("reaction");
		for(int i = 0; i < actionsFromXml.getLength(); i++)
		{
			Element actionFromXml = (Element) actionsFromXml.item(i);
			Action action = new Action(actionFromXml.getElementsByTagName("name").item(0).getTextContent(), actionFromXml.getElementsByTagName("text").item(0).getTextContent());
			if(actionFromXml.getElementsByTagName("attack").item(0) != null)
				action.Attack = actionFromXml.getElementsByTagName("attack").item(0).getTextContent();
			actions.add(action);
		}

		return actions;
	}

	private List<Action> GetLegendaryActionsFromXML(Element monster) {
		List<Action> actions = new ArrayList<Action>();

		NodeList actionsFromXml = monster.getElementsByTagName("legendary");
		for(int i = 0; i < actionsFromXml.getLength(); i++)
		{
			Element actionFromXml = (Element) actionsFromXml.item(i);
			Action action = new Action(actionFromXml.getElementsByTagName("name").item(0).getTextContent(), actionFromXml.getElementsByTagName("text").item(0).getTextContent());
			if(actionFromXml.getElementsByTagName("attack").item(0) != null)
				action.Attack = actionFromXml.getElementsByTagName("attack").item(0).getTextContent();
			actions.add(action);
		}

		return actions;
	}

}
