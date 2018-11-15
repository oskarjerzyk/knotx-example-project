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
package com.acme.forms.adapter.multiform;


import com.acme.forms.adapter.multiform.common.configuration.MultiStepFormsAdapterConfiguration;
import io.knotx.forms.api.FormsAdapterProxy;
import io.knotx.proxy.KnotProxy;
import io.vertx.core.Context;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.MessageConsumer;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.reactivex.core.AbstractVerticle;
import io.vertx.serviceproxy.ServiceBinder;

public class MultiStepFormsAdapterVerticle extends AbstractVerticle {

  private static final Logger LOGGER = LoggerFactory.getLogger(MultiStepFormsAdapterVerticle.class);

  private MultiStepFormsAdapterConfiguration configuration;

  private MessageConsumer<JsonObject> consumer;

  private ServiceBinder adapterServiceBinder;

  private ServiceBinder sessionServiceBinder;

  @Override
  public void init(Vertx vertx, Context context) {
    super.init(vertx, context);
    this.configuration = new MultiStepFormsAdapterConfiguration(config());
  }

  @Override
  public void start() throws Exception {
    LOGGER.info("Starting <{}>", this.getClass().getSimpleName());

    //register the service proxy on event bus
    adapterServiceBinder = new ServiceBinder(getVertx());
    consumer = adapterServiceBinder
        .setAddress(configuration.getAdapterAddress())
        .register(FormsAdapterProxy.class, new MultiStepFormsAdapterProxy(vertx, configuration));


    sessionServiceBinder = new ServiceBinder(getVertx());
    consumer = sessionServiceBinder
        .setAddress(configuration.getSessionAddress())
        .register(KnotProxy.class, new MultiStepFormsAdapterSessionProxy(vertx, configuration));
  }

  @Override
  public void stop() throws Exception {
    adapterServiceBinder.unregister(consumer);
  }

}
