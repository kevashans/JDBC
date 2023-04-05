package org.Comparator;

import org.DTOs.Player;
import org.DTOs.Scout;

import java.sql.Date;
import java.util.Comparator;

public class CompDOB implements Comparator<Scout> {

    @Override
    public int compare(Scout o1, Scout o2) {
        Date date1 = Date.valueOf(o1.getDOB());
        Date date2 = Date.valueOf(o2.getDOB());

        if (date1.after(date2)){
            return 1;
        }else if(date2.after(date1)){
            return -1;
        }
        return 0;
    }
}
