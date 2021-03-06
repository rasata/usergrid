/*
 *
 *  * Licensed to the Apache Software Foundation (ASF) under one or more
 *  *  contributor license agreements.  The ASF licenses this file to You
 *  * under the Apache License, Version 2.0 (the "License"); you may not
 *  * use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *     http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.  For additional information regarding
 *  * copyright in this work, please see the NOTICE file in the top level
 *  * directory of this distribution.
 *
 */

package org.apache.usergrid.services.queues;


import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.usergrid.persistence.queue.LegacyQueueManager;
import org.apache.usergrid.persistence.queue.LegacyQueueMessage;


/**
 * Manages the queueManager implementation for Import
 */
public class ImportQueueManager implements LegacyQueueManager {

    @Override
    public List<LegacyQueueMessage> getMessages(final int limit, final Class klass) {
        return new ArrayList<>();
    }

    @Override
    public long getQueueDepth() {
        return 0;
    }


    @Override
    public void commitMessage( final LegacyQueueMessage queueMessage ) {

    }


    @Override
    public void commitMessages( final List<LegacyQueueMessage> queueMessages ) {

    }


    @Override
    public void sendMessages( final List bodies ) throws IOException {

    }


    @Override
    public List<LegacyQueueMessage> sendQueueMessages(final List<LegacyQueueMessage> queueMessages ) throws IOException {
        return new ArrayList<>();
    }


    @Override
    public <T extends Serializable> void sendMessageToLocalRegion(final T body ) throws IOException {

    }


    @Override
    public <T extends Serializable> void sendMessageToAllRegions(final T body ) throws IOException {

    }

    @Override
    public void deleteQueue() {

    }

    @Override
    public void clearQueueNameCache(){
        //no-op
    }
}
