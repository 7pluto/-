package util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import model.Score;

public class ScoreSortUtil {

	public static List<Score> sortScore(List<Score> scores) {
		List<Score> scoreSort = new ArrayList<Score>();
		/*Collections.sort(scores, new Comparator<Score>() {

			@Override
			public int compare(Score o1, Score o2) {
				int i = (int)(o2.getScore() - o1.getScore());
				return i;
			}
		});*/
		
		for(int i = 0; i < scores.size() - 1; i++)
		{
			Score s1 = scores.get(i);
			for(int j = i + 1; j < scores.size(); j++)
			{
				Score s2 = scores.get(j);
				if(s1.getScore() > s2.getCredit())
				{
					Score t = s1;
					s1 = s2;
					s2 = t;
				}
				scoreSort.add(s2);
			}
		}
		//return scores;
		return scoreSort;
	}
	
	public static String[] findScore(List<Score> scores, double score1, double score2)
	{
		int n = 0;
		String[] findScore = new String[]{};
		for(int i = 0; i < scores.size(); i++)
		{
			Score s = scores.get(i);
			if(s.getScore() < score2 && s.getScore() > score1)
			{
				findScore[n++] = s.getNumber();
			}
		}
		return findScore;
	}

}
