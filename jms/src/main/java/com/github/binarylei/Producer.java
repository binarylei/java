package com.github.binarylei;

import org.apache.activemq.ActiveMQConnectionFactory;
import sun.java2d.d3d.D3DDrawImage;

import javax.jms.*;

public class Producer {

    public static void main(String[] args) throws JMSException {
        //1. 创建 ConnectionFactory
        ConnectionFactory factory = new ActiveMQConnectionFactory(
                ActiveMQConnectionFactory.DEFAULT_USER,
                ActiveMQConnectionFactory.DEFAULT_PASSWORD,
                "tcp://localhost:61616/"
        );

        //2. 创建 connection，并启动连接
        Connection connection = factory.createConnection();
        connection.start();

        //3. 通过 connection 创建 session 会话。参数1：是否开启事务；参数2：签收模式，一般为自动签收
        Session session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);
;
        //4. 通过 session 创建 Destination 对象，指定生产消息目标禾消费消息来源的对象。
        Destination dest = session.createQueue("queue1");

        //5. 创建消息的发送和接收对象
        MessageProducer producer = session.createProducer(dest);

        //6. 设定持久化特性和非持久化
        producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

        //7. 创建消息
        for (int i = 0; i < 10; i++) {
            TextMessage message = session.createTextMessage("aaaaaaaaaaaaaaa");
            producer.send(message);
        }

        if (connection != null) {
            connection.close();
        }
    }
}
