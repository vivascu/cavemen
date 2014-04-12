package com.cavemen.inception.events;

import com.cavemen.inception.model.Floor;

public class FloorSelectedEvent {

    private Floor floor;

    public FloorSelectedEvent(Floor currentFloorIndex) {
        floor = currentFloorIndex;
    }

    public Floor getFloor() {
        return floor;
    }
}
