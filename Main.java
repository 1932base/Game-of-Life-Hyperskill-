package life;
import java.util.*;

public class Main
{
    public static void main(String[] args)
    {
        Scanner keyboard = new Scanner(System.in);

        //make this a method call for n
        System.out.print("Enter size of the universe: ");
        int size = keyboard.nextInt();
        keyboard.nextLine();
        life.ObservableUniverse.clearConsole();

        Thread universe = new Thread(new life.ObservableUniverse(size));

        universe.start();

        //continuous user input
        String input = keyboard.nextLine();

        while(input != null)
        {
            if (input.isEmpty())
            {
                universe.interrupt();
            }
            else if (input.equals("n"))
            {

            }
            else if (input.equals("q")) //quit
            {
                ObservableUniverse.pause = true;
                universe.interrupt();
                break;
            }

            if (keyboard.hasNextLine()) {
                input = keyboard.nextLine();
            } else {
                keyboard = null;
            }
        }

        //make sure main thread doesn't end prematurely
        try {
            universe.join();
        } catch (InterruptedException e) { }

    }
}