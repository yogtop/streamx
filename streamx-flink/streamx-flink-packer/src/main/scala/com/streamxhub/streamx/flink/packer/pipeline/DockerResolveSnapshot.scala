/*
 * Copyright 2019 The StreamX Project
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

package com.streamxhub.streamx.flink.packer.pipeline

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.streamxhub.streamx.common.util.Utils

import java.util.{List => JavaList}
import scala.collection.JavaConverters._

/**
 * Snapshot for docker resolved progress
 *
 * @author Al-assad
 */
@JsonIgnoreProperties(ignoreUnknown = true)
case class DockerResolvedSnapshot(pull: DockerPullSnapshot, build: DockerBuildSnapshot, push: DockerPushSnapshot)

/**
 * snapshot for pulling docker image progress.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
case class DockerPullSnapshot(detail: Seq[DockerLayerProgress], error: String, emitTime: Long, percent: Double) {
  def detailAsJava: JavaList[DockerLayerProgress] = detail.asJava
}

/**
 * snapshot for building docker image progress.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
case class DockerBuildSnapshot(detail: Seq[String], emitTime: Long) {
  def detailAsJava: JavaList[String] = detail.asJava
}

/**
 * snapshot for pushing docker image progress.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
case class DockerPushSnapshot(detail: Seq[DockerLayerProgress], error: String, emitTime: Long, percent: Double) {
  def detailAsJava: JavaList[DockerLayerProgress] = detail.asJava
}

object DockerPullSnapshot {
  def of(detail: Seq[DockerLayerProgress], error: String, emitTime: Long): DockerPullSnapshot =
    DockerPullSnapshot(detail, error, emitTime, Utils.calPercent(detail.map(_.current).sum, detail.map(_.total).sum))
}

object DockerPushSnapshot {
  def of(detail: Seq[DockerLayerProgress], error: String, emitTime: Long): DockerPushSnapshot =
    DockerPushSnapshot(detail, error, emitTime, Utils.calPercent(detail.map(_.current).sum, detail.map(_.total).sum))
}






