package com.pnia.challenge;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.boot.test.context.SpringBootTest;

import static com.tngtech.archunit.library.Architectures.layeredArchitecture;

@SpringBootTest
public class ChallengeApplicationTests {

	@Test
	@DisplayName("Test application's architecture")
	public void archTesting() {
		JavaClasses javaClasses = new ClassFileImporter().importPackages("com.pnia.challenge");

		ArchRule basicLayersRule = layeredArchitecture()
				.layer("Config").definedBy("..config..")
				.layer("Domain").definedBy("..domain..")
				.layer("Usecase").definedBy("..usecase..")
				.layer("Infrastructure").definedBy("..infrastructure..");

		basicLayersRule.check(javaClasses);
	}

}
