
public class AbilityScore {
	public int Score;
	public int Modifier;
	public String AbilityName;
	
	public int GetModifierByScore(int score)
	{
		if (score == 1)
		{
			return -5;
		}
		else if (score == 2 || score == 3)
		{
			return -4;
		}
		else if (score ==  4 || score == 5)
		{
			return -3;
		}
		else if (score == 6 || score == 7)
		{
			return -2;
		}
		else if (score == 8 || score == 9)
		{
			return -1;
		}
		else if (score == 10 || score == 11)
		{
			return 0;
		}
		else if (score == 12 || score == 13)
		{
			return 1;
		}
		else if (score == 14 || score == 15)
		{
			return 2;
		}
		else if (score == 16 || score == 17)
		{
			return 3;
		}
		else if (score == 18 || score == 19)
		{
			return 4;
		}
		else if (score == 20 || score == 21)
		{
			return 5;
		}
		else if (score == 22 || score == 23)
		{
			return 6;
		}
		else if (score == 24 || score == 25)
		{
			return 7;
		}
		else if (score == 26 || score == 27)
		{
			return 8;
		}
		else if (score == 28 || score == 29)
		{
			return 9;
		}
		else if (score == 30)
		{
			return 10;
		}
		//default is 0
		else
			return 0;
	}

	public void ToString()
	{
		System.out.println(AbilityName + " Score: " + Score);
		System.out.println(AbilityName + " Modifier: " + Modifier);
	}
}
