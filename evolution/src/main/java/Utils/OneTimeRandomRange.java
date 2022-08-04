package Utils;

import java.util.ArrayList;
import java.util.Random;

public class OneTimeRandomRange {

    public static ArrayList<Integer> random(int size,
                                            int range,
                                            Random random,
                                            boolean includedZero){
        ArrayList<Integer> results = new ArrayList(size);
        ArrayList<Integer> indexes = new ArrayList<>(range);

        if(includedZero)
            for (int j = 0;j < range;j ++)
                indexes.add(j);
        else
            for (int j = 1;j < range;j ++)
                indexes.add(j);

        for (int j = 0;j < size;j ++)
        {
            int randIndex = random.nextInt(indexes.size());
            results.add(indexes.get(randIndex));
            indexes.remove(randIndex);
        }

        return results;
    }
}
