package com.techelevator;

import java.util.StringTokenizer;
 
public class WordWrap //found on rosettacode.org to wrap without breaking words
{
	int defaultLineWidth = 75;
	int defaultSpaceWidth = 1;
	void minNumLinesWrap(String text)
	{
		minNumLinesWrap(text,defaultLineWidth);
	}
	void minNumLinesWrap(String text,int LineWidth)
	{
		StringTokenizer st=new StringTokenizer(text);
		int SpaceLeft = LineWidth;
		int SpaceWidth = defaultSpaceWidth;
		while(st.hasMoreTokens())
		{
			String word = st.nextToken();
			if((word.length() + SpaceWidth)>SpaceLeft)
			{
				System.out.print("\n" + word + " ");
				SpaceLeft = LineWidth - word.length();
			}
			else
			{
				System.out.print(word + " ");
				SpaceLeft -= (word.length() + SpaceWidth);
			}
		}
	}
}
