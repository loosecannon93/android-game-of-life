package com.cannonmatthews.gameoflife;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class GridView extends View  {
    
    public static final int PAUSE = 0;
    public static final int RUNNING = 1;
    public static final int ALIVE = 2;
    public static final int DYING = 1;
    public static final int DEAD = 0;
    
    private Life _life;
    
    private long _moveDelay = (1000/6);
    
    private RefreshHandler _redrawHandler = new RefreshHandler();

    class RefreshHandler extends Handler {

        @Override
        public void handleMessage(Message message) {
            GridView.this.update();
            GridView.this.invalidate();
        }

        public void sleep(long delayMillis) {
            this.removeMessages(0);
            sendMessageDelayed(obtainMessage(0), delayMillis);
        }
    };

    public GridView(Context context, AttributeSet attrs) {
        super(context, attrs);
        _life = new Life(context);
        initGridView();
    }
    
    public void setMode(int mode) {
        if (mode == RUNNING) {
            update();
            return;
        }
        if (mode == PAUSE) {
           
        }
    }
    public Life getLife()
    {
    	return _life;
    	
    }
    @Override
    protected void onDraw(Canvas canvas) {
        Paint background = new Paint();
        background.setColor(getResources().getColor(R.color.background));

        Paint aliveCell = new Paint();
        aliveCell.setColor(getResources().getColor(R.color.cell));

        Paint dyingCell = new Paint();
        dyingCell.setColor(getResources().getColor(R.color.cell2));
        
        // draw background
        canvas.drawRect(0, 0, getWidth(), getHeight(), background);
        
        // draw cells
        for (int h = 0; h < Life.HEIGHT; h++) {
            for (int w = 0; w < Life.WIDTH; w++) {
                if (Life.getGrid()[h][w] == ALIVE) {
                    canvas.drawRect(
                        w * Life.CELL_SIZE, 
                        h * Life.CELL_SIZE, 
                        (w * Life.CELL_SIZE) + (Life.CELL_SIZE -1),
                        (h * Life.CELL_SIZE) + (Life.CELL_SIZE -1),
                        aliveCell);
                }
                if (Life.getGrid()[h][w] == DYING) {
                    canvas.drawRect(
                        w * Life.CELL_SIZE, 
                        h * Life.CELL_SIZE, 
                        (w * Life.CELL_SIZE) + (Life.CELL_SIZE -1),
                        (h * Life.CELL_SIZE) + (Life.CELL_SIZE -1),
                        dyingCell);
                }
            }
        }
    }
    public boolean onTouchEvent(MotionEvent event) {
    	float x = event.getRawX();
    	float y = event.getRawY();
    	_life.markAlive(x, y);
    	return true;
    	
    }
    private void update() {
        _life.generateNextGeneration();
        _redrawHandler.sleep(_moveDelay);
    }
    
    private void initGridView() {
        setFocusable(true);
    }
}
