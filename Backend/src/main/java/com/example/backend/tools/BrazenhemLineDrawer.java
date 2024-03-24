package com.example.backend.tools;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;

public class BrazenhemLineDrawer {
    private int dx, dy;

    public synchronized List<Point> getLinePoints(int x1, int y1, int x2, int y2){
        List<Point> points = new ArrayList<>();

        dx = abs(x2 - x1);
        dy = abs(y2 - y1);

        if(x2 >= x1 && y2 >= y1){
            if(dx > dy){
                draw1(x1, y1, points);
            }
            else{
                draw2(x1, y1, points);
            }
        }
        else if(x2 <= x1 && y2 <= y1){
            if(dx > dy){
                draw3(x1, y1, points);
            }
            else{
                draw4(x1, y1, points);
            }
        }
        else if(x2 >= x1 && y2 <= y1){
            if(dx > dy){
                draw5(x1, y1, points);
            }
            else{
                draw6(x1, y1, points);
            }
        }
        else if(x2 <= x1 && y2 >= y1){
            if(dx > dy){
                draw7(x1, y1, points);
            }
            else{
                draw8(x1, y1, points);
            }
        }

        return points;
    }

    private void draw1(int x, int y, List<Point> points){   // x2 > x1, y2 > y1, dx > dy
        int err = -dx;
        points.add(new Point(x, y));
        for(int i = 0; i < dx; ++i) {
            x++;
            err += 2 * dy;
            if (err > dx) {
                err -= 2 * dx;
                y++;
            }
            points.add(new Point(x, y));
        }
    }

    private void draw2(int x, int y, List<Point> points){  // x2 > x1, y2 > y1, dx < dy
        int err = -dy;
        points.add(new Point(x, y));
        for(int i = 0; i < dy; ++i) {
            y++;
            err += 2 * dx;
            if (err > dy) {
                err -= 2 * dy;
                x++;
            }
            points.add(new Point(x, y));
        }
    }

    private void draw3(int x, int y, List<Point> points){  // x2 < x1, y2 < y1, dx > dy
        int err = -dx;
        points.add(new Point(x, y));
        for(int i = 0; i < dx; ++i) {
            x--;
            err += 2 * dy;
            if (err > dx) {
                err -= 2 * dx;
                y--;
            }
            points.add(new Point(x, y));
        }
    }

    private void draw4(int x, int y, List<Point> points){  // x2 < x1, y2 < y1, dx < dy
        int err = -dy;
        points.add(new Point(x, y));
        for(int i = 0; i < dy; ++i) {
            y--;
            err += 2 * dx;
            if (err > dy) {
                err -= 2 * dy;
                x--;
            }
            points.add(new Point(x, y));
        }
    }

    private void draw5(int x, int y, List<Point> points){  // x2 > x1, y2 < y1, dx > dy
        int err = -dx;
        points.add(new Point(x, y));
        for(int i = 0; i < dx; ++i) {
            x++;
            err += 2 * dy;
            if (err > dx) {
                err -= 2 * dx;
                y--;
            }
            points.add(new Point(x, y));
        }
    }

    private void draw6(int x, int y, List<Point> points){  // x2 > x1, y2 < y1, dx < dy
        int err = -dy;
        for(int i = 0; i < dy; ++i) {
            y--;
            err += 2 * dx;
            if (err > dy) {
                err -= 2 * dy;
                x++;
            }
            points.add(new Point(x, y));
        }
    }

    private void draw7(int x, int y, List<Point> points){  // x2 < x1, y2 > y1, dx > dy
        int err = -dx;
        for(int i = 0; i < dx; ++i) {
            x--;
            err += 2 * dy;
            if (err > dx) {
                err -= 2 * dx;
                y++;
            }
            points.add(new Point(x, y));
        }
    }

    private void draw8(int x, int y, List<Point> points){  // x2 < x1, y2 > y1, dx < dy
        int err = -dy;
        points.add(new Point(x, y));
        for(int i = 0; i < dy; ++i) {
            y++;
            err += 2 * dx;
            if (err > dy) {
                err -= 2 * dy;
                x--;
            }
            points.add(new Point(x, y));
        }
    }
}
