package com.benjaminstammen.bfi.config

import com.amazonaws.regions.Regions
import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.AmazonS3ClientBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class BfiConfiguration {

    @Bean
    fun amazonS3ClientBean(): AmazonS3 {
        return AmazonS3ClientBuilder
            .standard()
            .withRegion(Regions.DEFAULT_REGION)
            .build()
    }
}