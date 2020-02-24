package wtsd.dbaudit.Monitor.Service;

import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import wtsd.dbaudit.Monitor.Wechat.Entity.RequestMessage;
import wtsd.dbaudit.Monitor.Wechat.Entity.Text;
import wtsd.dbaudit.Monitor.Wechat.Entity.WeiXinParames;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static wtsd.dbaudit.Monitor.Wechat.Utils.SendWeiXinUtils.sendWeiXinMessage;

@Component
@EnableAsync
public class WeiXinMessageSend {

    @Autowired
    private FreeMarkerConfigurer configurer;

    @Async
    public <T> void send(String To,Integer AgentId,String Secret,List<T> args,String templateName) {
        RequestMessage requestMessage = new RequestMessage();

        Map<String, Object> model = new HashMap<>();
        model.put("params", args);
        try {
            Template template = configurer.getConfiguration().getTemplate(templateName);
            try {
                String content = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
                Text text = new Text();
                text.setContent(content);
                requestMessage.setText(text);
            } catch (TemplateException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<String> list = Arrays.asList(To.split(","));

        requestMessage.setTotag(list);
        requestMessage.setMsgtype("text");
        requestMessage.setAgentid(AgentId);

        WeiXinParames weiXinParames = new WeiXinParames();
        weiXinParames.setCorpId("wxa3c7c7c29539e1fd");
        weiXinParames.setAgentSecret(Secret);

        sendWeiXinMessage(requestMessage, weiXinParames);

    }
}
