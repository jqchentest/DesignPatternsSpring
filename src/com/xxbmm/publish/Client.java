package com.xxbmm.publish;

/**
 * 客户端
 * @author chenjunqiang 2019/1/22
 **/
public class Client {
    private static GongzhonghaoSubject gongzhonghao = new GongzhonghaoSubject();
    private static XiaochengxuSubject xiaochengxu = new XiaochengxuSubject();

    static {
        //创建微信用户
        WeixinUser user1 = new WeixinUser("宋洋(๑ŐдŐ)b");
        WeixinUser user2 = new WeixinUser("宋青龙");
        WeixinUser user3 = new WeixinUser("李泽坤 皮卡皮卡001号");
        //订阅公众号
        gongzhonghao.attach(user1);
        gongzhonghao.attach(user2);
        gongzhonghao.attach(user3);
        //订阅小程序
        xiaochengxu.attach(user1);
        xiaochengxu.attach(user3);
    }

    public static void main(String[] args) {
        //公众号更新发出消息给订阅的微信用户
        gongzhonghao.notify("小小包麻麻文章更新了");
        xiaochengxu.notify("育儿锦囊有新视频啦");
    }
}
