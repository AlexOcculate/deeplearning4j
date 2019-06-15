/*******************************************************************************
 * Copyright (c) 2015-2018 Skymind, Inc.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Apache License, Version 2.0 which is available at
 * https://www.apache.org/licenses/LICENSE-2.0.
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 *
 * SPDX-License-Identifier: Apache-2.0
 ******************************************************************************/

package org.nd4j.parameterserver.updater.storage;

import lombok.extern.slf4j.Slf4j;
import org.nd4j.aeron.ipc.NDArrayMessage;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Update storage that only stores update counts
 *
 * @author Adam Gibson
 */
@Slf4j
public class NoUpdateStorage extends BaseUpdateStorage {
    private AtomicInteger updateCount = new AtomicInteger(0);

    /**
     * Add an ndarray to the storage
     *
     * @param array the array to add
     */
    @Override
    public void addUpdate(NDArrayMessage array) {
        log.info("Adding array " + updateCount.get());
        updateCount.incrementAndGet();
    }

    /**
     * The number of updates added
     * to the update storage
     *
     * @return
     */
    @Override
    public int numUpdates() {
        return updateCount.get();
    }

    /**
     * Clear the array storage
     */
    @Override
    public void clear() {
        updateCount.set(0);
    }

    /**
     * A method for actually performing the implementation
     * of retrieving the ndarray
     *
     * @param index the index of the {@link INDArray} to get
     * @return the ndarray at the specified index
     */
    @Override
    public NDArrayMessage doGetUpdate(int index) {
        throw new UnsupportedOperationException("Nothing is being stored in this implementation");
    }
}