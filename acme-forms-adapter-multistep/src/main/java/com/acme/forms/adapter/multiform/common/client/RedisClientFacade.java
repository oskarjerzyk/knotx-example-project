/*
 * Copyright (C) 2018 Knot.x Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.acme.forms.adapter.multiform.common.client;

import com.acme.forms.adapter.multiform.common.configuration.RedisConfiguration;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.reactivex.core.Vertx;
import io.vertx.reactivex.redis.RedisClient;
import io.vertx.redis.RedisOptions;

public class RedisClientFacade {

  private static final Logger LOGGER = LoggerFactory.getLogger(
      RedisClientFacade.class);
  private RedisClient redis;


  public RedisClientFacade(Vertx vertx, RedisConfiguration configuration) {
    RedisOptions config = new RedisOptions()
        .setHost(configuration.getHost())
        .setPort(configuration.getPort());

    this.redis = RedisClient.create(vertx, config);
  }

  public void process() {
    LOGGER.info("RedisConfiguration process");

    redis.get("mykey", res -> {
      if (res.succeeded()) {
        LOGGER.info(res.result());
      } else {
        LOGGER.info("ERROR");
      }
    });
  }
}
