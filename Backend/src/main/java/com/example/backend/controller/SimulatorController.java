package com.example.backend.controller;

import com.example.backend.data.SimulatorDataStorage;
import com.example.backend.model.Antenna;
import com.example.backend.model.BlockPower;
import com.example.backend.model.Wall;
import com.example.backend.service.SimulatorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class SimulatorController {
    private final SimulatorService simulatorService;

    private static final Logger LOGGER = LoggerFactory.getLogger(SimulatorController.class);

    @Autowired
    public SimulatorController(SimulatorService simulatorService) {
        this.simulatorService = simulatorService;
    }

    @PostMapping("/antenna")
    public ResponseEntity<Void> addAntenna(@RequestBody Antenna antenna) {
        simulatorService.addAntenna(antenna);
        LOGGER.info("Add antenna: " + antenna.getX() + "," + antenna.getY());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/antenna/{antenna-index}")
    public ResponseEntity<Void> deleteAntenna(@PathVariable("antenna-index") Integer antennaIndex) {
        simulatorService.deleteAntenna(antennaIndex);
        LOGGER.info("Deleted antenna: " + antennaIndex);
        return ResponseEntity.ok().build();
    }


    @GetMapping("/block-power/{block-number}")
    public ResponseEntity<BlockPower> getBlockPower(@PathVariable("block-number") Integer blockNumber) {
        LOGGER.info("Sending block power, block number: " + blockNumber);
        return ResponseEntity.ok(simulatorService.getBlockPower(blockNumber));
    }

    @GetMapping("/walls")
    public ResponseEntity<Wall[]> getWalls() {
        LOGGER.info("Send walls");
        return ResponseEntity.ok(SimulatorDataStorage.walls);
    }
}
