import java.util.List;

public class Monster {
	public String Name;
	public String Size;
	public String Type;
	public String Alignment;
	public int AC;
	public String HP;
	public String Speed;
	public List<AbilityScore> AbilityScores;
	public String Saves;
	public String Skills;
	public String Immunities;
	public String Senses;
	public int PassivePerception;
	public String Languages;
	public double ChallengeRating;
	public int XP;
	public List<Trait> Traits;
	public List<Action> Actions;
	public List<Action> Reactions;
	public List<Action> LegendaryActions;
	
	public Monster()
	{
		
	}
	
	public void ToString()
	{
		System.out.println("Name: " + Name);
		System.out.println("Size: " + Size);
		System.out.println("Type: " + Type);
		System.out.println("Alignment: " + Alignment);
		System.out.println("Armor Class: " + AC);
		System.out.println("HP: " + HP);
		System.out.println("Speed: " + Speed);
		for(int abilityScoresCount = 0; abilityScoresCount < AbilityScores.size(); abilityScoresCount++)
			AbilityScores.get(abilityScoresCount).ToString();
		System.out.println("Saves: " + Saves);
		System.out.println("Skills: " + Skills);
		System.out.println("Immunities: " + Immunities);
		System.out.println("Senses: " + Senses);
		System.out.println("Passive Perception: " + PassivePerception);
		System.out.println("Languages: " + Languages);
		System.out.println("Challenge Rating: " + ChallengeRating);
		System.out.println("Experience Value: " + XP);
		if(Traits.size() != 0)
		{
			System.out.println("Traits:");
			for(int traitCount = 0; traitCount < Traits.size(); traitCount++)
				Traits.get(traitCount).ToString();
		}
		if(Actions.size() != 0)
		{
			System.out.println("Actions: ");
			for(int actionsCount = 0; actionsCount < Actions.size(); actionsCount++)
				Actions.get(actionsCount).ToString();
		}
		if(Reactions.size() != 0)
		{
			System.out.println("Reactions:");
			for(int reactionCount = 0; reactionCount < Reactions.size(); reactionCount++)
				Reactions.get(reactionCount).ToString();
		}
		if(LegendaryActions.size() != 0)		
		{
			System.out.println("Legendary Actions");
			for(int legendaryActionCount = 0; legendaryActionCount < LegendaryActions.size(); legendaryActionCount++)
				LegendaryActions.get(legendaryActionCount).ToString();
		}
		System.out.println();
	}
}
