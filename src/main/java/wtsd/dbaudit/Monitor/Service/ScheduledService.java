package wtsd.dbaudit.Monitor.Service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import wtsd.dbaudit.Monitor.dao.tpc.TbBizPayRepository;
import wtsd.dbaudit.Monitor.db.tpc.TbBizPay;


@Component
@Slf4j
public class ScheduledService {
    @Autowired
    private Monitor monitor;
    private TbBizPay tbbizpay;
    @Autowired
    private TbBizPayRepository tbBizPayRepository;

    @Scheduled(cron="0 0/1 * * * ? ")
    public void scheduled(){
        log.info("开始检查业务交易记录");
        monitor.monitorProbPay();
        log.info("完成检查业务交易记录");

        log.info("开始检查转账交易");
        monitor.monitorProbTransfer();
        log.info("完成检查转账交易");

        log.info("开始检查彩票兑奖");
        monitor.monitorLotteryCashing();
        log.info("完成检查彩票兑奖");
    }
}
