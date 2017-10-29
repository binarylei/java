package com.github.binarylei.t3.util;

import io.netty.handler.codec.marshalling.*;
import org.jboss.marshalling.MarshallerFactory;
import org.jboss.marshalling.Marshalling;
import org.jboss.marshalling.MarshallingConfiguration;

public class MarshallingCodeFactory {

    //创建 jboss-marshalling 解码源 MarshallingDecoder
    public static MarshallingDecoder buildMarshallingDecoder () {
        //1. 通过 Marshalling 工具类获取 Marshalling 实例对象，参数 serial 标识创建的是 java 序列化工厂对象
        final MarshallerFactory marshallerFactory = Marshalling.getProvidedMarshallerFactory("serial");

        //2. 创建 MarshallingConfiguration 对象，配置了版本号为5
        final MarshallingConfiguration configuration = new MarshallingConfiguration();
        configuration.setVersion(5);

        //3. 根据 marshallerFactory 和 configuration 创建 provider
        UnmarshallerProvider provider = new DefaultUnmarshallerProvider(marshallerFactory, configuration);

        //4. 构建 Netty 的 MarshallingDecoder 对象，两个参数分别为 provider 和 单个消息序列化后的最大长度
        return new MarshallingDecoder(provider, 1024 * 1024 *1);
    }

    //创建 jboss-marshalling 编码源 MarshallingEncoder
    public static MarshallingEncoder buildMarshallingEncoder () {
        //1. 通过 Marshalling 工具类获取 Marshalling 实例对象，参数 serial 标识创建的是 java 序列化工厂对象
        final MarshallerFactory marshallerFactory = Marshalling.getProvidedMarshallerFactory("serial");

        //2. 创建 MarshallingConfiguration 对象，配置了版本号为5
        final MarshallingConfiguration configuration = new MarshallingConfiguration();
        configuration.setVersion(5);

        //3. 根据 marshallerFactory 和 configuration 创建 provider
        MarshallerProvider provider = new DefaultMarshallerProvider(marshallerFactory, configuration);

        //4. 构建 Netty 的 MarshallingEncoder 对象，参数为 provider
        return new MarshallingEncoder(provider);
    }
}
