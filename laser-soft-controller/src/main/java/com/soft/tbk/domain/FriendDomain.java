package com.soft.tbk.domain;

import com.soft.tbk.model.TbkUser;

public class FriendDomain extends TbkUser {

    private Long friendAccount;

    public Long getFriendAccount() {

        return friendAccount;
    }

    public void setFriendAccount(Long friendAccount) {

        this.friendAccount = friendAccount;
    }

}