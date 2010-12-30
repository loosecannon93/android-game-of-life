

package com.cannonmatthews.gameoflife;



import android.content.Context;

public class Life {

    public static final int CELL_SIZE = 8;
    public static final int WIDTH = 320 / CELL_SIZE;
    public static final int HEIGHT = 480 / CELL_SIZE;
    public static final int ALIVE = 2;
    public static final int DYING = 1;
    public static final int DEAD = 0;

    private static final int[][] _lifeGrid = new int[HEIGHT][WIDTH];

    private Context _context;

    public Life(Context context) {
        this._context = context;
        initializeGrid();
    }

    public static int[][] getGrid() {
        return _lifeGrid;
    }
   

    public void initializeGrid() {
        resetGrid(_lifeGrid);
        
//          _lifeGrid[8][(WIDTH / 2) -1 ] = ALIVE;
//          _lifeGrid[8][(WIDTH / 2)] = ALIVE;
//            
//          _lifeGrid[9][(WIDTH / 2) -1 ] = DYING;
//          _lifeGrid[9][(WIDTH / 2)] = DYING;
//          _lifeGrid[9][(WIDTH / 2) + 1 ] = ALIVE;
//          
//          _lifeGrid[10][(WIDTH / 2)] = DYING;
//          _lifeGrid[10][(WIDTH / 2) + 1 ] = ALIVE;
               
          

//        _lifeGrid[8][(WIDTH / 2) - 1] = ALIVE;
//      //  _lifeGrid[8][(WIDTH / 2)] = ALIVE;
//        _lifeGrid[8][(WIDTH / 2) + 1] = ALIVE;
//        _lifeGrid[9][(WIDTH / 2) - 1] = ALIVE;
//      //  _lifeGrid[9][(WIDTH / 2)] = ALIVE;
//        _lifeGrid[9][(WIDTH / 2) + 1] = ALIVE;
//        _lifeGrid[10][(WIDTH / 2) - 1] = ALIVE;
//      //  _lifeGrid[10][(WIDTH / 2)] = ALIVE;
//        _lifeGrid[10][(WIDTH / 2) + 1] = ALIVE;
//        
       
    }

    public void generateNextGeneration() {
        int neighbours;
        int minimum = Integer.parseInt(PreferencesActivity
                .getMinimumVariable(this._context));
        int maximum = Integer.parseInt(PreferencesActivity
                .getMaximumVariable(this._context));
        int spawn = Integer.parseInt(PreferencesActivity
                .getSpawnVariable(this._context));

        int[][] nextGenerationLifeGrid = new int[HEIGHT][WIDTH];

        for (int h = 0; h < HEIGHT; h++) {
            for (int w = 0; w < WIDTH; w++) {
            	neighbours = calculateNeighbours(h, w);
            	switch(_lifeGrid[h][w]){
            	
            	case ALIVE:
            		//if (neighbours >=3 && neighbours <= 6)
            		//	nextGenerationLifeGrid[h][w] = ALIVE;
            		//else
            			nextGenerationLifeGrid[h][w] = DYING;
            		break;
            	case DYING:
            		nextGenerationLifeGrid[h][w] = DEAD;
            		break;
            	case DEAD:
            		if (neighbours >= 2)
            			nextGenerationLifeGrid[h][w] = ALIVE;
            		break;
            		
            	}
                            }
        }
        copyGrid(nextGenerationLifeGrid, _lifeGrid);
    }

    private void resetGrid(int[][] grid) {
        for (int h = 0; h < HEIGHT; h++) {
            for (int w = 0; w < WIDTH; w++) {
                grid[h][w] = 0;
            }
        }
    }

    private int calculateNeighbours(int y, int x) {
        int total =  0;
        for (int h = -1; h <= +1; h++) {
            for (int w = -1; w <= +1; w++) {
                if (_lifeGrid[(HEIGHT + (y + h)) % HEIGHT][(WIDTH + (x + w))
                        % WIDTH] == ALIVE) {
                    total++;
                }
            }
        }
        return total;
    }

    private void copyGrid(int[][] source, int[][] destination) {
        for (int h = 0; h < HEIGHT; h++) {
            for (int w = 0; w < WIDTH; w++) {
                destination[h][w] = source[h][w];
            }
        }
    }
    public void markAlive(float x, float y){
    	int h = (int)(y / CELL_SIZE );
    	int w = (int)(x / CELL_SIZE );
    	_lifeGrid[h][w] = ALIVE;
        
    }
}
