package com.popovgosha.motelbackend.utils;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Georgiy Popov on 22.04.2016.
 */
public class DateConverter {

    public Date calendarToDate(Calendar calendar) {
        return calendar.getTime();
    }

}
