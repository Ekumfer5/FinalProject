
public class Intelligence extends AbilityScore {
	public Intelligence(int score)
	{
		Score = score;
		Modifier = GetModifierByScore(score);
		AbilityName = "Intelligence";
	}
}
