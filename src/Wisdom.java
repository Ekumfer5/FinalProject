
public class Wisdom extends AbilityScore {
	public Wisdom(int score)
	{
		Score = score;
		Modifier = GetModifierByScore(score);
		AbilityName = "Wisdom";
	}
}
