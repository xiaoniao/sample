package com.test.tools.utils;

import com.test.tools.model.AccountBankVO;
import com.test.tools.model.UserBankCardDO;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by liuzz on 2018/01/19
 */
public class CompareUtilsTest {

    public static void main(String[] args) {
        CompareUtils<AccountBankVO, UserBankCardDO> compareUtils = new CompareUtils<>();

        List<UserBankCardDO> doList = new ArrayList<>();
        UserBankCardDO userBankCardDO = new UserBankCardDO();
        userBankCardDO.setTradeAccount("1");
        doList.add(userBankCardDO);

        List<AccountBankVO> voList = new ArrayList<>();
        AccountBankVO accountBankVO = new AccountBankVO();
        accountBankVO.setTradeAcco("3");
        voList.add(accountBankVO);
        AccountBankVO accountBankVO2 = new AccountBankVO();
        accountBankVO2.setTradeAcco("2");
        voList.add(accountBankVO2);

        List<UserBankCardDO> deleteList = compareUtils
                .getDeleteDoList(doList, voList, new Compare<UserBankCardDO, AccountBankVO>() {
                    @Override
                    public boolean isEquals(UserBankCardDO l, AccountBankVO r) {
                        return l.getTradeAccount().equals(r.getTradeAcco());
                    }
                });

        List<UserBankCardDO> updateList = compareUtils
                .getUpdateDoList(doList, voList, new Compare<UserBankCardDO, AccountBankVO>() {
                    @Override
                    public boolean isEquals(UserBankCardDO l, AccountBankVO r) {
                        return l.getTradeAccount().equals(r.getTradeAcco());
                    }

                    @Override
                    public UserBankCardDO transformVo2Do(AccountBankVO v) {
                        UserBankCardDO result = new UserBankCardDO();
                        // copy properties
                        return result;
                    }
                });

        List<UserBankCardDO> insertList = compareUtils
                .getInsertDoList(doList, voList, new Compare<UserBankCardDO, AccountBankVO>() {
                    @Override
                    public boolean isEquals(UserBankCardDO l, AccountBankVO r) {
                        return l.getTradeAccount().equals(r.getTradeAcco());
                    }

                    @Override
                    public UserBankCardDO transformVo2Do(AccountBankVO v) {
                        UserBankCardDO result = new UserBankCardDO();
                        result.setTradeAccount(v.getTradeAcco());
                        return result;
                    }
                });

        if (deleteList != null) {
            deleteList.forEach(a -> System.out.println("delete:" + a.getTradeAccount()));
        }
        if (updateList != null) {
            updateList.forEach(a -> System.out.println("update:" + a.getTradeAccount()));
        }
        if (insertList != null) {
            insertList.forEach(a -> System.out.println("insert:" + a.getTradeAccount()));
        }
    }

}
