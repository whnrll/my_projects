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

import org.springframework.data.mapping.Association;
import org.springframework.data.mapping.PersistentProperty;
import org.springframework.data.rest.core.mapping.ResourceMetadata;

import springfox.documentation.spring.data.rest.EntityContext;

public class EntityAssociationContext {
  private final springfox.documentation.spring.data.rest.EntityContext entityContext;
  private final Association<? extends PersistentProperty<?>> association;

  public EntityAssociationContext(
      springfox.documentation.spring.data.rest.EntityContext entityContext,
      Association<? extends PersistentProperty<?>> association) {
    this.entityContext = entityContext;
    this.association = association;
  }

  public EntityContext getEntityContext() {
    return entityContext;
  }


  public Association<? extends PersistentProperty<?>> getAssociation() {
    return association;
  }

  public ResourceMetadata associationMetadata() {
    return entityContext.getAssociations().getMetadataFor(entityContext.entity().getType());
  }
}
