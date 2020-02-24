package wtsd.dbaudit.Monitor.Utils;

import lombok.Getter;

@Getter
public enum TransTypeEnums{
    RECHARGE("Recharge","充值"),
    CARDSALE("CardSale","售卡"),
    LOTTERY("Lottery","售彩"),
    LOTTERYCASHING("LotteryCashing","彩票兑奖"),
    TRANSFER("Transfer","转账");

    private String type;
    private String name;

    TransTypeEnums(String type,String name){
        this.type=type;
        this.name=name;
    }
}