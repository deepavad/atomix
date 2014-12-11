/*
 * Copyright 2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.kuujo.copycat.internal.cluster;

import net.kuujo.copycat.cluster.LocalMember;
import net.kuujo.copycat.cluster.MessageHandler;
import net.kuujo.copycat.spi.ExecutionContext;

/**
 * Internal local cluster member.
 *
 * @author <a href="http://github.com/kuujo">Jordan Halterman</a>
 */
public class CoordinatedLocalMember extends CoordinatedMember implements LocalMember {
  private final InternalLocalMember parent;

  public CoordinatedLocalMember(int id, InternalLocalMember parent, ExecutionContext context) {
    super(id, parent, context);
    this.parent = parent;
  }

  @Override
  public <T, U> LocalMember handler(String topic, MessageHandler<T, U> handler) {
    if (handler != null) {
      parent.register(topic, id, handler);
    } else {
      parent.unregister(topic, id);
    }
    return this;
  }

}
