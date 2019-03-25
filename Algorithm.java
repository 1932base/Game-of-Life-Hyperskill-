package life;

//Model: runs algorithms
class Algorithm
{
    private static int alive;

    static int getAlive()
    {
        return alive;
    }

    static char[][] next(char[][] universe)
    {
        char[][] universe2 = new char[universe.length][universe.length];
        alive = 0;

        for (int r = 0; r < universe.length; r++)
        {
            for(int c = 0; c < universe.length; c++)
            {
                if(aliveNeighbors(r, c, universe) == 3)
                {
                    universe2[r][c] = 'O';
                    alive++;
                }
                else if(universe[r][c] == 'O' && aliveNeighbors(r, c, universe) == 2)
                {
                    universe2[r][c] = 'O';
                    alive++;
                }
                else
                {
                    universe2[r][c] = ' ';
                }
            }
        }
        return universe2;
    }

    private static int aliveNeighbors(int r, int c, char[][]universe)
    {
        int count = 0;

        //middle
        if(r != 0 && c != 0 && r != universe.length - 1 && c != universe.length - 1)
        {
            if (universe[r - 1][c - 1] == 'O') //nw
                count++;
            if (universe[r - 1][c] == 'O') //n
                count++;
            if (universe[r - 1][c + 1] == 'O') //ne
                count++;
            if (universe[r][c - 1] == 'O') //w
                count++;
            if (universe[r][c + 1] == 'O') //e
                count++;
            if (universe[r + 1][c - 1] == 'O') //sw
                count++;
            if (universe[r + 1][c] == 'O') //s
                count++;
            if(universe[r + 1][c + 1] == 'O') //se
                count++;
        }

        //top border
        else if (r == 0 && c != 0 && c != universe.length - 1)
        {
            if (universe[universe.length - 1][c - 1] == 'O') //nw
                count++;
            if (universe[universe.length - 1][c] == 'O') //n
                count++;
            if (universe[universe.length - 1][c + 1] == 'O') //ne
                count++;
            if (universe[r][c - 1] == 'O') //w
                count++;
            if (universe[r][c + 1] == 'O') //e
                count++;
            if (universe[r + 1][c - 1] == 'O') //sw
                count++;
            if (universe[r + 1][c] == 'O') //s
                count++;
            if(universe[r + 1][c + 1] == 'O') //se
                count++;
        }

        //bottom border
        else if (r == universe.length - 1 && c != 0 && c != universe.length - 1)
        {
            if (universe[r - 1][c - 1] == 'O') //nw
                count++;
            if (universe[r - 1][c] == 'O') //n
                count++;
            if (universe[r - 1][c + 1] == 'O') //ne
                count++;
            if (universe[r][c - 1] == 'O') //w
                count++;
            if (universe[r][c + 1] == 'O') //e
                count++;
            if (universe[0][c - 1] == 'O') //sw
                count++;
            if (universe[0][c] == 'O') //s
                count++;
            if(universe[0][c + 1] == 'O') //se
                count++;
        }

        //left border
        else if (c == 0 && r != 0 && r != universe.length - 1)
        {
            if (universe[r - 1][universe.length - 1] == 'O') //nw
                count++;
            if (universe[r - 1][c] == 'O') //n
                count++;
            if (universe[r - 1][c + 1] == 'O') //ne
                count++;
            if (universe[r][universe.length - 1] == 'O') //w
                count++;
            if (universe[r][c + 1] == 'O') //e
                count++;
            if (universe[r + 1][universe.length - 1] == 'O') //sw
                count++;
            if (universe[r + 1][c] == 'O') //s
                count++;
            if(universe[r + 1][c + 1] == 'O') //se
                count++;
        }

        //right border
        else if (c == universe.length - 1 && r != 0 && r != universe.length - 1)
        {
            if (universe[r - 1][c - 1] == 'O') //nw
                count++;
            if (universe[r - 1][c] == 'O') //n
                count++;
            if (universe[r - 1][0] == 'O') //ne
                count++;
            if (universe[r][c - 1] == 'O') //w
                count++;
            if (universe[r][0] == 'O') //e
                count++;
            if (universe[r + 1][c - 1] == 'O') //sw
                count++;
            if (universe[r + 1][c] == 'O') //s
                count++;
            if(universe[r + 1][0] == 'O') //se
                count++;
        }

        //top left corner
        else if (r == 0 && c == 0)
        {
            if (universe[universe.length - 1][universe.length - 1] == 'O') //nw
                count++;
            if (universe[universe.length - 1][c] == 'O') //n
                count++;
            if (universe[universe.length - 1][c + 1] == 'O') //ne
                count++;
            if (universe[r][universe.length - 1] == 'O') //w
                count++;
            if (universe[r][c + 1] == 'O') //e
                count++;
            if (universe[r + 1][universe.length - 1] == 'O') //sw
                count++;
            if (universe[r + 1][c] == 'O') //s
                count++;
            if(universe[r + 1][c + 1] == 'O') //se
                count++;
        }

        //top right corner
        else if (r == 0 && c == universe.length - 1)
        {
            if (universe[universe.length - 1][c - 1] == 'O') //nw
                count++;
            if (universe[universe.length - 1][c] == 'O') //n
                count++;
            if (universe[universe.length - 1][0] == 'O') //ne
                count++;
            if (universe[r][c - 1] == 'O') //w
                count++;
            if (universe[r][0] == 'O') //e
                count++;
            if (universe[r + 1][c - 1] == 'O') //sw
                count++;
            if (universe[r + 1][c] == 'O') //s
                count++;
            if(universe[r + 1][0] == 'O') //se
                count++;
        }

        //bottom left corner
        else if (r == universe.length - 1 && c == 0)
        {
            if (universe[r - 1][universe.length - 1] == 'O') //nw
                count++;
            if (universe[r - 1][c] == 'O') //n
                count++;
            if (universe[r - 1][c + 1] == 'O') //ne
                count++;
            if (universe[r][universe.length - 1] == 'O') //w
                count++;
            if (universe[r][c + 1] == 'O') //e
                count++;
            if (universe[0][universe.length - 1] == 'O') //sw
                count++;
            if (universe[0][c] == 'O') //s
                count++;
            if(universe[0][c + 1] == 'O') //se
                count++;
        }

        //bottom right corner
        else if (r == universe.length - 1 && c == universe.length - 1)
        {
            if (universe[r - 1][c - 1] == 'O') //nw
                count++;
            if (universe[r - 1][c] == 'O') //n
                count++;
            if (universe[r - 1][0] == 'O') //ne
                count++;
            if (universe[r][c - 1] == 'O') //w
                count++;
            if (universe[r][0] == 'O') //e
                count++;
            if (universe[0][c - 1] == 'O') //sw
                count++;
            if (universe[0][c] == 'O') //s
                count++;
            if(universe[0][0] == 'O') //se
                count++;
        }

        return count;
    }

}
