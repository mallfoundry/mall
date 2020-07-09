/*
 * Copyright (C) 2019-2020 the original author or authors.
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */

package org.mallfoundry.autoconfigure.storage;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import org.mallfoundry.storage.LocalStorageSystem;
import org.mallfoundry.storage.StoragePathReplacer;
import org.mallfoundry.storage.StorageSystem;
import org.mallfoundry.storage.aliyun.AliyunStorageSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.EncodedResourceResolver;

@Configuration
@Import(StorageFtpConfiguration.class)
@EnableConfigurationProperties(StorageProperties.class)
public class StorageAutoConfiguration implements WebMvcConfigurer {

    @Bean
    public StoragePathReplacer storagePathReplacer(StorageProperties properties) {
        var output = properties.getOutput();
        return new StoragePathReplacer(output.getPath(), output.getFilename());
    }

    @Bean
    @ConditionalOnClass(LocalStorageSystem.class)
    @ConditionalOnProperty(prefix = "mall.storage", name = "type", havingValue = "local")
    public LocalStorageSystem localStorageSystem(StorageProperties properties) {
        var local = properties.getLocal();
        return new LocalStorageSystem(local.getDirectory(), properties.getBaseUrl());
    }

    @Bean
    @ConditionalOnClass(AliyunStorageSystem.class)
    @ConditionalOnProperty(prefix = "mall.storage", name = "type", havingValue = "aliyun")
    public StorageSystem storageSystem(StorageProperties properties,
                                       @Autowired(required = false) StoragePathReplacer pathReplacer) {
        var aliyun = properties.getAliyun();
        OSS client = new OSSClientBuilder().build(aliyun.getEndpoint(), aliyun.getAccessKeyId(), aliyun.getAccessKeySecret());
        var ass = new AliyunStorageSystem(client, aliyun.getBucketName(), properties.getBaseUrl());
        ass.setPathReplacer(pathReplacer);
        return ass;
    }

    @Configuration
    @ConditionalOnProperty(prefix = "mall.storage", name = "type", havingValue = "local")
    public static class ResourceHandlerConfiguration implements WebMvcConfigurer {

        private final StorageProperties properties;

        public ResourceHandlerConfiguration(StorageProperties properties) {
            this.properties = properties;
        }

        @Override
        public void addResourceHandlers(ResourceHandlerRegistry registry) {
            registry.addResourceHandler("/static/**")
                    .addResourceLocations("file:" + properties.getLocal().getDirectory())
                    .setCachePeriod(3000)
                    .resourceChain(true)
                    .addResolver(new EncodedResourceResolver());
        }
    }
}
