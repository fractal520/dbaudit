package wtsd.dbaudit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import wtsd.dbaudit.Monitor.Service.WeiXinMessageSend;
import wtsd.dbaudit.Monitor.dao.oms.TbInfStationRepository;
import wtsd.dbaudit.Monitor.dao.tpc.TbBizCardSaleRepository;
import wtsd.dbaudit.Monitor.dao.tpc.TbBizLotteryRepository;
import wtsd.dbaudit.Monitor.dao.tpc.TbBizRechargeRepository;
import wtsd.dbaudit.Monitor.db.tpc.TbBizCardSale;
import wtsd.dbaudit.Monitor.db.tpc.TbBizLottery;
import wtsd.dbaudit.Monitor.db.tpc.TbBizRecharge;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DbauditApplicationTests {
    @Autowired
    TbBizRechargeRepository tbBizRechargeRepository;
    @Autowired
    TbBizCardSaleRepository tbBizCardSaleRepository;
    @Autowired
    TbBizLotteryRepository tbBizLotteryRepository;
    @Autowired
    TbInfStationRepository tbInfStationRepository;
    @Autowired
    WeiXinMessageSend weiXinMessageSend;
    @Test
    public void contextLoads() {
    }

    @Test
    public void recharge(){
        TbBizRecharge tbBizRecharge=tbBizRechargeRepository.findByTranseq("180251141972");
        assertEquals((long) 7000, (long)tbBizRecharge.getAmount());
    }

    @Test
    public void cardsale(){
        TbBizCardSale tbBizCardSale=tbBizCardSaleRepository.findByTranseq("110000034474");
        assertEquals((long) 5000, (long)tbBizCardSale.getPrice());
    }

    @Test
    public void lottery(){
        TbBizLottery tbBizLottery=tbBizLotteryRepository.findByTranseq("190211052202");
        assertEquals((long) 2000, (long)tbBizLottery.getAmount());
    }

    @Test
    public void tbinfstation(){
        String tbInfStation=tbInfStationRepository.findByCompanyid("907550004").get(0).getStatioinid();
        assertEquals("607550113",tbInfStation);
    }

}