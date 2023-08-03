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

import org.springframework.data.mapping.Association;
import org.springframework.data.mapping.PersistentEntity;
import org.springframework.data.mapping.PersistentProperty;
import org.springframework.data.mapping.SimpleAssociationHandler;
import org.springframework.data.rest.webmvc.mapping.Associations;

import com.google.common.base.Function;
import com.google.common.collect.FluentIterable;

import springfox.documentation.RequestHandler;
import springfox.documentation.spring.data.rest.EntityAssociationContext;
import springfox.documentation.spring.data.rest.EntityAssociationOperationsExtractor;
import springfox.documentation.spring.data.rest.EntityContext;
import springfox.documentation.spring.data.rest.EntityOperationsExtractor;

public class EntityAssociationsExtractor implements EntityOperationsExtractor {


  @Override
  public List<RequestHandler> extract(final EntityContext context) {
    final List<RequestHandler> handlers = newArrayList();
    final PersistentEntity<?, ?> entity = context.entity();
    final Associations associations = context.getAssociations();

    entity.doWithAssociations(new SimpleAssociationHandler() {

      @Override
      public void doWithAssociation(Association<? extends PersistentProperty<?>> association) {
        PersistentProperty<?> property = association.getInverse();
        if (!associations.isLinkableAssociation(property)) {
          return;
        }
        final EntityAssociationContext associationContext = new EntityAssociationContext(context, association);
        handlers.addAll(FluentIterable.from(context.getAssociationExtractors())
            .transformAndConcat(extractHandlers(associationContext))
            .toList());
      }
    });
    return handlers;
  }

  private Function<EntityAssociationOperationsExtractor, Iterable<RequestHandler>> extractHandlers(
      final EntityAssociationContext associationContext) {
    return new Function<EntityAssociationOperationsExtractor, Iterable<RequestHandler>>() {
      @Override
      public Iterable<RequestHandler> apply(EntityAssociationOperationsExtractor input) {
        return input.extract(associationContext);
      }
    };
  }

}
