package life;

import java.io.IOException;
import java.util.Random;

class ObservableUniverse implements Runnable
{
    private int generations = 1;
    private int alive;
    private char[][] universe;
    static boolean pause;

    ObservableUniverse(int size)
    {
        if (size <= 2)
        {
            System.out.println("Please enter a size greater than 2.");
            return;
        }

        universe = createUniverse(size);
    }

    char[][] createUniverse(int size)
    {
        char[][] matrix = new char[size][size];
        Random random = new Random(System.currentTimeMillis());

        for (int rows = 0; rows < matrix.length; rows++)
        {
            for (int col = 0; col < matrix.length; col++)
            {
                if (random.nextBoolean())
                {
                    matrix[rows][col] = 'O';
                    alive++;
                }

                else
                    matrix[rows][col] = ' ';
            }
        }
        return matrix;
    }

    @Override
    public void run()
    {
            while (alive > 0)
            {
                try
                {
                    universe = life.Algorithm.next(universe);
                    alive = life.Algorithm.getAlive();
                    print();
                    Thread.sleep(1500);
                    clearConsole();
                    generations++;
                }
                catch (InterruptedException e)
                {
                    if (pause)
                        return;
                    try {
                        Thread.sleep(Long.MAX_VALUE);
                    } catch (InterruptedException i){ }
                }
            }


    }

    private void print()
    {
        System.out.println("Generation: #" + generations);
        System.out.println("Alive: " + alive + "\n");

        for (int i = 0; i < universe.length; i++)
        {
            for (int j = 0; j < universe.length; j++)
            {
                System.out.print(universe[i][j]);
            }
            System.out.println();
        }
        System.out.println();
        System.out.println("Press ENTER for pause");
        System.out.println("Input n and press ENTER to start over");
        System.out.println("Input q and press ENTER to quit");
    }

    static void clearConsole()
    {
        try {
            if (System.getProperty("os.name").contains("Windows"))
                new ProcessBuilder("cmd","/c","cls").inheritIO().start().waitFor();
            else
                Runtime.getRuntime().exec("clear");
        }
        catch (IOException | InterruptedException e) {}
    }

}