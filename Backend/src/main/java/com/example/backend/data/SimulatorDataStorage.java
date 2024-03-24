package com.example.backend.data;

import com.example.backend.model.Antenna;
import com.example.backend.model.Wall;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArrayList;

public class SimulatorDataStorage {
    public static final CopyOnWriteArrayList<Antenna> antennas = new CopyOnWriteArrayList<>();

    public static final int TX_POWER = 23;
    public static final int A_MAX = 30;
    public static final int ANGLE_3DB = 65;
    public static final int FIELD_SIZE = 740;
    public static final int BLOCK_SIZE = 370;
    public static final int WALL_WIGHT = 1;

    public static final double f = 3.6;

    public static Wall[] walls;
    public static BufferedImage wallsImage;

    public static void initialize() {
        generateWalls();
        saveImage();
    }

    public static void generateWalls() {
        walls = new Wall[] {
                new Wall(50, 20, 675, 2),
                new Wall(20, 50, 2, 612),

                new Wall(75, 20, 2, 25),
                new Wall(105, 20, 2, 25),
                new Wall(140, 20, 2, 25),
                new Wall(175, 20, 2, 25),
                new Wall(190, 20, 2, 25),
                new Wall(210, 20, 2, 25),
                new Wall(230, 20, 2, 25),
                new Wall(245, 20, 2, 25),
                new Wall(265, 20, 2, 25),
                new Wall(285, 20, 2, 25),
                new Wall(320, 20, 2, 25),
                new Wall(350, 20, 2, 25),
                new Wall(365, 20, 2, 25),
                new Wall(380, 20, 2, 25),
                new Wall(410, 20, 2, 25),
                new Wall(440, 20, 2, 25),
                new Wall(455, 20, 2, 25),
                new Wall(470, 20, 2, 25),
                new Wall(505, 20, 2, 25),
                new Wall(535, 20, 2, 25),
                new Wall(550, 20, 2, 25),
                new Wall(580, 20, 2, 25),
                new Wall(595, 20, 2, 25),
                new Wall(610, 20, 2, 25),
                new Wall(640, 20, 2, 25),
                new Wall(655, 20, 2, 25),
                new Wall(675, 20, 2, 25),
                new Wall(725, 20, 2, 276),

                new Wall(75, 45, 5, 2),
                new Wall(85, 45, 45, 2),
                new Wall(135, 45, 25, 2),
                new Wall(165, 45, 15, 2),
                new Wall(185, 45, 15, 2),
                new Wall(205, 45, 10, 2),
                new Wall(220, 45, 12, 2),
                new Wall(245, 45, 10, 2),
                new Wall(260, 45, 15, 2),
                new Wall(280, 45, 20, 2),
                new Wall(280, 45, 20, 2),
                new Wall(305, 45, 20, 2),
                new Wall(330, 45, 25, 2),
                new Wall(360, 45, 7, 2),
                new Wall(380, 45, 15, 2),
                new Wall(400, 45, 20, 2),
                new Wall(425, 45, 20, 2),
                new Wall(450, 45, 7, 2),
                new Wall(470, 45, 20, 2),
                new Wall(495, 45, 20, 2),
                new Wall(520, 45, 20, 2),
                new Wall(545, 45, 20, 2),
                new Wall(570, 45, 15, 2),
                new Wall(590, 45, 10, 2),
                new Wall(605, 45, 7, 2),
                new Wall(640, 45, 20, 2),
                new Wall(665, 45, 20, 2),
                new Wall(690, 45, 35, 2),

                new Wall(20, 75, 25, 2),
                new Wall(20, 90, 25, 2),
                new Wall(20, 105, 25, 2),
                new Wall(20, 130, 25, 2),
                new Wall(20, 145, 25, 2),
                new Wall(20, 160, 25, 2),
                new Wall(20, 180, 25, 2),
                new Wall(20, 200, 25, 2),
                new Wall(20, 215, 25, 2),
                new Wall(20, 240, 25, 2),
                new Wall(20, 265, 25, 2),
                new Wall(20, 305, 25, 2),
                new Wall(20, 320, 25, 2),
                new Wall(20, 360, 25, 2),
                new Wall(20, 375, 25, 2),
                new Wall(20, 390, 25, 2),
                new Wall(20, 415, 25, 2),
                new Wall(20, 430, 25, 2),
                new Wall(20, 455, 25, 2),
                new Wall(20, 475, 25, 2),
                new Wall(20, 495, 25, 2),
                new Wall(20, 515, 25, 2),
                new Wall(20, 535, 25, 2),
                new Wall(20, 560, 25, 2),
                new Wall(20, 575, 25, 2),
                new Wall(20, 590, 25, 2),
                new Wall(20, 610, 25, 2),
                new Wall(20, 660, 276, 2),

                new Wall(45, 90, 2, 25),
                new Wall(45, 120, 2, 20),
                new Wall(45, 145, 2, 10),
                new Wall(45, 160, 2, 15),
                new Wall(45, 180, 2, 15),
                new Wall(45, 215, 2, 15),
                new Wall(45, 235, 2, 20),
                new Wall(45, 260, 2, 20),
                new Wall(45, 285, 2, 25),
                new Wall(45, 315, 2, 25),
                new Wall(45, 345, 2, 20),
                new Wall(45, 370, 2, 15),
                new Wall(45, 390, 2, 15),
                new Wall(45, 410, 2, 15),
                new Wall(45, 430, 2, 15),
                new Wall(45, 450, 2, 15),
                new Wall(45, 470, 2, 15),
                new Wall(45, 490, 2, 15),
                new Wall(45, 510, 2, 15),
                new Wall(45, 530, 2, 20),
                new Wall(45, 555, 2, 10),
                new Wall(45, 570, 2, 10),
                new Wall(45, 585, 2, 15),
                new Wall(45, 605, 2, 20),
                new Wall(45, 630, 2, 30),

                new Wall(90, 105, 2, 487),
                new Wall(105, 90, 575, 2),

                new Wall(90, 590, 165, 2),
                new Wall(655, 90, 2, 155),

                new Wall(65, 105, 25, 2),
                new Wall(65, 125, 25, 2),
                new Wall(65, 140, 25, 2),
                new Wall(65, 155, 25, 2),
                new Wall(65, 180, 25, 2),
                new Wall(65, 200, 25, 2),
                new Wall(65, 215, 25, 2),
                new Wall(65, 230, 25, 2),
                new Wall(65, 250, 25, 2),
                new Wall(65, 275, 25, 2),
                new Wall(65, 315, 25, 2),
                new Wall(65, 355, 25, 2),
                new Wall(65, 370, 25, 2),
                new Wall(65, 405, 25, 2),
                new Wall(65, 430, 25, 2),
                new Wall(65, 450, 25, 2),
                new Wall(65, 470, 25, 2),
                new Wall(65, 495, 25, 2),
                new Wall(65, 520, 25, 2),
                new Wall(65, 550, 25, 2),
                new Wall(65, 590, 25, 2),

                new Wall(65, 105, 2, 5),
                new Wall(65, 115, 2, 15),
                new Wall(65, 135, 2, 10),
                new Wall(65, 150, 2, 15),
                new Wall(65, 170, 2, 20),
                new Wall(65, 195, 2, 7),
                new Wall(65, 215, 2, 5),
                new Wall(65, 225, 2, 10),
                new Wall(65, 240, 2, 15),
                new Wall(65, 260, 2, 20),
                new Wall(65, 285, 2, 20),
                new Wall(65, 310, 2, 20),
                new Wall(65, 335, 2, 25),
                new Wall(65, 365, 2, 20),
                new Wall(65, 390, 2, 25),
                new Wall(65, 420, 2, 10),
                new Wall(65, 450, 2, 10),
                new Wall(65, 465, 2, 15),
                new Wall(65, 485, 2, 20),
                new Wall(65, 510, 2, 20),
                new Wall(65, 535, 2, 20),
                new Wall(65, 560, 2, 40),
                new Wall(65, 605, 2, 10),

                new Wall(105, 65, 2, 25),
                new Wall(125, 65, 2, 25),
                new Wall(145, 65, 2, 25),
                new Wall(165, 65, 2, 25),
                new Wall(185, 65, 2, 25),
                new Wall(205, 65, 2, 25),
                new Wall(230, 65, 2, 25),
                new Wall(245, 65, 2, 25),
                new Wall(260, 65, 2, 25),
                new Wall(300, 65, 2, 25),
                new Wall(325, 65, 2, 25),
                new Wall(340, 65, 2, 25),
                new Wall(375, 65, 2, 25),
                new Wall(400, 65, 2, 25),
                new Wall(425, 65, 2, 25),
                new Wall(440, 65, 2, 25),
                new Wall(455, 65, 2, 25),
                new Wall(470, 65, 2, 25),
                new Wall(490, 65, 2, 25),
                new Wall(510, 65, 2, 25),
                new Wall(530, 65, 2, 25),
                new Wall(550, 65, 2, 25),
                new Wall(570, 65, 2, 25),
                new Wall(600, 65, 2, 25),
                new Wall(630, 65, 2, 25),
                new Wall(660, 65, 2, 25),

                new Wall(105, 65, 10, 2),
                new Wall(120, 65, 15, 2),
                new Wall(140, 65, 15, 2),
                new Wall(160, 65, 15, 2),
                new Wall(180, 65, 15, 2),
                new Wall(200, 65, 15, 2),
                new Wall(220, 65, 10, 2),
                new Wall(245, 65, 5, 2),
                new Wall(255, 65, 15, 2),
                new Wall(275, 65, 35, 2),
                new Wall(315, 65, 20, 2),
                new Wall(340, 65, 20, 2),
                new Wall(365, 65, 25, 2),
                new Wall(395, 65, 20, 2),
                new Wall(420, 65, 10, 2),
                new Wall(435, 65, 10, 2),
                new Wall(450, 65, 10, 2),
                new Wall(465, 65, 15, 2),
                new Wall(485, 65, 15, 2),
                new Wall(505, 65, 7, 2),
                new Wall(530, 65, 5, 2),
                new Wall(540, 65, 15, 2),
                new Wall(560, 65, 15, 2),
                new Wall(580, 65, 30, 2),
                new Wall(615, 65, 25, 2),
                new Wall(645, 65, 35, 2),

                new Wall(65, 635, 2, 25),
                new Wall(85, 635, 2, 25),
                new Wall(105, 635, 2, 25),
                new Wall(120, 635, 2, 25),
                new Wall(135, 635, 2, 25),
                new Wall(150, 635, 2, 25),
                new Wall(165, 635, 2, 25),
                new Wall(180, 635, 2, 25),
                new Wall(195, 635, 2, 25),
                new Wall(245, 635, 2, 25),

                new Wall(65, 635, 10, 2),
                new Wall(80, 635, 15, 2),
                new Wall(100, 635, 10, 2),
                new Wall(115, 635, 10, 2),
                new Wall(130, 635, 10, 2),
                new Wall(145, 635, 10, 2),
                new Wall(160, 635, 10, 2),
                new Wall(175, 635, 10, 2),
                new Wall(190, 635, 15, 2),
                new Wall(210, 635, 35, 2),

                new Wall(90, 590, 2, 25),
                new Wall(110, 590, 2, 25),
                new Wall(125, 590, 2, 25),
                new Wall(155, 590, 2, 25),
                new Wall(170, 590, 2, 25),
                new Wall(185, 590, 2, 25),
                new Wall(200, 590, 2, 25),
                new Wall(215, 590, 2, 25),
                new Wall(255, 590, 2, 25),

                new Wall(65, 615, 35, 2),
                new Wall(105, 615, 7, 2),
                new Wall(125, 615, 15, 2),
                new Wall(145, 615, 15, 2),
                new Wall(165, 615, 10, 2),
                new Wall(180, 615, 7, 2),
                new Wall(200, 615, 7, 2),
                new Wall(212, 615, 18, 2),
                new Wall(235, 615, 22, 2),

                new Wall(700, 65, 25, 2),
                new Wall(700, 80, 25, 2),
                new Wall(700, 100, 25, 2),
                new Wall(700, 115, 25, 2),
                new Wall(700, 130, 25, 2),
                new Wall(700, 155, 25, 2),
                new Wall(700, 180, 25, 2),
                new Wall(700, 230, 25, 2),

                new Wall(700, 65, 2, 5),
                new Wall(700, 75, 2, 10),
                new Wall(700, 90, 2, 15),
                new Wall(700, 110, 2, 10),
                new Wall(700, 125, 2, 15),
                new Wall(700, 145, 2, 15),
                new Wall(700, 165, 2, 25),
                new Wall(700, 195, 2, 35),

                new Wall(655, 110, 25, 2),
                new Wall(655, 125, 25, 2),
                new Wall(655, 150, 25, 2),
                new Wall(655, 165, 25, 2),
                new Wall(655, 180, 25, 2),
                new Wall(655, 195, 25, 2),
                new Wall(655, 215, 25, 2),
                new Wall(655, 245, 25, 2),

                new Wall(678, 65, 2, 10),
                new Wall(678, 80, 2, 20),
                new Wall(678, 105, 2, 5),
                new Wall(678, 125, 2, 5),
                new Wall(678, 135, 2, 20),
                new Wall(678, 160, 2, 10),
                new Wall(678, 175, 2, 10),
                new Wall(678, 190, 2, 15),
                new Wall(678, 210, 2, 15),
                new Wall(678, 230, 2, 15),

                new Wall(90, 590, 165, 2),
                new Wall(255, 590, 2, 25),

                new Wall(20, 50, 42, 2, -45),
                new Wall(90, 105, 22, 2, -45),
                new Wall(255, 590, 124, 2, -45),
                new Wall(296, 659, 146, 2, -45),

                new Wall(317, 532, 78, 2, 45),
                new Wall(344, 500, 78, 2, 45),

                new Wall(255, 590, 10, 2, 45),
                new Wall(265, 600, 64, 2, 45),
                new Wall(658, 245, 10, 2, 45),
                new Wall(668, 255, 70, 2, 45),

                new Wall(658, 245, 124, 2, 135),
                new Wall(727, 296, 136, 2, 135),
                new Wall(571, 331, 85, 2, 45),
                new Wall(603, 300, 85, 2, 45)
        };
    }

    public static void saveImage() {
        wallsImage = new BufferedImage(FIELD_SIZE, FIELD_SIZE, BufferedImage.TYPE_INT_ARGB);
        Graphics g = wallsImage.getGraphics();

        g.setColor(Color.WHITE);
        g.fillRect(0, 0, FIELD_SIZE, FIELD_SIZE);

        g.setColor(Color.BLACK);
        ((Graphics2D) g).setStroke(new BasicStroke(WALL_WIGHT));

        for (Wall wall : walls) {
            Graphics2D g2d = (Graphics2D) g.create();

            g2d.translate(wall.getX(), wall.getY());
            g2d.rotate(Math.toRadians(wall.getRotate()));
            g2d.drawRect(0, 0, wall.getWidth(), wall.getLength());
        }

        g.dispose();

        try {
            ImageIO.write(wallsImage, "PNG", new File("src/main/resources/file.png"));
        } catch (IOException e) {
            System.out.println("Ошибка при сохранении изображения: " + e.getMessage());
        }
    }
}
