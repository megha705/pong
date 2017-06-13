package com.shrp.pong;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.KeyEvent;

/**
 * Created by shriroop on 06-Jun-17.
 */

public class GameState {
    int _screenWidth = 300, _screenHeight = 420;

    int _ballSize = 10;
    int _ballX = 100, _ballY = 100;
    int _ballVelocityX = 3, _ballVelocityY = 3;

    int _batLength = 75, _batHeight = 10;
    int _topBatX = (_screenWidth / 2) - (_batLength / 2);
    int _topBatY = 20;
    int _bottomBatX = (_screenWidth / 2) - (_batLength / 2);
    int _bottomBatY = 400;
    int _batSpeed = 3;

    public GameState(int height, int width) {
        _screenHeight = height;
        _screenWidth = width;
    }

    public void update() {
        _ballX += _ballVelocityX;
        _ballY += _ballVelocityY;

        if(_ballY > _screenHeight || _ballY < 0) {
            _ballX = _screenHeight / 2;
            _ballY = _screenWidth / 2;
        }
        if(_ballX > _screenWidth || _ballX < 0) {
            _ballVelocityX *= -1;
        }
        if(_ballX > _bottomBatX && _ballX < _bottomBatX + _batLength && _ballY > _bottomBatY) {
            _ballVelocityY *= -1;
        }
    }

    public boolean keyPressed(int keyCode, KeyEvent keyMsg) {
        if(keyCode == KeyEvent.KEYCODE_DPAD_LEFT) { // left
            _topBatX += _batSpeed; _bottomBatX -= _batSpeed;
        }
        if(keyCode == KeyEvent.KEYCODE_DPAD_RIGHT) { //right
            _topBatX -= _batSpeed; _bottomBatX += _batSpeed;
        }
        return true;
    }
    public void draw(Canvas canvas, Paint paint) {
        canvas.drawRGB(20, 20, 20); // clear canvas
        canvas.drawCircle(_ballX, _ballY, _ballSize, paint);
        canvas.drawRect(new Rect(_topBatX, _topBatY, _topBatX + _batLength, _topBatY + _batHeight), paint);
        canvas.drawRect(new Rect(_bottomBatX, _bottomBatY, _bottomBatX + _batLength, _bottomBatY + _batHeight), paint);
    }

}
