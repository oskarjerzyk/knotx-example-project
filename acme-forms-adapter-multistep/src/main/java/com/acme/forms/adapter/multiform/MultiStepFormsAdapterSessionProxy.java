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
import io.knotx.dataobjects.KnotContext;
import io.knotx.knot.AbstractKnotProxy;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.reactivex.Single;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.reactivex.core.Vertx;
import java.util.Set;


public class MultiStepFormsAdapterSessionProxy extends AbstractKnotProxy {

  private static final Logger LOGGER = LoggerFactory
      .getLogger(MultiStepFormsAdapterSessionProxy.class);

  private final Vertx vertx;

  private MultiStepFormsAdapterConfiguration configuration;

  MultiStepFormsAdapterSessionProxy(Vertx vertx, MultiStepFormsAdapterConfiguration configuration) {
    this.vertx = vertx;
    this.configuration = configuration;
  }

  @Override
  public Single<KnotContext> processRequest(final KnotContext knotContext) {
    return Single.just(knotContext);

  }

  @Override
  protected boolean shouldProcess(Set<String> knots) {
    return true;
  }

  @Override
  protected KnotContext processError(KnotContext context, Throwable error) {
    LOGGER.error("Could not process template [{}]", context.getClientRequest().getPath(), error);
    KnotContext errorResponse = new KnotContext().setClientResponse(context.getClientResponse());
    errorResponse.getClientResponse()
        .setStatusCode(HttpResponseStatus.INTERNAL_SERVER_ERROR.code());
    return errorResponse;
  }
}
