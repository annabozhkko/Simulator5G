package com.example.backend.service;

import com.example.backend.controller.SimulatorController;
import com.example.backend.exception.SimulatorException;
import com.example.backend.model.Antenna;
import com.example.backend.model.BlockPower;
import com.example.backend.tools.BrazenhemLineDrawer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;

import java.awt.*;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.example.backend.data.SimulatorDataStorage.*;

@Service
@RequestScope
public class SimulatorService {
    private static final Logger LOGGER = LoggerFactory.getLogger(SimulatorController.class);
    private final Integer threadCount = 8;

    private final BrazenhemLineDrawer lineDrawer = new BrazenhemLineDrawer();
    private final ExecutorService executor = Executors.newFixedThreadPool(threadCount);
    private CountDownLatch latch;

    public void addAntenna(Antenna antenna) {
        antennas.add(antenna);
    }

    public void deleteAntenna(int index) {
        antennas.remove(index);
    }

    public BlockPower getBlockPower(Integer blockNumber) {
        long start = System.currentTimeMillis();
        double[] blockPowerArray = new double[BLOCK_SIZE * FIELD_SIZE];

        latch = new CountDownLatch(threadCount);

        for (int threadNumber = 0; threadNumber < threadCount; ++threadNumber) {
            int x = blockNumber * BLOCK_SIZE + threadNumber * (BLOCK_SIZE / threadCount);
            executor.submit(() -> getRowPower(x, blockPowerArray));
        }

        try {
            latch.await();
        } catch (InterruptedException exception) {
            throw new SimulatorException(exception.getMessage());
        }

        long end = System.currentTimeMillis();
        LOGGER.info("Send block: {}, time: {}", blockNumber, end - start);

        BlockPower blockPower = new BlockPower();
        blockPower.setPower(blockPowerArray);
        return blockPower;
    }

    private void getRowPower(int startX, double[] blockPowerArray) {
        for(Antenna antenna : antennas) {
            int antennaX = antenna.getX();
            int antennaY = antenna.getY();
            int antennaDirection = antenna.getDirection();

            for (int x = startX; x < startX + BLOCK_SIZE / threadCount; ++x) {
                for(int y = 0; y < FIELD_SIZE; ++y) {
                    if (wallsImage.getRGB(x, y) == Color.BLACK.getRGB()) {
                        continue;
                    }

                    double horizontalCut = getHorizontalCut(getAngle(x, y, antennaX, antennaY, antennaDirection));
                    //double horizontalCut = 0;
                    double attenuation = getAttenuationCoef(getDistance(x, y, antennaX, antennaY));
                    double wallsAttenuation = getWallsAttenuation(x, y, antennaX, antennaY);
                    //double wallsAttenuation = 0;
                    double power = TX_POWER - attenuation + horizontalCut - wallsAttenuation;
                    blockPowerArray[x % BLOCK_SIZE * FIELD_SIZE + y] = power;
                }
            }
        }
        latch.countDown();
    }

    private double getAngle(int x, int y, int antennaX, int antennaY, int antennaDirection) {
        double w = Math.abs(antennaX - x);
        double h = Math.abs(antennaY - y);

        if (w == 0) {
            return angle(y > antennaY ? 180 - antennaDirection : antennaDirection);
        }
        if (h == 0) {
            return angle(x > antennaX ? 90 - antennaDirection : 90 + antennaDirection);
        }

        double atan = Math.atan(w / h) / Math.PI * 180;
        if (x < antennaX && y < antennaY){
            return angle(atan + antennaDirection);
        }
        if (x > antennaX && y < antennaY){
            return angle(atan - antennaDirection);
        }
        if (x < antennaX && y > antennaY){
            return angle(180 - atan + antennaDirection);
        }
        return angle(180 - atan - antennaDirection);
    }

    private double angle(double angle) {
        if (angle < -180) {
            return 360 - Math.abs(angle);
        }
        return angle < 180 ? angle : 360 - angle;
    }

    private double getHorizontalCut(double angle) {
        return -Math.min(12 * Math.pow(angle / ANGLE_3DB, 2), A_MAX);
    }

    private double getDistance(int x, int y, int antennaX, int antennaY) {
        double w = Math.abs(antennaX - x);
        double h = Math.abs(antennaY - y);
        return Math.sqrt(w * w + h * h) / 5;
    }

    private double getAttenuationCoef(double distance){
        return 32.4 + 17.3 * Math.log10(distance) + 20 * Math.log10(f);
    }

    private double getWallsAttenuation(int x, int y, int antennaX, int antennaY) {
        List<Point> points = lineDrawer.getLinePoints(x, y, antennaX, antennaY);
        int count = 0;
        boolean isWall = false;
        for(Point point : points){
            if (wallsImage.getRGB(point.x, point.y) == Color.BLACK.getRGB()) {
                if (!isWall) {
                    isWall = true;
                    count++;
                }
            } else {
                if (isWall) {
                    isWall = false;
                }
            }
        }
        return count * getPenetrationLoss();
    }

    private double getPenetrationLoss() {
        return 5 + 4 * f;
    }

}
