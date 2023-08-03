/*
 *
 *  Copyright 2017-2018 the original author or authors.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 *
 */
package org.example.common.starter.swagger.spring.data.rest;

import static com.google.common.collect.Lists.newArrayList;

import java.util.List;

import springfox.documentation.spring.data.rest.EntityAssociationDeleteExtractor;
import springfox.documentation.spring.data.rest.EntityAssociationItemDeleteExtractor;
import springfox.documentation.spring.data.rest.EntityAssociationItemGetExtractor;
import springfox.documentation.spring.data.rest.EntityAssociationOperationsExtractor;
import springfox.documentation.spring.data.rest.EntityAssociationSaveExtractor;
import springfox.documentation.spring.data.rest.EntityAssociationsExtractor;

class DefaultExtractorConfiguration implements RequestHandlerExtractorConfiguration {
  private final List<EntityOperationsExtractor> defaultEntityExtractors  = newArrayList(
      new springfox.documentation.spring.data.rest.EntitySaveExtractor(),
      new EntityDeleteExtractor(),
      new EntityFindOneExtractor(),
      new springfox.documentation.spring.data.rest.EntityFindAllExtractor(),
      new springfox.documentation.spring.data.rest.EntitySearchExtractor(),
      new EntityAssociationsExtractor()
  );


  private final List<springfox.documentation.spring.data.rest.EntityAssociationOperationsExtractor> defaultAssociationExtractors = newArrayList(
      new EntityAssociationSaveExtractor(),
      new EntityAssociationDeleteExtractor(),
      new EntityAssociationGetExtractor(),
      new EntityAssociationItemGetExtractor(),
      new EntityAssociationItemDeleteExtractor()
  );

  @Override
  public List<EntityOperationsExtractor> getEntityExtractors() {
    return defaultEntityExtractors;
  }

  @Override
  public List<EntityAssociationOperationsExtractor> getAssociationExtractors() {
    return defaultAssociationExtractors;
  }
}
