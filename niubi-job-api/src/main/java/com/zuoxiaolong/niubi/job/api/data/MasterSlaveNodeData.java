/*
 * Copyright 2002-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.zuoxiaolong.niubi.job.api.data;

import lombok.Getter;
import lombok.Setter;
import org.apache.curator.framework.recipes.cache.ChildData;

import java.util.ArrayList;
import java.util.List;

/**
 * 主从模式ZK Node数据节点类
 *
 * @author Xiaolong Zuo
 * @since 0.9.3
 */
@Getter
@Setter
public class MasterSlaveNodeData extends AbstractGenericData<MasterSlaveNodeData, MasterSlaveNodeData.Data> {

    public MasterSlaveNodeData(ChildData childData) {
        super(childData);
    }

    public MasterSlaveNodeData(String path, byte[] bytes) {
        super(path, bytes);
    }

    public MasterSlaveNodeData(String path, Data data) {
        super(path, data);
    }

    @Setter
    @Getter
    public static class Data extends AbstractNodeData<Data> {

        private List<String> jobPaths;

        public Data() {
            this(null);
        }

        public Data(String ip) {
            super(ip);
            setState("Slave");
        }

        public void addJobPath(String jobPath) {
            if (jobPaths == null) {
                jobPaths = new ArrayList<>();
            }
            jobPaths.add(jobPath);
            increment();
        }

        public void removeJobPath(String jobPath) {
            if (jobPaths == null) {
                return;
            }
            jobPaths.remove(jobPath);
            decrement();
        }

    }

}
