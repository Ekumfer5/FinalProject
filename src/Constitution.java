
public class Constitution extends AbilityScore {
	public Constitution(int score)
	{
		Score = score;
		Modifier = GetModifierByScore(score);
		AbilityName = "Constitution";
	}
}
