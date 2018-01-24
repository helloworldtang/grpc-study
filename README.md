# grpc-study
grpc-study

1. 创建生成Stub的Module : **grpc-hello-api**
2. 在Maven的POM文件中配置生成相关代码的plugin
3. 在 **src/main** (不是src/main/java或src/main/resources目录)下创建proto文件夹
4. 在src/main/proto文件夹下创建一个以 **.proto**为扩展名的基于Protocol Buffers规范编写的服务定义
```text
   syntax 定义Protocol Buffers文件的语法版本
   option定义所生成Java源代码（即Stub代码库）的相关选项
   	>java_package :生成Stub代码所对应的包名
   	>java_outer_classname:生成Stub代码外部类名
   	>java_multiple_files:生成Stub代码是否为多份文件
   package 指定服务接口的包名
   service 定义服务
   rpc 定义方法。 一个服务中可定义一个或多个方法，方法包括方法名、参数或返回值
   message 定义消息。消息包括一个消息名、一个或多个相关属性，属性包括属性类型、属性名称、属性顺序。
   以HelloRequest消息为例，它拥有一个属性，该属性类型为string，属性名称为name,该属性出现在第1位
```
   遇到的几个报错：

```text
[INFO] 
[INFO] --- protobuf-maven-plugin:0.5.0:compile (default) @ grpc-hello-api ---
[INFO] F:\rpc\grpc-study\grpc-hello-api\src\main\proto does not exist. Review the configuration or consider disabling the plugin.
[INFO] 
[INFO] --- protobuf-maven-plugin:0.5.0:compile-custom (default) @ grpc-hello-api ---
[INFO] F:\rpc\grpc-study\grpc-hello-api\src\main\proto does not exist. Review the configuration or consider disabling the plugin.
[INFO] 

[INFO] Compiling 1 proto file(s) to F:\rpc\grpc-study\grpc-hello-api\target\generated-sources\protobuf\java
[ERROR] PROTOC FAILED: hello.proto:5:1: Expected ";".
[ERROR] F:\rpc\grpc-study\grpc-hello-api\src\main\proto\hello.proto [0:0]: hello.proto:5:1: Expected ";".

```

5. 在Module **grpc-hello-api**中执行 
```bash
mvn compile 
```

可以生成stub代码
6. Module **grpc-hello-client** 和 **grpc-hello-server**依赖包含stub的Module**grpc-hello-api**
7. 启动Module**grpc-hello-server**中grpc的服务：
   com.tangcheng.rpc.grpc.hello.service.HelloServer.java中的main方法
8. 运行Module**grpc-hello-client**中访问grpc server提供的服务
   com.tangcheng.rpc.grpc.hello.client.HelloClient.java中的main方法