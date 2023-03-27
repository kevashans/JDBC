package org.Comparator;

import org.DTOs.Player;

import java.util.Comparator;

public class CompDraftYear implements Comparator<Player> {


    @Override
    public int compare(Player o1, Player o2) {
        if (o1.getPlayer_draft_year()>o2.getPlayer_draft_year()){
            return 1;
        }else if(o1.getPlayer_draft_year()<o2.getPlayer_draft_year()){
            return -1;
        }
        return 0;
    }
}
