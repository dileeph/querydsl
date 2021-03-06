/*
 * Copyright 2011, Mysema Ltd
 * 
 * Licensed under the Apache License, Version 2.0 (the "License")
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.mysema.query.scala

object Numeric {
  implicit val byte = new Numeric[Byte]   
  implicit val byte2 = new Numeric[java.lang.Byte]
  implicit val double = new Numeric[Double]  
  implicit val double2 = new Numeric[java.lang.Double]
  implicit val float = new Numeric[Float]  
  implicit val float2 = new Numeric[java.lang.Float]
  implicit val int = new Numeric[Int]  
  implicit val int2 = new Numeric[java.lang.Integer]
  implicit val long = new Numeric[Long]  
  implicit val long2 = new Numeric[java.lang.Long]
  implicit val short = new Numeric[Short]        
  implicit val short2 = new Numeric[java.lang.Short]
    
  implicit val bigDecimal = new Numeric[java.math.BigDecimal]
  implicit val bigInteger = new Numeric[java.math.BigInteger]
}
  
/**
 * Type class for Number types
 */
class Numeric[Num] extends Serializable