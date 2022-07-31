
public class Dexterity extends AbilityScore {
	public Dexterity(int score)
	{
		Score = score;
		Modifier = GetModifierByScore(score);
		AbilityName = "Dexterity";
	}
}
