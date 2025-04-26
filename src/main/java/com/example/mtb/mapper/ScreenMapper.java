package com.example.mtb.mapper;

import com.example.mtb.dto.ScreenResponse;
import com.example.mtb.dto.SeatRespose;
import com.example.mtb.entity.Screen;
import com.example.mtb.entity.Seat;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

@Component
public class ScreenMapper {

    public ScreenResponse screenResponseMapper(Screen screen) {
        if (screen == null)
            return null;
        return new ScreenResponse(
                screen.getScreenId(),
                screen.getScreenType(),
                screen.getCapacity(),
                screen.getNoOfRows(),
                seatResposeMapper(screen.getSeats())

        );
    }

    private SeatRespose seatResposeMapper (List<Seat> seats){
        List<String> seatId = new LinkedList<>();
        List<String> seatName = new LinkedList<>();
        for (Seat seat : seats){
            seatId.add(seat.getSeatId());
            seatName.add(seat.getName());
        }
        return SeatRespose.builder()
                .name(seatName)
                .seatId(seatId)
                .build();
    }
}
