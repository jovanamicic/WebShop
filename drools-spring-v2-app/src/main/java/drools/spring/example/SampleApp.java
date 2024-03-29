package drools.spring.example;

import java.util.Arrays;

import org.kie.api.KieServices;
import org.kie.api.builder.KieScanner;
import org.kie.api.runtime.KieContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class SampleApp {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(SampleApp.class, args); 
       
	}
	
	@Bean
    public KieContainer kieContainer() {
		KieServices ks = KieServices.Factory.get();
		KieContainer kContainer = ks.newKieContainer(ks.newReleaseId("drools-spring-v2","drools-spring-v2-kjar", "0.0.1-SNAPSHOT"));
		KieScanner kScanner = ks.newKieScanner(kContainer);
		kScanner.start(10000);
		return kContainer;
    }
	/*
	 * KieServices ks = KieServices.Factory.get();
	 * KieContainer kContainer = ks.newKieContainer(ks.newReleaseId("drools-spring-v2","drools-spring-v2-kjar", "0.0.1-SNAPSHOT"));
	 * KieScanner kScanner = ks.newKieScanner(kContainer);
	 * kScanner.start(10_000);
	 * KieSession kSession = kContainer.newKieSession();
	 */
}
