package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.kristofa.brave.Brave;
import com.github.kristofa.brave.Brave.Builder;
import com.github.kristofa.brave.EmptySpanCollectorMetricsHandler;
import com.github.kristofa.brave.SpanCollector;
import com.github.kristofa.brave.http.HttpSpanCollector;
import com.github.kristofa.brave.http.HttpSpanCollector.Config;
import com.github.kristofa.brave.Brave;
import com.github.kristofa.brave.EmptySpanCollectorMetricsHandler;
import com.github.kristofa.brave.Sampler;
import com.github.kristofa.brave.SpanCollector;
import com.github.kristofa.brave.http.DefaultSpanNameProvider;
import com.github.kristofa.brave.http.HttpSpanCollector;
import com.github.kristofa.brave.okhttp.BraveOkHttpRequestResponseInterceptor;
import com.github.kristofa.brave.servlet.BraveServletFilter;

import okhttp3.OkHttpClient;


@Configuration
public class ZipkinConfig {
	
	@Bean
	public SpanCollector spanCollector() {
		Config config =HttpSpanCollector.Config.builder()
				.compressionEnabled(false)
				.connectTimeout(5000)
				.flushInterval(1)
				.readTimeout(6000)
				.build();
		return HttpSpanCollector.create("http://192.168.68.182:9411", config, new EmptySpanCollectorMetricsHandler());
	}
	@Bean
	public Brave brave(SpanCollector spanCollector){
		 Builder builder = new Brave.Builder("sleuth1");
		builder.spanCollector(spanCollector);
		 builder.traceSampler(Sampler.create(1));
		 
		 return builder.build();
	}

	@Bean
	public BraveServletFilter braveServletFilter(Brave brave) {
	   BraveServletFilter filter = new BraveServletFilter(brave.serverRequestInterceptor(),
	   brave.serverResponseInterceptor(), new DefaultSpanNameProvider());
	    return filter;
	}
	@Bean
	public OkHttpClient okHttpClient(Brave brave){
		OkHttpClient httpClient = new OkHttpClient.Builder()
		.addInterceptor(new BraveOkHttpRequestResponseInterceptor(
		 brave.clientRequestInterceptor(),
	     brave.clientResponseInterceptor(),
	     new DefaultSpanNameProvider())).build();
		return httpClient;
	}

		
}
