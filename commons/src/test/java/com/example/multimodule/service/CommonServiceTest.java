package com.example.multimodule.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest("service.message=Hello")
public class CommonServiceTest {

	@Autowired
	private CommonService commonService;

	@Test
	public void contextLoads() {
		assertThat(commonService.message()).isNotNull();
	}

	@SpringBootApplication
	static class TestConfiguration {
	}

}
