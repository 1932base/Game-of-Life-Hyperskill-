package life;

import java.io.IOException;
import java.util.*;

//View: outputs display
public class ObservableUniverse implements Runnable
{
    private int generations = 1;
    private int alive;
    private char[][] universe;
    private static boolean quit;
    private static boolean reset;
    private static int size;

    public ObservableUniverse(int size)
    {
        if (size <= 2)
        {
            System.out.println("Please enter a size greater than 2.");
            return;
        }

        universe = createUniverse(size);
    }

    public static void setQuit(boolean quit) { ObservableUniverse.quit = quit; }
    public static void setReset(boolean reset) { ObservableUniverse.reset = reset; }
    public static void setSize(int size) { ObservableUniverse.size = size; }

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
        while (true)
        {
                universe = life.Algorithm.next(universe);
                alive = life.Algorithm.getAlive();
                print();
                schleep(1000);
                if (alive == 0)
                    schleep(Long.MAX_VALUE);
                if (quit)
                    return;
                clearConsole();
                generations++;
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

    private void reset(int size)
    {
        universe = createUniverse(size);
        generations = 0;
        reset = false;
    }

    private void schleep(long milliseconds)
    {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e)
        {
            if (quit)
               return;

            if (reset) {
                try{
                    Thread.sleep(Long.MAX_VALUE);
                } catch (InterruptedException i)
                {
                    reset(size);
                }

            } else {

                try {
                    Thread.sleep(Long.MAX_VALUE);
                } catch (InterruptedException i) { }

            }
        }
    }

    public static void clearConsole()
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
