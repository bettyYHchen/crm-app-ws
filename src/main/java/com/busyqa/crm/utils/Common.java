package com.busyqa.crm.utils;

import com.busyqa.crm.model.User;

import java.util.List;

public class Common {
    public List<User> mergeTwoList(List<User> l1, List<User> l2){

        for (User x : l2){
            if (!l1.contains(x))
                l1.add(x);
        }
        return l1;
    }
}
