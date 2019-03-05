package cn.qiuzhizhushou.wechat;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("cn.qiuzhizhushou.wechat.mapper")
public class WechatApplication
{

	public static void main(String[] args) {
		SpringApplication.run(WechatApplication.class, args);
	}
}
