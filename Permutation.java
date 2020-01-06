/* *****************************************************************************
 *  Name: Arjoban Singh
 *  Date: Jan 6, 2020
 *  Description: Permutation Implemented
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;

public class Permutation {
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        RandomizedQueue<String> randomizedQueue = new RandomizedQueue<String>();
        while(!StdIn.isEmpty())
        {
            randomizedQueue.enqueue(StdIn.readString());
        }

        for (int i = 0; i < k; i++)
        {
            System.out.println(randomizedQueue.dequeue());
        }
    }
}
