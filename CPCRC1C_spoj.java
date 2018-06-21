import java.util.*;

public class CPCRC1C_spoj{
    static long dp[][][];
    static ArrayList<Integer> getDigits(int num){
        ArrayList<Integer> res = new ArrayList<>();
        while(num != 0){
            res.add(num%10);
            num = num/10;
        }
        return res;
    }

    static long digitSum(int idx, int sum, int tight, ArrayList<Integer> digit){
        if(idx == -1) return sum;

        if(dp[idx][sum][tight] != -1 && tight != -1){
            return dp[idx][sum][tight];
        }

        long ret= 0;
        int k = (tight == 1)? digit.get(idx):9;

        for (int i = 0; i <= k ; i++){
            // caclulating newTight value for next state
            int newTight = (digit.get(idx) == i)? tight : 0;
    
            // fetching answer from next state
            ret += digitSum(idx-1, sum+i, newTight, digit);
        }
 
    if (tight == 0)
      dp[idx][sum][tight] = ret;
 
    return ret;
    }

    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        while(sc.hasNextLine()){
            String line = sc.nextLine();
            StringTokenizer st = new StringTokenizer(line);
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if(a == -1 && b == -1)break;

            dp = new long[20][180][2];
            for(int i = 0; i < dp.length; i++)
                for(int j = 0; j < dp[i].length; j++)
                    for(int k = 0; k < dp[i][j].length; k++) dp[i][j][k] = -1;
            ArrayList<Integer> Adig = getDigits(a - 1);
            ArrayList<Integer> Bdig = getDigits(b);

            long ans1 = digitSum(Adig.size() -1, 0, 1, Adig);
            long ans2 = digitSum(Bdig.size() -1, 0, 1, Bdig);

            System.out.println(ans2 - ans1);

        }
    }
}