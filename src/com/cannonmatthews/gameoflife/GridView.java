package com.cannonmatthews.gameoflife;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;

public class GridView extends View {
    
    public static final int PAUSE = 0;
    public static final int RUNNING = 1;
    
    private Life _life;
    
    private long _moveDelay = 250;
    
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
            // TODO: implement.
        }
    }
    
    @Override
    protected void onDraw(Canvas canvas) {
        Paint background = new Paint();
        background.setColor(getResources().getColor(R.color.background));

        Paint cell = new Paint();
        cell.setColor(getResources().getColor(R.color.cell));

        // draw background
        canvas.drawRect(0, 0, getWidth(), getHeight(), background);

        // draw cells
        for (int h = 0; h < Life.HEIGHT; h++) {
            for (int w = 0; w < Life.WIDTH; w++) {
                if (Life.getGrid()[h][w] != 0) {
                    canvas.drawRect(
                        w * Life.CELL_SIZE, 
                        h * Life.CELL_SIZE, 
                        (w * Life.CELL_SIZE) + (Life.CELL_SIZE -1),
                        (h * Life.CELL_SIZE) + (Life.CELL_SIZE -1),
                        cell);
                }
            }
        }
    }
    
    private void update() {
        _life.generateNextGeneration();
        _redrawHandler.sleep(_moveDelay);
    }
    
    private void initGridView() {
        setFocusable(true);
    }
}
