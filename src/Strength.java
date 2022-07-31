
public class Strength extends AbilityScore {
	public Strength(int score)
	{
		Score = score;
		Modifier = GetModifierByScore(score);
		AbilityName = "Strength";
	}
}
