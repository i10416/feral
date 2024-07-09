/*
 * Copyright 2021 Typelevel
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

package feral.lambda.runtime

import cats.MonadThrow
import cats.syntax.all._

private[runtime] case class LambdaSettings(
    val functionName: String,
    val functionVersion: String,
    val functionMemorySize: Int,
    val logGroupName: String,
    val logStreamName: String
)

private[runtime] object LambdaSettings {
  def fromLambdaEnv[F[_]: MonadThrow](implicit Env: LambdaRuntimeEnv[F]): F[LambdaSettings] = (
    Env.lambdaFunctionName,
    Env.lambdaFunctionVersion,
    Env.lambdaFunctionMemorySize,
    Env.lambdaLogGroupName,
    Env.lambdaLogStreamName).mapN(LambdaSettings.apply)
}