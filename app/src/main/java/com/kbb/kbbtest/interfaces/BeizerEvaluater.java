package com.kbb.kbbtest.interfaces;

import android.animation.TypeEvaluator;

import com.kbb.kbbtest.bean.Point;

/**
 * Created by longlong on 2017/11/20.
 *
 * @ClassName: BeizerEvaluater
 * @Description:
 * @Date 2017/11/20
 */

public class BeizerEvaluater implements TypeEvaluator<Point> {
    @Override
    public Point evaluate(float fraction, Point startValue, Point endValue) {
        Point point = new Point();
        point.setX(startValue.getX());
        return null;
    }
}
