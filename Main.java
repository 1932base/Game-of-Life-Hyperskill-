package life;
import java.util.*;

//Controller: takes in user input
public class Main
{
    public static void main(String[] args)
    {
        //game start
        Scanner keyboard = new Scanner(System.in);
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
                universe.interrupt();
                ObservableUniverse.setReset(true);
                System.out.print("Enter size of universe: ");
                ObservableUniverse.setSize(keyboard.nextInt());
                universe.interrupt();
            }
            else if (input.equals("q")) //quit
            {
                ObservableUniverse.setQuit(true);
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
