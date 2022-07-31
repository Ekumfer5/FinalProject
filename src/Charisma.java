
public class Charisma extends AbilityScore {
	public Charisma(int score)
	{
		Score = score;
		Modifier = GetModifierByScore(score);
		AbilityName = "Charisma";
	}
}
