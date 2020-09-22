package kattis;

import java.util.StringTokenizer;
import java.util.TreeSet;
import java.io.BufferedReader;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.OutputStream;

public class CanonicalCoinSystems {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Kattio io = new Kattio(System.in, System.out);
		int size =io.getInt();
		int [] denominations = new int [size];
		
		for(int i=0; i<size; i++)
		{
			denominations[i] = io.getInt();
			
		}
		
		if(isCanonical(denominations, size))
		{
			io.println("canonical");
		}
		else {
			io.println("non-canonical");
		}
	

		io.close();

	}

	private static boolean isCanonical(int[] denominations, int size) {
		// TODO Auto-generated method stub
		
		int [] optimal =optimalFind(denominations[size-1] + denominations[size-2] -1, denominations,size);
		int [] greedy = greedyFind(denominations[size-1] + denominations[size-2] -1, denominations, size); //return the number of coins use
		for(int i= 2; i<(denominations[size-1] + denominations[size-2]); i++) // i is current value looking for
		{
			if(optimal[i]<greedy[i])
				return false;
		}
		return true;
	}

	private static int[] optimalFind(int goal, int[] denominations, int size) {
		// TODO Auto-generated method stub
		int [] dp = new int [goal+1];  //store the number of coins needed, index is the value of the goal
		
		for(int j =0; j<goal+1; j++) //set all unknown
		{
			dp[j]= -1;
		}
		
		dp[0] =0; //value 0 need 0 coins to make change 
		
		for( int j =1; j< goal+1; j++) // index for dp
			{
			for(int i:denominations)
			{
				if(j>=i && dp[j-i]>-1)
				{
					if (dp[j] ==-1) // never updated
						dp[j] = 1+ dp[j-i];
					else {
						dp[j] =Math.min(1+ dp[j-i], dp[j]); //has one solution, check for minimal
					}
				}
			}
		}
		
		return dp;
	}

	private static int[] greedyFind(int goal, int[] denominations, int size) {
		// TODO Auto-generated method stub
		
		int [] dp = new int [goal+1];
		
		for(int j =0; j<goal+1; j++) //set all unknown
		{
			dp[j]= -1;
		}
		
		dp[0] =0; //value 0 need 0 coins to make change 
		
		for( int j =0; j< goal+1; j++) // index for dp
		{
			for(int i:denominations)
			{
				if(  j+ i<goal+1 && dp[j+i] ==-1)
					dp[j+i] = 1+dp[j];
			}
		}
		
		return dp;
		
		/*
		 if (goal==0)
			 return 0;
			return (1 + greedyFind(goal-set.floor(goal), set, size));

		*/
	}



}

class Kattio extends PrintWriter {
    public Kattio(InputStream i) {
        super(new BufferedOutputStream(System.out));
        r = new BufferedReader(new InputStreamReader(i));
    }
    public Kattio(InputStream i, OutputStream o) {
        super(new BufferedOutputStream(o));
        r = new BufferedReader(new InputStreamReader(i));
    }

    public boolean hasMoreTokens() {
        return peekToken() != null;
    }

    public int getInt() {
        return Integer.parseInt(nextToken());
    }

    public double getDouble() {
        return Double.parseDouble(nextToken());
    }

    public long getLong() {
        return Long.parseLong(nextToken());
    }

    public String getWord() {
        return nextToken();
    }



    private BufferedReader r;
    private String line;
    private StringTokenizer st;
    private String token;

    private String peekToken() {
        if (token == null)
            try {
                while (st == null || !st.hasMoreTokens()) {
                    line = r.readLine();
                    if (line == null) return null;
                    st = new StringTokenizer(line);
                }
                token = st.nextToken();
            } catch (IOException e) { }
        return token;
    }

    private String nextToken() {
        String ans = peekToken();
        token = null;
        return ans;
    }
}
