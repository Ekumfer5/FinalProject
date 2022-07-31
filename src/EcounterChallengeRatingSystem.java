

public class EcounterChallengeRatingSystem {
	
	public int[] ExperiencePointValues;
	public double ChallengeRating;
	public int NumberOfPlayers;
	
	public EcounterChallengeRatingSystem(Double averageLevel, int numberOfPlayers) throws IllegalArgumentException
	{
		if(averageLevel >= 1 || averageLevel <= 20)
		{
			if(numberOfPlayers > 0)
			{
				NumberOfPlayers = numberOfPlayers;
				ChallengeRating = averageLevel;
				ExperiencePointValues = GetXpArray(averageLevel);
			}
			else
				throw new IllegalArgumentException("Number of players must be greater than 0;");
		}
		else
			throw new IllegalArgumentException("Average Level must be between 1 and 20");
	}
	
	private int[] GetXpArray(Double averageLevel)
	{
		if(averageLevel == 1)
		{
			return new int[] {20, 50, 100, 150};
		}
		else if(averageLevel == 2)
		{
			return new int[] {20, 70, 140, 210};
		}
		else if(averageLevel == 3)
		{
			return new int[] {40, 110, 220,	330};
		}
		else if(averageLevel == 4)
		{
			return new int[] {50, 150, 300,	450};
		}
		else if(averageLevel == 5)
		{
			return new int[] {70, 200, 400,	600};
		}
		else if(averageLevel == 6)
		{
			return new int[] {80, 250, 500,	750};
		}
		else if(averageLevel == 7)
		{
			return new int[] {100, 300, 600, 900};
		}
		else if(averageLevel == 8)
		{
			return new int[] {120, 350, 700, 1050};
		}
		else if(averageLevel == 9)
		{
			return new int[] {130, 400, 800, 1200};
		}
		else if(averageLevel == 10 || averageLevel == 11)
		{
			return new int[] {150, 500, 1000, 1500};
		}
		else if(averageLevel == 12 || averageLevel == 13)
		{
			return new int[] {200, 600, 1200, 1800};
		}
		else if(averageLevel == 14)
		{
			return new int[] {250, 700, 1400, 2100};
		}
		else if(averageLevel == 15 || averageLevel == 16)
		{
			return new int[] {250, 800, 1600, 24400};
		}
		else if(averageLevel == 17)
		{
			return new int[] {300, 900, 1800, 2700};
		}
		else if(averageLevel == 18)
		{
			return new int[] {350, 1000, 2000, 3000};
		}
		else
		{
			return new int[] {350, 1100, 2200, 3300};
		}
	}
}
