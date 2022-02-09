package com.southsystem.ApiVoting.app.config.docs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

	@Value("${api.docs.release.version}")
	private String releaseVersion;

	@Value("${api.docs.version}")
	private String apiVersion;

	@Value("${api.docs.title}")
	private String apiTitle;

	@Value("${api.docs.description}")
	private String apiDescription;

	/**
	 * sets the endpoints to be documented.
	 * 
	 * @return <code>Docket</code> object
	 */
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.southsystem.ApiVoting.app.resources"))
				.paths(PathSelectors.any()).build().apiInfo(metaInfo());
	}

	/**
	 * returns the API info.
	 * 
	 * @return <code>ApiInfo</code> object
	 */
	private ApiInfo metaInfo() {
		return new ApiInfoBuilder().title(apiTitle).description(apiDescription)
				.contact(new Contact("South System", "https://southsystem.com.br/", null))
				.version(releaseVersion.concat("_").concat(apiVersion)).build();
	}
}