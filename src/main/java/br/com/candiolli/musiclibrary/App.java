package br.com.candiolli.musiclibrary;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;

@SpringBootApplication
public class App {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

	@Bean
	RouterFunction<?> routes(RouteHandles routeHandles) {
		return RouterFunctions.route(
				RequestPredicates.GET("/music"), routeHandles::allMusics)
				.andRoute(RequestPredicates.GET("/music/{musicId}"), routeHandles::musicById)
				.andRoute(RequestPredicates.POST("/music"), routeHandles::musicCreate);



//				.andRoute(RequestPredicates.GET("/cars/{carId}/events"), routeHandles::events);
	}
}
